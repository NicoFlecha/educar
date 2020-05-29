    package com.nico.educarjava.model.dao;

import com.nico.educarjava.model.entities.Categoria;
import com.nico.educarjava.model.entities.TipoUsuario;
import com.nico.educarjava.model.entities.Usuario;
import com.nico.educarjava.model.entities.Curso;
import com.nico.educarjava.model.entities.UsuarioCurso;
import com.nico.educarjava.model.entities.Tema;
import com.nico.educarjava.model.entities.UsuarioTema;
import com.nico.educarjava.utils.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO {
    
    public ArrayList<Usuario> getListadoUsuarios() {
        
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT usuarios.*, tipo_usuarios.* " + 
                    "FROM usuarios, tipo_usuarios " +
                    "WHERE usuarios.tipo_usuario_id = tipo_usuarios.id";
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                
                Usuario usuario = new Usuario();
                
                usuario.setId(rs.getInt("usuarios.id"));
                usuario.setNombre(rs.getString("usuarios.nombre"));
                usuario.setApellido(rs.getString("usuarios.apellido"));
                usuario.setFechaNacimiento(rs.getDate("usuarios.fecha_nacimiento").toLocalDate());
                usuario.setEmail(rs.getString("usuarios.email"));
                usuario.setFoto(rs.getString("usuarios.foto"));
                
                TipoUsuario tipoUsuario = new TipoUsuario();
                tipoUsuario.setId(rs.getInt("tipo_usuarios.id"));
                tipoUsuario.setTipo(rs.getString("tipo_usuarios.tipo"));
                
                usuario.setTipoUsuario(tipoUsuario);
                
                listaUsuarios.add(usuario);
                
            }
            
            rs.close();
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener los usuarios");
            System.out.println(ex.getMessage());
        }
        
        return listaUsuarios;
        
    }
    
    public Usuario getUsuarioById(int idUsuario) {
        
        Usuario usuario = new Usuario();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT usuarios.*, tipo_usuarios.*" +
                    "FROM usuarios, tipo_usuarios " +
                    "WHERE usuarios.id = " + idUsuario + " " +
                    "AND usuarios.tipo_usuario_id = tipo_usuarios.id";
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            rs.next();
            
            usuario.setId(rs.getInt("usuarios.id"));
            usuario.setNombre(rs.getString("usuarios.nombre"));
            usuario.setApellido(rs.getString("usuarios.apellido"));
            String fecha = String.valueOf(rs.getDate("usuarios.fecha_nacimiento"));
            if (fecha != "null") {
                usuario.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            } else {
                usuario.setFechaNacimiento(null);
            }
            usuario.setEmail(rs.getString("usuarios.email"));
            usuario.setFoto(rs.getString("usuarios.foto"));

            TipoUsuario tipoUsuario = new TipoUsuario();
            tipoUsuario.setId(rs.getInt("tipo_usuarios.id"));
            tipoUsuario.setTipo(rs.getString("tipo_usuarios.tipo"));

            usuario.setTipoUsuario(tipoUsuario);
            
            rs.close();
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener el usuario");
            System.out.println(ex.getMessage());
        }
        
        return usuario;
        
    }
    
    public Usuario getUsuarioByEmail(String email) {
        
        Usuario usuario = new Usuario();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            PreparedStatement stm;
            ResultSet rs;
            String sql;

            sql =   "SELECT * " +
                    "FROM usuarios " +
                    "WHERE email = ?";

            stm = con.prepareStatement(sql);
            
            stm.setString(1, email);
            
            rs = stm.executeQuery();

            if(rs.next()) {
                
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellido(rs.getString("apellido"));
                
                TipoUsuarioDAO tipoUsuarioDAO = new TipoUsuarioDAO();
                TipoUsuario tipoUsuario = tipoUsuarioDAO.getTipo(rs.getInt("tipo_usuario_id"));
                usuario.setTipoUsuario(tipoUsuario);
                
                usuario.setPassword(rs.getString("password"));
                
            };
            
            stm.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener el usuario");
            System.out.println(ex);
        }
        
        return usuario;
        
    }
    
    public ArrayList<Curso> getCursos(int idUsuario) {
        
        ArrayList<Curso> listaCursos = new ArrayList<>();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT cursos.*, categorias.*, usuarios_cursos.* " +
                    "FROM cursos, categorias, usuarios_cursos " +
                    "WHERE cursos.id = usuarios_cursos.curso_id " +
                    "AND usuarios_cursos.usuario_id = " + idUsuario + " " +
                    "AND cursos.categoria_id = categorias.id";
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while (rs.next()) {
                
                Curso curso = new Curso();
                
                curso.setId(rs.getInt("cursos.id"));
                curso.setNombre(rs.getString("cursos.nombre"));
                curso.setDescripcion(rs.getString("cursos.descripcion"));
                curso.setDuracion(rs.getString("cursos.duracion"));
                
                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categorias.id"));
                categoria.setNombre(rs.getString("categorias.nombre"));
                
                curso.setCategoria(categoria);
                curso.setFoto(rs.getString("cursos.foto"));
                curso.setPrecio(rs.getInt("cursos.precio"));
                
                listaCursos.add(curso);
                
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener los cursos");
            System.out.println(ex);
        }
        
        return listaCursos;
        
    }
    
    public UsuarioCurso getInfoCurso(int idUsuario, int idCurso) {
        
        UsuarioCurso infoCurso = new UsuarioCurso();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT usuarios_cursos.*, usuarios.*, tipo_usuarios.* , cursos.*, categorias.* " +
                    "FROM usuarios_cursos, usuarios, cursos, categorias " +
                    "WHERE usuarios_cursos.usuario_id = " + idUsuario + " " +
                    "AND usuarios_cursos.curso_id = " + idCurso + " " +
                    "AND cursos.categoria_id = categorias.id " +
                    "AND usuarios.tipo_usuario_id = tipo_usuarios.id";
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            rs.next();
            
            Usuario usuario = new Usuario();
            
            usuario.setId(rs.getInt("usuarios.id"));
            usuario.setNombre(rs.getString("usuarios.nombre"));
            usuario.setApellido(rs.getString("usuarios.apellido"));
            usuario.setFechaNacimiento(rs.getDate("usuarios.fecha_nacimiento").toLocalDate());
            usuario.setEmail(rs.getString("usuarios.email"));
            usuario.setFoto(rs.getString("usuarios.foto"));

            TipoUsuario tipoUsuario = new TipoUsuario();
            tipoUsuario.setId(rs.getInt("tipo_usuarios.id"));
            tipoUsuario.setTipo(rs.getString("tipo_usuarios.tipo"));

            usuario.setTipoUsuario(tipoUsuario);
            
            infoCurso.setUsuario(usuario);
            
            Curso curso = new Curso();
            
            curso.setId(idCurso);
            curso.setNombre(rs.getString("cursos.nombre"));
            curso.setDescripcion(rs.getString("cursos.descripcion"));
            curso.setFechaInicio(rs.getDate("cursos.fecha_inicio").toLocalDate());
            curso.setDuracion(rs.getString("cursos.duracion"));
            
            Categoria categoria = new Categoria();
            categoria.setId(rs.getInt("categorias.id"));
            categoria.setNombre(rs.getString("categorias.nombre"));
            
            curso.setCategoria(categoria);
            curso.setPrecio(rs.getInt("cursos.precio"));
            
            infoCurso.setCurso(curso);
            infoCurso.setPuntuacion(rs.getInt("usuarios_cursos.usuario_puntuacion"));
            infoCurso.setComentario(rs.getString("usuarios_cursos.usuario_comentario"));
            
            rs.close();
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener la relacion del usuario y el curso");
        }
        
        return infoCurso;
        
    }
    
    public ArrayList<UsuarioCurso> getListadoInfoCursos(int idUsuario) {
        
        ArrayList<UsuarioCurso> listaInfoCursos = new ArrayList<>();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT usuarios_cursos.*, usuarios.*, tipo_usuarios.* , cursos.*, categorias.* " +
                    "FROM usuarios_cursos, usuarios, cursos, categorias " +
                    "WHERE usuarios_cursos.usuario_id = " + idUsuario + " " +
                    "AND usuarios_cursos.curso_id = cursos.id " +
                    "AND cursos.categoria_id = categorias.id " +
                    "AND usuarios.tipo_usuario_id = tipo_usuarios.id";
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
            while(rs.next()) {
                
                UsuarioCurso infoCurso = new UsuarioCurso();
                
                Usuario usuario = new Usuario();
            
                usuario.setId(rs.getInt("usuarios.id"));
                usuario.setNombre(rs.getString("usuarios.nombre"));
                usuario.setApellido(rs.getString("usuarios.apellido"));
                usuario.setFechaNacimiento(rs.getDate("usuarios.fecha_nacimiento").toLocalDate());
                usuario.setEmail(rs.getString("usuarios.email"));
                usuario.setFoto(rs.getString("usuarios.foto"));

                TipoUsuario tipoUsuario = new TipoUsuario();
                tipoUsuario.setId(rs.getInt("tipo_usuarios.id"));
                tipoUsuario.setTipo(rs.getString("tipo_usuarios.tipo"));

                usuario.setTipoUsuario(tipoUsuario);

                infoCurso.setUsuario(usuario);

                Curso curso = new Curso();

                curso.setId(rs.getInt("cursos.id"));
                curso.setNombre(rs.getString("cursos.nombre"));
                curso.setDescripcion(rs.getString("cursos.descripcion"));
                curso.setFechaInicio(rs.getDate("cursos.fecha_inicio").toLocalDate());
                curso.setDuracion(rs.getString("cursos.duracion"));

                Categoria categoria = new Categoria();
                categoria.setId(rs.getInt("categorias.id"));
                categoria.setNombre(rs.getString("categorias.nombre"));

                curso.setCategoria(categoria);
                curso.setPrecio(rs.getInt("cursos.precio"));

                infoCurso.setCurso(curso);
                infoCurso.setPuntuacion(rs.getInt("usuarios_cursos.usuario_puntuacion"));
                infoCurso.setComentario(rs.getString("usuarios_cursos.usuario_comentario"));

                listaInfoCursos.add(infoCurso);
                
            }
            
            rs.close();
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener los comentarios del usuario");
        }
        
        return listaInfoCursos;
        
    }
    
    public UsuarioTema getInfoTema(int idTema, int idUsuario) {
        
        UsuarioTema infoTema = new UsuarioTema();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();

            Statement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT temas_usuarios.*, temas.*, cursos.*, categorias.*, usuarios.*, tipo_usuarios.* " +
                    "FROM temas_usuarios, temas, cursos, categorias, usuarios, tipo_usuarios " +
                    "WHERE temas_usuarios.tema_id = temas.id " +
                    "AND temas_usuarios.usuario_id = usuarios.id " +
                    "AND usuarios.tipo_usuario_id = tipo_usuarios.id " +
                    "AND cursos.categoria_id = categorias.id" +
                    "AND temas.curso_id = cursos.id";
            
            stm = con.createStatement();
            rs = stm.executeQuery(sql);
            
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
            
            infoTema.setTema(tema);
            
            Usuario usuario = new Usuario();
            
            usuario.setId(rs.getInt("usuarios.id"));
            usuario.setNombre(rs.getString("usuarios.nombre"));
            usuario.setApellido(rs.getString("usuarios.apellido"));
            usuario.setFechaNacimiento(rs.getDate("usuarios.fecha_nacimiento").toLocalDate());
            usuario.setEmail(rs.getString("usuarios.email"));
            usuario.setFoto(rs.getString("usuarios.foto"));

            TipoUsuario tipoUsuario = new TipoUsuario();
            tipoUsuario.setId(rs.getInt("tipo_usuarios.id"));
            tipoUsuario.setTipo(rs.getString("tipo_usuarios.tipo"));

            usuario.setTipoUsuario(tipoUsuario);
            
            infoTema.setUsuario(usuario);
            infoTema.setNota(rs.getInt("temas_usuarios.nota"));
            infoTema.setProfesor_comentario(rs.getString("temas_usuarios.profesor_comentario"));
            
            
            
        } catch (SQLException ex) {
            System.out.println("Error al obtener la relacion del usuario y el tema");
        }
        
        return infoTema;
        
    }
    
    public void registrarUsuario(Usuario usuario) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
                        
            PreparedStatement stm;
            String sql;
            
            sql =   "INSERT INTO usuarios(nombre, apellido, email, password, tipo_usuario_id) " +
                    "VALUES (?, ?, ?, ?, ?)";
            
            stm = con.prepareStatement(sql);
            
            stm.setString(1, usuario.getNombre());
            stm.setString(2, usuario.getApellido());    
            stm.setString(3, usuario.getEmail());           
            stm.setString(4, usuario.getPassword());       
            stm.setInt(5, usuario.getTipoUsuario().getId());
            
            stm.executeUpdate();
            System.out.println("Ejecuté la consulta");
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al guardar al usuario");
            System.out.println(ex);
        }
        
    }
    
    public void borrarUsuario(Usuario usuario) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
                        
            PreparedStatement stm;
            String sql;
            
            sql =   "DELETE FROM usuarios " +
                    "WHERE id = ?";
            
            stm = con.prepareStatement(sql);
            
            stm.setInt(1, usuario.getId());
            
            stm.executeUpdate();
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al borrar al usuario");
        }
        
    }
    
    public void actualizarUsuario(Usuario usuario) {
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            PreparedStatement stm;
            String sql;
            
            sql =   "UPDATE usuarios " +
                    "SET nombre = ?, apellido = ?, fecha_nacimiento = ?, foto = ? " +
                    "WHERE id = ?";
            
            stm = con.prepareStatement(sql);
            
            stm.setString(1, usuario.getNombre());
            stm.setString(2, usuario.getApellido());
            stm.setDate(3, java.sql.Date.valueOf(usuario.getFechaNacimiento()));
            stm.setString(4, usuario.getFoto());
            stm.setInt(5, usuario.getId());
            
            stm.executeUpdate();
            
            stm.close();
            con.close();
            
        } catch (SQLException ex) {
            System.out.println("Error al actualizar la info del usuario");
            System.out.println(ex.getMessage());
        }
        
    }
    
    public ArrayList<Usuario> buscarUsuario(String busqueda) {
        
        ArrayList<Usuario> resultado = new ArrayList<>();
        
        try {
            
            ConnectionManager connectionManager = new ConnectionManager();
            Connection con = connectionManager.getConnection();
            
            PreparedStatement stm;
            ResultSet rs;
            String sql;
            
            sql =   "SELECT usuarios.*, tipo_usuarios.* " +
                    "FROM usuarios, tipo_usuarios " +
                    "WHERE usuarios.tipo_usuario_id = tipo_usuarios.id " +
                    "AND (usuarios.nombre LIKE '%' ? '%'" +
                    "OR usuarios.apellido LIKE '%' ? '%' " +
                    "OR usuarios.email LIKE '%' ? '%')";
            
            stm = con.prepareStatement(sql);
            
            stm.setString(1, busqueda);
            stm.setString(2, busqueda);
            stm.setString(3, busqueda);
            
            rs = stm.executeQuery();
            
            while(rs.next()) {
                
                Usuario usuario = new Usuario();
                
                usuario.setId(rs.getInt("usuarios.id"));
                usuario.setNombre(rs.getString("usuarios.nombre"));
                usuario.setApellido(rs.getString("usuarios.apellido"));
                usuario.setEmail(rs.getString("usuarios.email"));
                usuario.setFechaNacimiento(rs.getDate("usuarios.fecha_nacimiento").toLocalDate());
                usuario.setFoto(rs.getString("usuarios.foto"));
                
                TipoUsuario tipoUsuario = new TipoUsuario();
                tipoUsuario.setId(rs.getInt("tipo_usuarios.id"));
                tipoUsuario.setTipo(rs.getString("tipo_usuarios.tipo"));
                
                usuario.setTipoUsuario(tipoUsuario);
                
                resultado.add(usuario);
                
            }
            
        } catch (SQLException ex) {
            System.out.println("Error al buscar el usuario");
            System.out.println(ex.getMessage());
        }
        
        return resultado;
    }
    
}
