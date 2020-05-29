package com.nico.educarjava.model.entities;

import java.time.LocalDate;

public class Profesor extends Persona {

    public Profesor(int id, String nombre, String apellido, LocalDate fechaNacimiento, String email, String password, String foto) {
      super(id, nombre, apellido, fechaNacimiento, email, password, foto);
    }
    
    public Profesor() {
        
    }
    
}