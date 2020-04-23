package com.nico.educarjava.entities;

public class Tema {
    
    private int id;
    private Curso curso;
    private String nombre;
    private String descripcion;
    private String archivo;

    public Tema(int id, Curso curso, String nombre, String descripcion, String archivo) {
        setId(id);
        setCurso(curso);
        setNombre(nombre);
        setDescripcion(descripcion);
        setArchivo(archivo);
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }
    
}
