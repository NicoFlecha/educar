package com.nico.educarjava.entities;

import java.util.Date;

public class Usuario extends Persona {
    
    private TipoUsuario tipoUsuario;
    
    public Usuario(int id, String nombre, String apellido, Date fechaNacimiento, String email, String password, String foto, TipoUsuario tipoUsuario) {
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
