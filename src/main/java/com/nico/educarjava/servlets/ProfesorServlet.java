package com.nico.educarjava.servlets;

import com.google.gson.Gson;
import com.nico.educarjava.model.dao.ProfesoresDAO;
import com.nico.educarjava.model.entities.Profesor;
import com.nico.educarjava.ajax.ResponseJSON;
import com.nico.educarjava.utils.Middleware;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProfesorServlet", urlPatterns = {"/profesor"})
public class ProfesorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String ver = request.getParameter("ver");
        String obtener = request.getParameter("obtener");
        Middleware middleware = new Middleware();
        ResponseJSON respuesta;
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        if (ver != null) {
            
            switch (ver) {
                case "panel" :
                    request.getRequestDispatcher("profesor_panel.jsp").forward(request, response);
                
                case "cursos" :
                    request.getRequestDispatcher("profesor_cursos.jsp").forward(request, response);
                    
                case "alumnos" :
                    request.getRequestDispatcher("profesor_alumnos.jsp").forward(request, response);
            }
            
        }
        
        if (obtener != null) {
            
            if(!middleware.esAdmin(request, response)) {
                respuesta = new ResponseJSON("ERROR", "No estás autorizado para ver este contenido", new ArrayList());
                out.print(gson.toJson(respuesta));
                out.flush();
            }
            
            ProfesoresDAO profesoresDAO = new ProfesoresDAO();
            
            switch(obtener) {    
                
                case "profesores":        
                    ArrayList<Profesor> listaProfesores = profesoresDAO.getListadoProfesores();
                    
                    String message;
                    
                    if (listaProfesores.size() > 0) {
                        message = "Lista de profesores cargado correctamente";
                    } else {
                        message = "No hay profesores aun";
                    }
                    
                    respuesta = new ResponseJSON("OK", message, listaProfesores);
                    
                    out.print(gson.toJson(respuesta));
                    out.flush();
                    
                    break;
                    
                case "profesor":
                    
                    int idProfesor = Integer.parseInt(request.getParameter("profesor"));
                    
                    Profesor profesor = profesoresDAO.getProfesor(idProfesor);
                    
                    ArrayList data = new ArrayList();
                    data.add(profesor);
                    
                    respuesta = new ResponseJSON("OK", "Datos del profesor entregados correctamente", data);
                    
                    out.print(gson.toJson(respuesta));
                    out.flush();
                    
                    break;
                
            }
            
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        ResponseJSON respuesta;
        Gson gson = new Gson();
        Middleware middleware = new Middleware();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        if (!middleware.esAdmin(request, response)) {
            respuesta = new ResponseJSON("ERROR", "No estás autorizado para realizar esta acción", new ArrayList());
            out.print(gson.toJson(respuesta));
            out.flush();
        } else {
            
            ProfesoresDAO profesoresDAO = new ProfesoresDAO();
            
            Profesor profesor = new Profesor();
            profesor.setNombre(request.getParameter("nombre"));
            profesor.setApellido(request.getParameter("apellido"));
            profesor.setFechaNacimiento((LocalDate) LocalDate.parse(request.getParameter("fecha_nacimiento")));
            profesor.setEmail(request.getParameter("email"));
            profesor.setPassword(request.getParameter("password"));
            profesor.setFoto(request.getParameter("foto"));
            
            profesoresDAO.guardarProfesor(profesor);
            
            respuesta = new ResponseJSON("OK", "Profesor creado correctamente", new ArrayList());
            
            out.print(gson.toJson(respuesta));
            out.flush();
            
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ResponseJSON respuesta;
        PrintWriter out = response.getWriter();
        Middleware middleware = new Middleware();
        Gson gson = new Gson();
        
        if(!middleware.esAdmin(request, response)) {
            respuesta = new ResponseJSON("ERROR", "No estás autorizado para realizar esta acción", new ArrayList());
            out.print(respuesta);
            out.flush();
        }
        
        ProfesoresDAO profesoresDAO = new ProfesoresDAO();
        
        Profesor profesor = new Profesor();
        profesor.setNombre(request.getParameter("nombre"));
        profesor.setApellido(request.getParameter("apellido"));
        profesor.setFechaNacimiento(LocalDate.parse(request.getParameter("fecha_nacimiento")));
        profesor.setFoto(request.getParameter("foto"));
        profesor.setId(Integer.parseInt(request.getParameter("id")));
        
        profesoresDAO.actualizarProfesor(profesor);
        
        respuesta = new ResponseJSON("OK", "Profesor actualizado correctamente", new ArrayList());
        out.print(gson.toJson(respuesta));
        out.flush();
        
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        ResponseJSON respuesta;
        Gson gson = new Gson();
        Middleware middleware = new Middleware();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        if (!middleware.esAdmin(request, response)) {
            respuesta = new ResponseJSON("ERROR", "No estás autorizado para realizar esta acción", new ArrayList());
            out.print(gson.toJson(respuesta));
            out.flush();
        } else {
            
            ProfesoresDAO profesoresDAO = new ProfesoresDAO();
            
            profesoresDAO.eliminarProfesor(Integer.parseInt(request.getParameter("profesor")));
            
            respuesta = new ResponseJSON("OK", "Profesor eliminado correctamente", new ArrayList());
            
            out.print(gson.toJson(respuesta));
            
            out.flush();
            
        }
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
