package com.nico.educarjava.servlets;

import com.nico.educarjava.model.dao.TipoUsuarioDAO;
import com.nico.educarjava.model.entities.TipoUsuario;
import com.nico.educarjava.model.dao.UsuarioDAO;
import com.nico.educarjava.model.entities.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.nico.educarjava.servlets.LoginServlet;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/registro"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellido(request.getParameter("apellido"));
        usuario.setEmail(request.getParameter("email"));
        usuario.setPassword(request.getParameter("password"));
        
        TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
        TipoUsuario tipoUsuario = tipoUsuarioDAO.getTipo(1);
        usuario.setTipoUsuario(tipoUsuario);
        
        Usuario usuarioComprobar = new Usuario();
        usuarioComprobar = usuarioDAO.getUsuarioByEmail(usuario.getEmail());
        
        LoginServlet loginServlet = new LoginServlet();
        
        if (usuarioComprobar.getEmail() != null) {
            
            String error = "El usuario ya existe";
            
            request.setAttribute("error", error);
            
            request.setAttribute("registerError", error);
            
        } else {
                    
            usuarioDAO.registrarUsuario(usuario);
            
            String message = "Ya estás registrado!";
        
            request.setAttribute("message", message);
        
        }
        
        loginServlet.doGet(request, response);
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
