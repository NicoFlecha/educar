package com.nico.educarjava.servlets;

import com.nico.educarjava.model.dao.CategoriasDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.nico.educarjava.model.entities.Categoria;
import com.nico.educarjava.utils.Middleware;
import java.util.ArrayList;

@WebServlet(name = "CategoriasServlet", urlPatterns = {"/categorias"})
public class CategoriasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String ver = request.getParameter("ver");
        
        CategoriasDAO categoriasDAO = new CategoriasDAO();
        
        Middleware middleware = new Middleware();
        
        CursosServlet cursosServlet = new CursosServlet();
        
        if(ver != null) {
            
            switch(ver) {
                
                case "nuevo":
                    
                    if (!middleware.esAdmin(request, response)) {
                        
                        request.getRequestDispatcher("/cursos").forward(request, response);
                        
                    }
                    
                    ArrayList<Categoria> categorias = categoriasDAO.getListadoCategorias();
                    
                    request.setAttribute("categorias", categorias);
                    
                    request.getRequestDispatcher("categorias_formulario.jsp").forward(request, response);
                    
                    break;
                
            }
            
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String accion = request.getParameter("accion");
        
        CursosServlet cursosServlet = new CursosServlet();
        
        Middleware middleware = new Middleware();
        
        if(accion != null) {
            
            if (!middleware.esAdmin(request, response)) {
                
                request.getRequestDispatcher("cursos");
                cursosServlet.doGet(request, response);
                
            }
            
            Categoria categoria = new Categoria();
            
            CategoriasDAO categoriasDAO = new CategoriasDAO();
            
            switch(accion) {
                
                case "guardar":
                    
                    
                    System.out.println(request.getAttributeNames());
                    categoria.setNombre(request.getParameter("nombre"));
                    
                    categoriasDAO.guardarCategoria(categoria);
                    
                    response.sendRedirect("categorias?ver=nuevo");
                    
                    break;
                    
                case "eliminar":
                    
                    categoria = categoriasDAO.getCategoria( Integer.parseInt(request.getParameter("id")) );
                    
                    System.out.println(categoria.getNombre());
                    
                    categoriasDAO.eliminarCategoria(categoria);
                    
                    response.sendRedirect("categorias?ver=nuevo");
                    
                    break;
            }
            
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
