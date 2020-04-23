package com.nico.educarjava.entities;

import java.util.Date;

public class Curso {
    
    private int id;
    private String nombre;
    private String descripcion;
    private Date fechaInicio;
    private String duracion;
    private Categoria categoria;
    private int precio;

    public Curso(int id, String nombre, String descripcion, Date fechaInicio, String duracion, Categoria categoria, int precio) {
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setFechaInicio(fechaInicio);
        setDuracion(duracion);
        setCategoria(categoria);
        setPrecio(precio);
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
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
