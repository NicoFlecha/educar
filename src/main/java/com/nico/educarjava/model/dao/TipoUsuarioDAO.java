package com.nico.educarjava.model.dao;

import com.nico.educarjava.model.entities.TipoUsuario;
import com.nico.educarjava.utils.ConnectionManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TipoUsuarioDAO {
    
    public TipoUsuario getTipo(int idTipoUsuario) {
        
        TipoUsuario tipoUsuario = new TipoUsuario();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT * " +
                    "FROM tipo_usuarios " +
                    "WHERE id = " + idTipoUsuario;
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            rs.next();
            
            tipoUsuario.setId(rs.getInt("id"));
            tipoUsuario.setTipo(rs.getString("tipo"));
            
            stm.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener el tipo de usuario");
        }
        
        return tipoUsuario;
        
    }
    
}
