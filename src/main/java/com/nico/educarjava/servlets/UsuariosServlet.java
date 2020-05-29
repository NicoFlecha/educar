package com.nico.educarjava.servlets;

import com.google.gson.Gson;
import com.nico.educarjava.ajax.ResponseJSON;
import com.nico.educarjava.model.dao.UsuarioDAO;
import com.nico.educarjava.utils.Middleware;
import com.nico.educarjava.model.entities.Usuario;
import com.nico.educarjava.model.entities.Curso;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UsuariosServlet", urlPatterns = {"/usuario"})
public class UsuariosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Middleware middleware = new Middleware();
        ResponseJSON respuesta;
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        HttpSession session = request.getSession();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        if (!middleware.estaLogeado(request, response)) {
            respuesta = new ResponseJSON("ERROR", "No estás autorizado para ver este contenido", new ArrayList());
            gson.toJson(respuesta);
            out.print(gson.toJson(respuesta));
            out.flush();
        } else {
            
            Usuario usuario = (Usuario) session.getAttribute("user");
            
            String ver = request.getParameter("ver");
            
            String obtener = request.getParameter("obtener");
            
            if (ver != null) {
                
                switch (ver) {
                    
                    case "cursos":
                        request.getRequestDispatcher("usuario_cursos.jsp").forward(request, response);
                        
                        break;
                    
                    case "perfil":
                        request.getRequestDispatcher("usuario_perfil.jsp").forward(request, response);
                        
                        break;
                }
                
            }
            
            if (obtener != null) {
                
                switch (obtener) {
                    
                    case "cursos":
                        
                        ArrayList<Curso> listaCursos = usuarioDAO.getCursos(usuario.getId());
                        
                        String message;
                        
                        if (listaCursos.size() > 0) {
                            message = "Cursos cargados correctamente";
                        } else {
                            message = "No hay cursos asociados a este usuario";
                        }
                        
                        respuesta = new ResponseJSON("OK", message, listaCursos);
                        
                        out.print(gson.toJson(respuesta));
                        out.flush();
                        
                        request.getRequestDispatcher("cursos_usuario");
                        
                        break;
                        
                    case "usuario":
                        Usuario usuarioResultado = usuarioDAO.getUsuarioById(usuario.getId());
                        ArrayList data = new ArrayList();
                        data.add(usuarioResultado);
                        
                        respuesta = new ResponseJSON("OK", "Datos del usuario entregados correctamente", data);
                        
                        out.print(gson.toJson(respuesta));
                        out.flush();
                        
                        break;
                    
                }
                
            }
            
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Middleware middleware = new Middleware();
        ResponseJSON respuesta;
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        if (!middleware.estaLogeado(request, response)) {
            respuesta = new ResponseJSON("ERROR", "No estás autorizado para realizar esta acción", new ArrayList());
            out.print(gson.toJson(respuesta));
            out.flush();
        }
        
        Usuario usuario = (Usuario) session.getAttribute("user");
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        Usuario usuarioBD = usuarioDAO.getUsuarioById(usuario.getId());
        
        usuarioBD.setNombre(request.getParameter("nombre"));
        usuarioBD.setApellido(request.getParameter("apellido"));
        usuarioBD.setFechaNacimiento((LocalDate) LocalDate.parse(request.getParameter("fecha_nacimiento")));
        usuarioBD.setFoto(request.getParameter("foto"));
        
        usuarioDAO.actualizarUsuario(usuarioBD);
        
        respuesta = new ResponseJSON("OK", "Datos actualizados correctamente!", new ArrayList());
        
        out.print(gson.toJson(respuesta));
        out.flush();
                
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
