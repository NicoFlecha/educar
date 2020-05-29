package com.nico.educarjava.model.dao;

import com.nico.educarjava.model.entities.Tema;
import com.nico.educarjava.model.entities.Curso;
import com.nico.educarjava.model.entities.Categoria;
import com.nico.educarjava.utils.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TemasDAO {
    
    public ArrayList<Tema> getListaTemas() {
        
        ArrayList<Tema> listaTemas = new ArrayList<>();

        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT temas.*, cursos.*, categorias.* " +
                    "FROM temas, cursos, categorias " +
                    "WHERE temas.curso_id = cursos.id " +
                    "AND categorias.id = cursos.categoria_id";
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                
                Tema tema = new Tema();
                
                tema.setId(rs.getInt("temas.id"));
                
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
                
                tema.setCurso(curso);
                tema.setNumero_tema(rs.getInt("temas.numero_tema"));
                tema.setNombre(rs.getString("temas.nombre"));
                tema.setDescripcion(rs.getString("temas.descripcion"));
                tema.setArchivo(rs.getString("temas.archivo"));
                
                listaTemas.add(tema);
                
            }
            
            rs.close();
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener los temas");
        }
    
        return listaTemas;
        
    }
    
    public Tema getTemaById(int idTema) {
        
        Tema tema = new Tema();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT temas.*, cursos.* " +
                    "FROM temas, cursos " +
                    "WHERE cursos.id = temas.curso_id " +
                    "AND temas.id = " + idTema;
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            rs.next();
            
            tema.setId(rs.getInt("temas.id"));
            
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

            tema.setCurso(curso);
            tema.setNumero_tema(rs.getInt("temas.numero_tema"));
            tema.setNombre(rs.getString("temas.nombre"));
            tema.setDescripcion(rs.getString("temas.descripcion"));
            tema.setArchivo(rs.getString("temas.archivo"));
            
            rs.close();
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener el tema");
        }
        
        return tema;
        
    }
    
    public ArrayList<Tema> getListadoTemasByCurso(int idCurso) {
        
        ArrayList<Tema> listaTemas = new ArrayList<>();

        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT temas.*, cursos.*, categorias.* " +
                    "FROM temas, cursos, categorias " +
                    "WHERE temas.curso_id = " + idCurso + " " +
                    "AND temas.curso_id = cursos.id " +
                    "AND categorias.id = cursos.categoria_id";
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                
                Tema tema = new Tema();
                
                tema.setId(rs.getInt("temas.id"));
                
                Curso curso = new Curso();
                curso.setId(rs.getInt("cursos.id"));
                curso.setNombre(rs.getString("cursos.nombre"));
                curso.setDescripcion(rs.getString("cursos.descripcion"));
                curso.setDuracion(rs.getString("cursos.duracion"));
                
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categorias.id"));
                categoria.setNombre(rs.getString("categorias.nombre"));
                
                curso.setCategoria(categoria);
                curso.setPrecio(rs.getInt("cursos.precio"));
                
                tema.setCurso(curso);
                tema.setNumero_tema(rs.getInt("temas.numero_tema"));
                tema.setNombre(rs.getString("temas.nombre"));
                tema.setDescripcion(rs.getString("temas.descripcion"));
                tema.setArchivo(rs.getString("temas.archivo"));
                
                listaTemas.add(tema);
                
            }
            
            rs.close();
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener los temas del curso");
            System.out.println(ex);
        }
    
        return listaTemas;
        
    }
    
    public Tema getTemaByNumero(int numeroTema, int idCurso) {
        
        Tema tema = new Tema();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            PreparedStatement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT temas.*, cursos.* " +
                    "FROM temas, cursos " +
                    "WHERE temas.curso_id = ? " +
                    "AND cursos.id = temas.curso_id" +
                    "AND temas.id = ?";
            
            stm = con.prepareStatement(sql);
            
            stm.setInt(1, idCurso);
            stm.setInt(2, numeroTema);
            
            rs = stm.executeQuery(sql);
            
            rs.next();
            
            tema.setId(rs.getInt("temas.id"));
            
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

            tema.setCurso(curso);
            tema.setNumero_tema(rs.getInt("temas.numero_tema"));
            tema.setNombre(rs.getString("temas.nombre"));
            tema.setDescripcion(rs.getString("temas.descripcion"));
            tema.setArchivo(rs.getString("temas.archivo"));
            
            rs.close();
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener el tema");
        }
        
        return tema;
        
    }
    
    public void guardarTema(Tema tema) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            PreparedStatement stm;
            String sql;
        
            sql =   "INSERT INTO temas (curso_id, numero_tema, nombre, descripcion, archivo) " + 
                    "VALUES (?, ?, ?, ?, ?)";
            
            stm = con.prepareStatement(sql);
            
            stm.setInt(1, tema.getCurso().getId());
            stm.setInt(2, tema.getNumero_tema());
            stm.setString(3, tema.getNombre());
            stm.setString(4, tema.getDescripcion());
            stm.setString(5, tema.getArchivo());
            
            stm.executeUpdate();

            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al guardar el tema");
            System.out.println(ex);
        }
        
    }
    
    public void eliminarTema(int idTema) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            PreparedStatement stm;
            String sql;
        
            sql =   "DELETE FROM temas " + 
                    "WHERE id = ?";
            
            stm = con.prepareStatement(sql);
            
            stm.setInt(1, idTema);
            
            stm.executeUpdate();

            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al guardar el tema");
            System.out.println(ex.getMessage());
        }
        
    }
                
}
