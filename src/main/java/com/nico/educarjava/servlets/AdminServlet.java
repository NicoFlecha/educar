package com.nico.educarjava.servlets;

import com.google.gson.Gson;
import com.nico.educarjava.ajax.ResponseJSON;
import com.nico.educarjava.model.dao.CategoriasDAO;
import com.nico.educarjava.model.dao.CursosDAO;
import com.nico.educarjava.model.dao.UsuarioDAO;
import com.nico.educarjava.model.entities.Categoria;
import com.nico.educarjava.model.entities.Curso;
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

@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        ResponseJSON respuesta;
        Gson gson = new Gson();
        
        Middleware middleware = new Middleware();
        if (!middleware.esAdmin(request, response)) {
            request.getRequestDispatcher("/").forward(request, response);
        }
        
        String ver = request.getParameter("ver");
        String editar = request.getParameter("editar");
        String obtener = request.getParameter("obtener");

        if (ver != null)
        
        switch (ver) {
            
            case "panel":
                request.getRequestDispatcher("admin_panel.jsp").forward(request, response);
                break;
                
            case "cursos":
                CursosDAO cursosDAO = new CursosDAO();
                CategoriasDAO categoriasDAO = new CategoriasDAO();
                
                ArrayList<Curso> cursos = cursosDAO.getListadoCursos();
                request.setAttribute("cursos", cursos);
            
                ArrayList<Categoria> categorias = categoriasDAO.getListadoCategorias();
                request.setAttribute("categorias", categorias);
                request.getRequestDispatcher("admin_cursos.jsp").forward(request, response);
                
                break;
                
            case "profesores":
                request.getRequestDispatcher("admin_profesores.jsp").forward(request, response);
                break;
                
            case "alumnos":
                request.getRequestDispatcher("admin_alumnos.jsp").forward(request, response);
                break;
                
        }
        
        if (editar != null) {
            
            switch(editar) {
                
                case "curso":
                    CursosDAO cursosDAO = new CursosDAO();
                    Curso curso = cursosDAO.getCurso(Integer.valueOf(request.getParameter("curso")));
                    System.out.println(curso.getNombre());
                    
                    request.setAttribute("curso", curso);
                    request.getRequestDispatcher("cursos?ver=nuevo").forward(request, response);
                
            }
            
        }
        
        if (obtener != null) {
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            
            switch (obtener) {
                
                case "alumnos":
                    
                    
                    ArrayList<Usuario> listaUsuarios = usuarioDAO.getListadoUsuarios();
                    
                    respuesta = new ResponseJSON("OK", "Listado de usuarios cargado correctamente", listaUsuarios);
                    
                    out.print(gson.toJson(respuesta));
                    out.flush();
                    
                    break;
                    
                case "busqueda":
                    String buscar = request.getParameter("buscar");
                    
                    ArrayList<Usuario> resultado = usuarioDAO.buscarUsuario(buscar);
                    
                    String message;
                    
                    if (resultado.size() > 0) {
                        message = "Usuarios encontrados!";
                    } else {
                        message = "No encontramos usuarios :(";
                    }
                    
                    respuesta = new ResponseJSON("OK", message, resultado);
                    
                    out.print(gson.toJson(respuesta));
                    out.flush();
                    
                    break;
                
            }
            
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
