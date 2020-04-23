package com.nico.educarjava.entities;

import java.util.Date;

public class Profesor extends Persona {

    public Profesor(int id, String nombre, String apellido, Date fechaNacimiento, String email, String password, String foto) {
      super(id, nombre, apellido, fechaNacimiento, email, password, foto);
    }
    
}