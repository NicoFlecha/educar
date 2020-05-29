package com.nico.educarjava.servlets;

import com.google.gson.Gson;
import com.nico.educarjava.ajax.ResponseJSON;
import com.nico.educarjava.model.entities.Categoria;
import com.nico.educarjava.model.entities.Curso;
import com.nico.educarjava.model.dao.CategoriasDAO;
import com.nico.educarjava.model.dao.CursosDAO;
import com.nico.educarjava.model.entities.Usuario;
import com.nico.educarjava.model.entities.UsuarioCurso;
import com.nico.educarjava.utils.Middleware;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CursosServlet", urlPatterns = {"/cursos"})
public class CursosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String ver = request.getParameter("ver");
        String obtener = request.getParameter("obtener");
        CategoriasDAO categoriasDAO = new CategoriasDAO();
        CursosDAO cursosDAO = new CursosDAO();
        Middleware middleware = new Middleware();
        
        if(ver!=null) {
            
            switch(ver) {
                
                case "nuevo":
                    
                    if(!middleware.esAdmin(request, response)) {
                        request.getRequestDispatcher("/").forward(request, response);
                    }
                    
                    ArrayList<Categoria> listadoCategorias = categoriasDAO.getListadoCategorias();
                    
                    request.setAttribute("listadoCategorias", listadoCategorias);
                    
                    request.getRequestDispatcher("curso_formulario.jsp").forward(request, response);
                    
                    break;
                
                case "curso":
                    
                    String idCursoString = request.getParameter("curso");
                    Integer idCurso = Integer.parseInt(idCursoString);
                    
                    Curso curso = cursosDAO.getCurso(idCurso);
                    request.setAttribute("curso", curso);
                    
                    Boolean inscripto = false;
                    
                    if (middleware.estaLogeado(request, response)) {
                        HttpSession session = request.getSession();
                        Usuario usuario = (Usuario) session.getAttribute("user");
                        
                        if(cursosDAO.estaInscripto(usuario.getId(), idCurso)) {
                            inscripto = true;
                        }
                    }
                    
                    request.setAttribute("inscripto", inscripto);
                    
                    request.getRequestDispatcher("verCurso.jsp").forward(request, response);
                    
                    break;
                    
                case "comentarios":
                    
                    PrintWriter out = response.getWriter();
        
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    
                    idCurso = Integer.parseInt(request.getParameter("curso"));
                    ArrayList<UsuarioCurso> infoCurso = cursosDAO.getInfoUsuarios(idCurso);
                    
                    ResponseJSON respuesta = new ResponseJSON("OK", "Puntuaciones entregadas!", infoCurso);
                    
                    Gson gson = new Gson();
                    
                    out.print(gson.toJson(respuesta));
                    out.flush();
                    
                    break;
                
            }
            
        } else if (obtener != null) {
            
            PrintWriter out = response.getWriter();
            ResponseJSON respuesta;
            Gson gson = new Gson();
            
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            
            switch (obtener) {
                
                case "cursos":
                    ArrayList<Curso> listadoCursos = cursosDAO.getListadoCursos();
                    
                    respuesta = new ResponseJSON("OK", "Cursos cargados correctamente", listadoCursos);
                    
                    out.print(gson.toJson(respuesta));
                    out.flush();
                    
                    break;
                    
                case "esProfesor":
                    boolean esProfesor = cursosDAO.esProfesorDelCurso(Integer.parseInt(request.getParameter("profesor")), Integer.parseInt(request.getParameter("curso")));
                    ArrayList data = new ArrayList();
                    data.add(esProfesor);
                    
                    respuesta = new ResponseJSON("OK", "Comprobado correctamente", data);
                    
                    out.print(gson.toJson(respuesta));
                    out.flush();
                
            }
            
        } else {
            
            ArrayList<Curso> cursos = cursosDAO.getListadoCursos();
            request.setAttribute("cursos", cursos);
            
            ArrayList<Categoria> categorias = categoriasDAO.getListadoCategorias();
            request.setAttribute("categorias", categorias);
        
            request.getRequestDispatcher("cursos.jsp").forward(request, response);
            
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        Middleware middleware = new Middleware();
        
        PrintWriter out = response.getWriter();
        
        Gson gson = new Gson();
        
        if(accion != null) {
            
            CursosDAO cursosDAO = new CursosDAO();
            
            switch(accion) {
                
                case "guardar":
        
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    
                    if (!middleware.esAdmin(request, response)) {
                        ResponseJSON respuesta = new ResponseJSON("ERROR", "No estás autorizado para realizar esta acción", new ArrayList());

                        out.print(gson.toJson(respuesta));
                        out.flush();
                    }
                    
                    Curso curso = new Curso();
                    curso.setNombre(request.getParameter("nombre"));
                    curso.setFoto(request.getParameter("foto"));
                    curso.setDescripcion(request.getParameter("descripcion"));
                    
                    String fechaString = request.getParameter("fecha_inicio");  
                    curso.setFechaInicio((LocalDate) LocalDate.parse(fechaString));
                    
                    curso.setDuracion(request.getParameter("duracion"));
                    
                    String categoriaString = request.getParameter("categoria");
                    int idCategoria = Integer.parseInt(categoriaString);
                    
                    CategoriasDAO categoriasDAO = new CategoriasDAO();
                    Categoria categoria = categoriasDAO.getCategoria(idCategoria);
                    
                    curso.setCategoria(categoria);
                    
                    curso.setPrecio(Integer.parseInt(request.getParameter("precio")));
                    
                    String mensaje;
                    ArrayList data = new ArrayList(); 
                    
                    if (Integer.valueOf(request.getParameter("curso_id")) != 0) {
                        cursosDAO.actualizarCurso(curso, Integer.valueOf(request.getParameter("curso_id")));
                        mensaje = "Curso actualizado correctamente!";
                        
                        
                    } else {
                        int cursoIdCreado = cursosDAO.guardarCurso(curso);
                        mensaje = "Curso guardado correctamente!";
                        data.add(cursoIdCreado);
                    }
                    
                    ResponseJSON respuesta = new ResponseJSON("OK", mensaje, data);

                    out.print(gson.toJson(respuesta));
                    out.flush();
                    
                    break;
                    
                case "inscribir":
                    if (!middleware.estaLogeado(request, response)) {
                        request.getRequestDispatcher("login").forward(request, response);
                    }
        
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    
                    HttpSession session = request.getSession();
                    
                    Usuario usuario = (Usuario) session.getAttribute("user");
                    
                    int idCurso = Integer.parseInt(request.getParameter("idCurso"));
                    
                    cursosDAO.inscribirUsuario(usuario.getId(), idCurso);
                    
                    respuesta = new ResponseJSON("OK", "Ya podés acceder al curso!", new ArrayList());
                    
                    gson = new Gson();
                    
                    out.print(gson.toJson(respuesta));
                    out.flush();
                    
                    break;
                    
                case "agregarProfesor":
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    
                    if (!middleware.esAdmin(request, response)) {
                        respuesta = new ResponseJSON("ERROR", "No estás autorizado para realizar esta acción", new ArrayList());
                        out.print(gson.toJson(respuesta));
                        out.flush();
                    } else {
                        
                        cursosDAO.agregarProfesor(Integer.parseInt(request.getParameter("profesor")), Integer.parseInt(request.getParameter("curso")));
                        
                        respuesta = new ResponseJSON("OK", "Profesor agregado correctamente", new ArrayList());
                        
                        out.print(gson.toJson(respuesta));
                        out.flush();
                        
                    }
                    
                    break;
                    
            }
            
        }

    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        if (accion != null) {
            
            CursosDAO cursosDAO = new CursosDAO();
            Middleware middleware = new Middleware();
            ResponseJSON respuesta;
            Gson gson = new Gson();
            PrintWriter out = response.getWriter();
            HttpSession session = request.getSession();
            
            switch(accion) {
                
                case "comentario":
                    
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    
                    if (!middleware.estaLogeado(request, response)) {
                        respuesta = new ResponseJSON("ERROR", "No estás autorizado para realizar esta acción", new ArrayList());
                        gson = new Gson();
                        out.print(gson.toJson(respuesta));
                        out.flush();
                        break;
                    }
                    
                    Usuario usuario = (Usuario) session.getAttribute("user");
                    
                    int idCurso = Integer.parseInt(request.getParameter("curso"));
                    
                    System.out.println("El id del curso es: " + idCurso);
                    
                    String comentario = request.getParameter("comentario");
                    
                    cursosDAO.guardarPuntuacion(idCurso, usuario.getId(), comentario);
                    
                    respuesta = new ResponseJSON("OK", "Tu comentario fue guardado", new ArrayList());
                    
                    gson = new Gson();
                    
                    out.print(gson.toJson(respuesta));
                    out.flush();
                    
                    break;
                
            }
            
        }
        
    }
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Middleware middleware = new Middleware();
        PrintWriter out = response.getWriter();
        ResponseJSON respuesta;
        Gson gson = new Gson();
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        if(!middleware.esAdmin(request, response)) {
            respuesta = new ResponseJSON("ERROR", "No estás autorizado para realizar esta acción", new ArrayList());
            out.print(gson.toJson(respuesta));
            out.flush();
        }
        
        CursosDAO cursosDAO = new CursosDAO();
        String accion = request.getParameter("accion");

        if(accion != null) {
            
            switch (accion) {
                
                case "curso":
                    String idCurso = request.getParameter("curso");
        
                    
                    cursosDAO.eliminarCurso(Integer.parseInt(idCurso));

                    respuesta = new ResponseJSON("OK", "Curso Eliminado Correctamente", new ArrayList());

                    out.print(gson.toJson(respuesta));
                    out.flush();
                    
                    break;
                    
                case "profesor":
                    int cursoId = Integer.parseInt(request.getParameter("curso"));
                    int profesorId = Integer.parseInt(request.getParameter("profesor"));
                    
                    cursosDAO.eliminarProfesor(profesorId, cursoId);
                    
                    respuesta = new ResponseJSON("OK", "Profesor eliminado del curso!", new ArrayList());
                    
                    out.print(gson.toJson(respuesta));
                    out.flush();
                    
                    break;
                
            }
            
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
