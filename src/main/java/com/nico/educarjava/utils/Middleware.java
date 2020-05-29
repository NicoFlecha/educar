
package com.nico.educarjava.utils;

import com.nico.educarjava.model.entities.Usuario;
import com.nico.educarjava.servlets.CursosServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Middleware {
    
    public boolean estaLogeado(HttpServletRequest request, HttpServletResponse response) {
        
        boolean resultado = false;
        
        HttpSession session = request.getSession();
        Usuario usuario = null;

        try {
            usuario = (Usuario) session.getAttribute("user");
            
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }
        
        if(usuario != null) {            
            resultado = true;
        }
        
        return resultado;
        
    }
    
    public boolean esAdmin(HttpServletRequest request, HttpServletResponse response) {
        
        boolean resultado = false;
        
        HttpSession session = request.getSession();
        Usuario usuario = null;
        
        try {
            usuario = (Usuario) session.getAttribute("user");
            
        } catch (NullPointerException ex) {
            System.out.println(ex);
        }
        
        if(usuario != null && usuario.getTipoUsuario().getId() == 2) {            
            resultado = true;
        }
        
        return resultado;
        
    }
    
}
