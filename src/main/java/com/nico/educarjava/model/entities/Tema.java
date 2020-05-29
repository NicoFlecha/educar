package com.nico.educarjava.model.entities;

public class Tema {
    
    private int id;
    private Curso curso;
    private int numero_tema;
    private String nombre;
    private String descripcion;
    private String archivo;
    
    public Tema() {};

    public Tema(int id, Curso curso, int numero_tema, String nombre, String descripcion, String archivo) {
        setId(id);
        setCurso(curso);
        setNumero_tema(numero_tema);
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

    public int getNumero_tema() {
        return numero_tema;
    }

    public void setNumero_tema(int numero_tema) {
        this.numero_tema = numero_tema;
    }
    
    
    
}
