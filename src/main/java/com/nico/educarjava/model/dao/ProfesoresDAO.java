package com.nico.educarjava.model.dao;

import com.nico.educarjava.model.entities.Categoria;
import com.nico.educarjava.model.entities.Profesor;
import com.nico.educarjava.model.entities.Curso;
import com.nico.educarjava.utils.ConnectionManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProfesoresDAO {
    
    public ArrayList<Profesor> getListadoProfesores() {
        
        ArrayList<Profesor> listaProfesores = new ArrayList<>();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT * " +
                    "FROM profesores";
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                
                Profesor profesor = new Profesor();
                
                profesor.setId(rs.getInt("id"));
                profesor.setNombre(rs.getString("nombre"));
                profesor.setApellido(rs.getString("apellido"));
                profesor.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
                profesor.setEmail(rs.getString("email"));
                profesor.setFoto(rs.getString("foto"));
                
                listaProfesores.add(profesor);
                
            }
            
            rs.close();
            stm.close();
            con.close();
            
        }   catch (SQLException ex) {
            System.out.println("Error al obtener los profesores");
            System.out.println(ex.getMessage());
        }
        
        return listaProfesores;
        
    }
    
    public Profesor getProfesor(int idProfesor) {
        
        Profesor profesor = new Profesor();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT * " +
                    "FROM profesores " +
                    "WHERE id = " + idProfesor;
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            rs.next();
            
            profesor.setId(rs.getInt("id"));
            profesor.setNombre(rs.getString("nombre"));
            profesor.setApellido(rs.getString("apellido"));
            profesor.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            profesor.setEmail(rs.getString("email"));
            profesor.setFoto(rs.getString("foto"));
            
        }   catch (SQLException ex) {
            System.out.println("Error al obtener al profesor");
        }
     
        return profesor;
        
    }
    
    public ArrayList<Curso> getCursos(int idProfesor) {
        
        ArrayList<Curso> listaCursos = new ArrayList<>();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT cursos.*, cursos_profesores.*, categorias.* " + 
                    "FROM cursos, cursos_profesores, categorias " +
                    "WHERE cursos.id = cursos_profesores.curso_id " +
                    "AND cursos_profesores.profesor_id = " + idProfesor + " " +
                    "AND cursos.categoria_id = categorias.id";
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                
                Curso curso = new Curso();
                
                curso.setId(rs.getInt("cursos.id"));
                curso.setNombre(rs.getString("cursos.nombre"));
                curso.setDescripcion(rs.getString("cursos.descricion"));
                curso.setDuracion(rs.getString("cursos.duracion"));
                
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categorias.id"));
                categoria.setNombre(rs.getString("categorias.nombre"));
                
                curso.setCategoria(categoria);
                curso.setPrecio(rs.getInt("cursos.precio"));
                
                listaCursos.add(curso);
                
            }
            
            rs.close();
            stm.close();
            con.close();
            
        }   catch (SQLException ex) {
            System.out.println("Error al obtener los cursos del profesor");
        }
        
        return listaCursos;
        
    }
    
    public void guardarProfesor(Profesor profesor) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            PreparedStatement stm;
            String sql;

            sql =   "INSERT INTO profesores (nombre, apellido, fecha_nacimiento, email, password, foto) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            stm = con.prepareStatement(sql);
            
            stm.setString(1, profesor.getNombre());
            stm.setString(2, profesor.getApellido());
            stm.setDate(3, Date.valueOf(profesor.getFechaNacimiento()));
            stm.setString(4, profesor.getEmail());
            stm.setString(5, profesor.getPassword());
            stm.setString(6, profesor.getFoto());
            
            stm.executeUpdate();
            
            stm.close();
            con.close();
            
        } catch(SQLException ex) {
            System.out.println("Error al guardar al profesor");
            System.out.println(ex.getMessage());
        }  
        
    }
    
    public void actualizarProfesor(Profesor profesor) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            PreparedStatement stm;
            String sql;

            sql =   "UPDATE profesores " +
                    "SET nombre = ?, apellido = ?, fecha_nacimiento = ?, foto = ? " +
                    "WHERE id = ?";

            stm = con.prepareStatement(sql);
            
            stm.setString(1, profesor.getNombre());
            stm.setString(2, profesor.getApellido());
            stm.setDate(3, Date.valueOf(profesor.getFechaNacimiento()));
            stm.setString(4, profesor.getFoto());
            stm.setInt(5, profesor.getId());
            
            stm.executeUpdate();
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al actualizar al profesor");
            System.out.println(ex.getMessage());
        }
        
        
        
    }
    
    public void eliminarProfesor(int idProfesor) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            PreparedStatement stm;
            String sql;

            sql =   "DELETE FROM profesores " +
                    "WHERE id = ?";

            stm = con.prepareStatement(sql);

            stm.setInt(1, idProfesor);
            
            stm.executeUpdate();
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al eliminar al profesor");
            System.out.println(ex.getMessage());
        }
        
    }
    
}
