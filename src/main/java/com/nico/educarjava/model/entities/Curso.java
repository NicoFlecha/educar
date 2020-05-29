package com.nico.educarjava.model.entities;

import java.util.Date;
import java.time.LocalDate;

public class Curso {
    
    private int id;
    private String nombre;
    private String foto;
    private String descripcion;
    private LocalDate fechaInicio;
    private String duracion;
    private Categoria categoria;
    private int precio;


    public Curso(int id, String nombre, String descripcion, LocalDate fechaInicio, String duracion, Categoria categoria, int precio) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setFechaInicio(fechaInicio);
        setDuracion(duracion);
        setCategoria(categoria);
        setPrecio(precio);
    }

    public Curso() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
}
