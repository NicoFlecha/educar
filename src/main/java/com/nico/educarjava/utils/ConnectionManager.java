package com.nico.educarjava.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    
    public Connection getConnection() {
        String user     = "root";
        String password = "";
        String bd       = "educar_db";
        String host     = "localhost";
        String port     = "3306";
        String timeZone = "UTC";
    
        String url;
        
        Connection con = null;
        
        url = "jdbc:mysql://" + host + ":" + port + "/" + bd + "?user=" + user + "&password=" + password + "&serverTimezone=" + timeZone;
        
         try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            System.out.println("Driver cargado correctamente!");
            
            con = DriverManager.getConnection(url);
            
            System.out.println("Conexión exitosa a la BBDD");
            
        } catch (ClassNotFoundException ex) {
            // Logger.getLogger(Intro.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error al cargar el driver de MySQL:" + ex.getMessage());
            
        } catch (SQLException ex) {

            System.out.println("Error al conectar con la BBDD: " + ex.getMessage());
            
        }
        
        return con;
        
    }
    
}