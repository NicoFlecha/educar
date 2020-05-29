package com.nico.educarjava.model.dao;

import com.nico.educarjava.model.entities.Categoria;
import com.nico.educarjava.model.entities.Curso;
import com.nico.educarjava.utils.ConnectionManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CategoriasDAO {
    
    public ArrayList<Categoria> getListadoCategorias() {
        
        ArrayList<Categoria> listadoCategorias = new ArrayList<>();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT * "+
                    "FROM categorias";
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                
                Categoria categoria = new Categoria();
                
                categoria.setId(rs.getInt("id"));
                categoria.setNombre(rs.getString("nombre"));
                
                listadoCategorias.add(categoria);
                
            }
            
            rs.close();
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener las categorias");
        }
        
        return listadoCategorias;
        
    }
    
    public ArrayList<Curso> getListadoCursos(int idCategoria) {
        
        ArrayList<Curso> listaCursos = new ArrayList<>();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT cursos.*, categorias.* " +
                    "FROM cursos, categorias " +
                    "WHERE cursos.categoria_id = " + idCategoria;
            
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
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener los cursos de esta categoria");
        }
        
        return listaCursos;
                
    }
    
    public Categoria getCategoria(int idCategoria) {
        
        Categoria categoria = new Categoria();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT * " +
                    "FROM categorias " +
                    "WHERE id = " + idCategoria;
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            rs.next();
            
            categoria.setId(rs.getInt("id"));
            categoria.setNombre(rs.getString("nombre"));
            
            rs.close();
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener la categoria");
        }
        
        return categoria;
        
    }
    
    public void guardarCategoria(Categoria categoria) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            PreparedStatement stm;
            String sql;
            
            sql =   "INSERT INTO categorias(nombre) " +
                    "VALUES (?)";
            
            stm = con.prepareStatement(sql);
            
            stm.setString(1, categoria.getNombre());
            
            stm.executeUpdate();
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al guardar la categoria");
        }
        
    }
    
    public void eliminarCategoria(Categoria categoria) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            PreparedStatement stm;
            String sql;

            sql =   "DELETE FROM categorias " +
                    "WHERE id = ?";

            stm = con.prepareStatement(sql);

            stm.setInt(1, categoria.getId());

            stm.executeUpdate();
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al eliminar la categoria");
        }
    }
    
}
