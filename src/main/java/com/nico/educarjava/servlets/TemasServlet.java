package com.nico.educarjava.servlets;

import com.google.gson.Gson;
import com.nico.educarjava.ajax.ResponseJSON;
import com.nico.educarjava.model.entities.Tema;
import com.nico.educarjava.model.entities.Curso;
import com.nico.educarjava.model.dao.CursosDAO;
import com.nico.educarjava.model.dao.TemasDAO;
import com.nico.educarjava.model.entities.Usuario;
import com.nico.educarjava.utils.Middleware;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "TemasServlet", urlPatterns = {"/temas"})
public class TemasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Middleware middleware = new Middleware();
        CursosDAO cursosDAO = new CursosDAO();
        HttpSession session = request.getSession();
        
        if (!middleware.estaLogeado(request, response)) {
            response.sendRedirect("login");
        }
        
        String ver = request.getParameter("ver");
        
        if (ver != null) {
            
            TemasDAO temasDAO = new TemasDAO();
            
            switch (ver) {
                
                case "temas":
                    
                    PrintWriter out = response.getWriter();
        
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    
                    int cursoId = Integer.parseInt(request.getParameter("curso"));
                    
                    if (!middleware.esAdmin(request, response) && !cursosDAO.estaInscripto(((Usuario) session.getAttribute("user")).getId(), cursoId)) {
                        
                        ResponseJSON respuesta = new ResponseJSON("ERROR", "No estás autorizado para ver este contenido", new ArrayList());

                        Gson gson = new Gson();

                        out.print(gson.toJson(respuesta));
                        out.flush();
                        
                    } else {
                        
                        ArrayList<Tema> listadoTemas = temasDAO.getListadoTemasByCurso(cursoId);
                    
                        String mensaje;

                        if (listadoTemas.size()>0) {
                            mensaje = "Temas cargados correctamente!";
                        } else {
                            mensaje = "No hay temas";
                        }

                        ResponseJSON respuesta = new ResponseJSON("OK", mensaje, listadoTemas);

                        Gson gson = new Gson();

                        out.print(gson.toJson(respuesta));
                        out.flush();
                        
                    }
                    
                    break;
                
            }
            
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        int idCurso;
        idCurso = Integer.parseInt(request.getParameter("curso_id"));
        
        Tema tema = new Tema();
        
        CursosDAO cursosDAO = new CursosDAO();
        Curso curso = cursosDAO.getCurso(idCurso);
        
        tema.setCurso(curso);
        tema.setNumero_tema(Integer.parseInt(request.getParameter("numero_tema")));
        tema.setNombre(request.getParameter("nombre_tema"));
        tema.setDescripcion(request.getParameter("descripcion_tema"));
        tema.setArchivo(request.getParameter("archivo_tema"));
        
        TemasDAO temasDAO = new TemasDAO();
        
        temasDAO.guardarTema(tema);

        ResponseJSON respuesta = new ResponseJSON("OK", "Tema guardado correctamente", new ArrayList());

        Gson gson = new Gson();

        out.print(gson.toJson(respuesta));
        out.flush();
        
    }
    
    protected void doDelete (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        int idTema;
        idTema = Integer.parseInt(request.getParameter("tema"));
        
        TemasDAO temasDAO = new TemasDAO();
        
        temasDAO.eliminarTema(idTema);
        
        ResponseJSON respuesta = new ResponseJSON("OK", "Tema Eliminado correctamente", new ArrayList());

        Gson gson = new Gson();

        out.print(gson.toJson(respuesta));
        out.flush();
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
