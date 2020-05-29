package com.nico.educarjava.model.dao;

import com.nico.educarjava.model.entities.Categoria;
import java.util.ArrayList;
import com.nico.educarjava.model.entities.Curso;
import com.nico.educarjava.model.entities.Profesor;
import com.nico.educarjava.model.entities.TipoUsuario;
import com.nico.educarjava.model.entities.Usuario;
import com.nico.educarjava.model.entities.UsuarioCurso;
import com.nico.educarjava.utils.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CursosDAO {
    
    public ArrayList<Curso> getListadoCursos() {
        
        ArrayList<Curso> listaCursos = new ArrayList<>();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            Statement stm;
            ResultSet rs;
            String sql;

            sql =   "SELECT cursos.*,categorias.* " +
                    "FROM cursos, categorias " +
                    "WHERE cursos.categoria_id = categorias.id";

            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while (rs.next()) {
                
                
                Curso curso = new Curso();
                curso.setId(rs.getInt("cursos.id"));
                curso.setNombre(rs.getString("cursos.nombre"));
                curso.setFoto(rs.getString("cursos.foto"));
                curso.setDescripcion(rs.getString("cursos.descripcion"));
                curso.setFechaInicio(rs.getDate("cursos.fecha_inicio").toLocalDate());
                curso.setDuracion(rs.getString("cursos.duracion"));
                
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categorias.id"));
                categoria.setNombre(rs.getString("categorias.nombre"));
                
                curso.setCategoria(categoria);
                curso.setPrecio(rs.getInt("cursos.precio"));
                
                listaCursos.add(curso);
                
            }
            
            stm.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener los cursos");
        }
        
        return listaCursos;
        
    }
    
    public ArrayList<Profesor> getListadoProfesores(Curso curso) {
        
        ArrayList<Profesor> listaProfesores = new ArrayList<>();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            Statement stm;
            ResultSet rs;
            String sql;
            
            int cursoId = curso.getId();
            
            sql =   "SELECT profesores.*, cursos_profesores.*, cursos.id " +
                    "FROM profesores, cursos_profesores, cursos " +
                    "WHERE profesores.id = cursos_profesores.profesor_id " +
                    "AND cursos_profesores.curso_id = " + cursoId;
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                
                Profesor profesor = new Profesor();
                
                profesor.setId(rs.getInt("profesores.id"));
                profesor.setNombre(rs.getString("profesores.nombre"));
                profesor.setApellido(rs.getString("profesores.apellido"));
                profesor.setFechaNacimiento(rs.getDate("profesores.fecha_nacimiento").toLocalDate());
                profesor.setEmail(rs.getString("profesores.email"));
                profesor.setFoto(rs.getString("profesores.foto"));
                
                listaProfesores.add(profesor);
                
            }
            
            rs.close();
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener los profesores del curso");
        }
     
        return listaProfesores;
        
    }
    
    public Curso getCurso(int idCurso) {
        
        Curso curso = new Curso();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT cursos.*, categorias.* " + 
                    "FROM cursos, categorias " +
                    "WHERE cursos.id = " + idCurso + " " +
                    "AND cursos.categoria_id = categorias.id";
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            rs.next();
            
            curso.setId(idCurso);
            curso.setNombre(rs.getString("cursos.nombre"));
            curso.setFoto(rs.getString("cursos.foto"));;
            curso.setDescripcion(rs.getString("cursos.descripcion"));
            curso.setFechaInicio(rs.getDate("cursos.fecha_inicio").toLocalDate());
            curso.setDuracion(rs.getString("cursos.duracion"));
            
            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("categorias.id"));
            categoria.setNombre(rs.getString("categorias.nombre"));
            
            curso.setCategoria(categoria);
            curso.setPrecio(rs.getInt("cursos.precio"));
            
            rs.close();
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener el curso");
        }
        
        return curso;
        
    }
    
    public ArrayList<UsuarioCurso> getInfoUsuarios(int idCurso) {
        
        ArrayList<UsuarioCurso> listaInfoUsuarios = new ArrayList<>();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT usuarios_cursos.*, cursos.*, categorias.*, usuarios.*, tipo_usuarios.* " +
                    "FROM usuarios_cursos, cursos, categorias, usuarios, tipo_usuarios " +
                    "WHERE usuarios_cursos.curso_id = " + idCurso + " " +
                    "AND cursos.id = usuarios_cursos.curso_id " +
                    "AND cursos.categoria_id = categorias.id " +
                    "AND usuarios.id = usuarios_cursos.usuario_id " +
                    "AND usuarios.tipo_usuario_id = tipo_usuarios.id";
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                
                UsuarioCurso infoUsuario = new UsuarioCurso();
                
                Usuario usuario = new Usuario();
            
                usuario.setId(rs.getInt("usuarios.id"));
                usuario.setNombre(rs.getString("usuarios.nombre"));
                usuario.setApellido(rs.getString("usuarios.apellido"));
                //usuario.setFechaNacimiento(rs.getDate("usuarios.fecha_nacimiento").toLocalDate());
                usuario.setEmail(rs.getString("usuarios.email"));
                usuario.setFoto(rs.getString("usuarios.foto"));

                TipoUsuario tipoUsuario = new TipoUsuario();
                tipoUsuario.setId(rs.getInt("tipo_usuarios.id"));
                tipoUsuario.setTipo(rs.getString("tipo_usuarios.tipo"));

                usuario.setTipoUsuario(tipoUsuario);

                infoUsuario.setUsuario(usuario);

                Curso curso = new Curso();

                curso.setId(rs.getInt("cursos.id"));
                curso.setNombre(rs.getString("cursos.nombre"));
                curso.setFoto(rs.getString("cursos.foto"));
                curso.setDescripcion(rs.getString("cursos.descripcion"));
                curso.setFechaInicio(rs.getDate("cursos.fecha_inicio").toLocalDate());
                curso.setDuracion(rs.getString("cursos.duracion"));

                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categorias.id"));
                categoria.setNombre(rs.getString("categorias.nombre"));

                curso.setCategoria(categoria);
                curso.setPrecio(rs.getInt("cursos.precio"));

                infoUsuario.setCurso(curso);
                infoUsuario.setPuntuacion(rs.getInt("usuarios_cursos.usuario_puntuacion"));
                infoUsuario.setComentario(rs.getString("usuarios_cursos.usuario_comentario"));

                listaInfoUsuarios.add(infoUsuario);
                
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener la relacion del curso con los usuarios");
            System.out.println(ex);
        }
        
        return listaInfoUsuarios;
    }
    
    public int guardarCurso(Curso curso) {
        
        int cursoId = 0;
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            PreparedStatement stm;
            ResultSet rs;
            String sql;

            sql =   "INSERT INTO cursos(nombre, foto, descripcion, fecha_inicio, duracion, categoria_id, precio) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            stm.setString(1, curso.getNombre());
            stm.setString(2, curso.getFoto());
            stm.setString(3, curso.getDescripcion());

            stm.setDate(4, java.sql.Date.valueOf(curso.getFechaInicio()));

            stm.setString(5, curso.getDuracion());
            stm.setInt(6, curso.getCategoria().getId());
            stm.setInt(7, curso.getPrecio());
            
            stm.executeUpdate();
            rs = stm.getGeneratedKeys();

            rs.next();
            
            cursoId = Integer.parseInt(rs.getString(1));
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al guardar el curso");
            System.out.println(ex);
        }
        
        return cursoId;
        
    }
    
    public void actualizarCurso(Curso curso, int idCurso) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            PreparedStatement stm;
            String sql;

            sql =   "UPDATE cursos " + 
                    "SET nombre = ?, foto= ?, descripcion = ?, fecha_inicio = ?, duracion = ?, categoria_id = ?, precio = ? " + 
                    "WHERE id = ?";

            stm = con.prepareStatement(sql);
            
            stm.setString(1, curso.getNombre());
            stm.setString(2, curso.getFoto());
            stm.setString(3, curso.getDescripcion());

            stm.setDate(4, java.sql.Date.valueOf(curso.getFechaInicio()));

            stm.setString(5, curso.getDuracion());
            stm.setInt(6, curso.getCategoria().getId());
            stm.setInt(7, curso.getPrecio());
            stm.setInt(8, idCurso);
            
            stm.executeUpdate();
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al guardar el curso");
            System.out.println(ex);
        }
        
    }
    
    public void eliminarCurso(int idCurso) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            PreparedStatement stm;
            String sql;

            sql =   "DELETE FROM cursos " +
                    "WHERE id = ?";

            stm = con.prepareStatement(sql);
            
            stm.setInt(1, idCurso);
            
            stm.executeUpdate();
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al eliminar el curso");
        }
        
    }
    
    public void inscribirUsuario(int usuarioId, int cursoId) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            PreparedStatement stm;
            String sql;

            sql =   "INSERT INTO usuarios_cursos (curso_id, usuario_id) " +
                    "VALUES (?, ?)";

            stm = con.prepareStatement(sql);
            
            stm.setInt(1, cursoId);
            stm.setInt(2, usuarioId);
            
            stm.executeUpdate();
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al inscribir al usuario en el curso");
        }
        
    }
    
    public boolean estaInscripto(int usuarioId, int cursoId) {
        
        boolean esta = false;
     
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            PreparedStatement stm;
            ResultSet rs;
            String sql;
            
            
            sql =   "SELECT * " +
                    "FROM usuarios_cursos " +
                    "WHERE usuario_id = ? " +
                    "AND curso_id = ?";
            
            stm = con.prepareStatement(sql);
            
            stm.setInt(1, usuarioId);
            stm.setInt(2, cursoId);
            
            rs = stm.executeQuery();
            
            if(rs.next()) {
                esta = true;
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al verificar si el usuario está inscripto en el curso");
            System.out.println(ex);
        }
            
        return esta;
        
    }
    
    public void guardarPuntuacion(int cursoId, int usuarioId, String comentario) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            PreparedStatement stm;
            String sql;
            
            
            sql =   "UPDATE usuarios_cursos " +
                    "SET usuario_comentario = ? " +
                    "WHERE curso_id = ? " +
                    "AND usuario_id = ?";
            
            stm = con.prepareStatement(sql);
            
            stm.setString(1, comentario);
            stm.setInt(2, cursoId);
            stm.setInt(3, usuarioId);
            
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println("Error al guardar la puntuacion");
            System.out.println(ex);
        }
        
    }
    
    public void agregarProfesor(int profesorId, int cursoId) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            PreparedStatement stm;
            String sql;
            
            sql =   "INSERT INTO cursos_profesores(curso_id, profesor_id) " +
                    "VALUES (?, ?)";
            
            stm = con.prepareStatement(sql);
            
            stm.setInt(1, cursoId);
            stm.setInt(2, profesorId);
            
            stm.executeUpdate();
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al asignar el profesor al curso");
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void eliminarProfesor(int profesorId, int cursoId) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            PreparedStatement stm;
            String sql;
            
            sql =   "DELETE FROM cursos_profesores " +
                    "WHERE curso_id = ? " +
                    "AND profesor_id = ?";
            
            stm = con.prepareStatement(sql);
            
            stm.setInt(1, cursoId);
            stm.setInt(2, profesorId);
            
            stm.executeUpdate();
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al eliminar al profesor del curso");
            System.out.println(ex.getMessage());
        }
        
    }
    
    public boolean esProfesorDelCurso(int profesorId, int cursoId) {
       
        boolean esProfesor = false;
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            PreparedStatement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT * " +
                    "FROM cursos_profesores " +
                    "WHERE curso_id = ? " +
                    "AND profesor_id = ?";
            
            stm = con.prepareStatement(sql);
            
            stm.setInt(1, cursoId);
            stm.setInt(2, profesorId);
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                
                esProfesor = true;
                
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al comprobar sí es profesor del curso");
            System.out.println(ex.getMessage());
        }
        
        return esProfesor;
        
    }
    
}
