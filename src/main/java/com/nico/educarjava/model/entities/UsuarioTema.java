package com.nico.educarjava.model.entities;

public class UsuarioTema {

    private Tema tema;
    private Usuario usuario;
    private int nota;
    private String profesor_comentario;
    
    public UsuarioTema() {}

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getProfesor_comentario() {
        return profesor_comentario;
    }

    public void setProfesor_comentario(String profesor_comentario) {
        this.profesor_comentario = profesor_comentario;
    }
    
    

}



