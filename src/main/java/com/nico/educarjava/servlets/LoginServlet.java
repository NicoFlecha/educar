package com.nico.educarjava.servlets;

import com.nico.educarjava.model.dao.UsuarioDAO;
import com.nico.educarjava.model.entities.Usuario;
import com.nico.educarjava.utils.Middleware;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Middleware middleware = new Middleware();
        
        if(middleware.estaLogeado(request, response)) {
            
            CursosServlet cursosServlet = new CursosServlet();
            cursosServlet.doGet(request, response);
            
        }
        
        request.getRequestDispatcher("login.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        Usuario usuarioForm = new Usuario();
        usuarioForm.setEmail(request.getParameter("email"));
        usuarioForm.setPassword(request.getParameter("password"));
        
        Usuario usuarioBD = new Usuario();
        usuarioBD = usuarioDAO.getUsuarioByEmail(usuarioForm.getEmail());
        
        
        
        if(usuarioBD.getEmail() == null || !usuarioForm.getPassword().equals(usuarioBD.getPassword())) {
            
            String error = "Usuario ó contraseña incorrectos";
            request.setAttribute("loginError", error);
            doGet(request, response);
            
        } else {
            
            HttpSession session = request.getSession();
            session.setAttribute("user", usuarioBD);
            
            Cookie idCookie = new Cookie("id", String.valueOf(usuarioBD.getId()));
            
            response.addCookie(idCookie);
            response.sendRedirect("cursos");
            
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
