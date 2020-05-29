package com.nico.educarjava.model.entities;

import java.time.LocalDate;

public class Usuario extends Persona {
    
    private TipoUsuario tipoUsuario;
    
    public Usuario() {
        
    }
    
    public Usuario(int id, String nombre, String apellido, LocalDate fechaNacimiento, String email, String password, String foto, TipoUsuario tipoUsuario) {
        super(id, nombre, apellido, fechaNacimiento, email, password, foto);
        setTipoUsuario(tipoUsuario);
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
    
    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
}
