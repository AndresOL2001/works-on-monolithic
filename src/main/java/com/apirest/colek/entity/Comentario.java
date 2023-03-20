package com.apirest.colek.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="comentarios")
public class Comentario {
    @Id
    @Column(name = "comentario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fechaCreacion;

    private String contenido;
    
    private String nombreUsuarioActual;

    @OneToMany(mappedBy = "comentario")
    private Set<Usuarios_Comentarios> usuarios_Comentarios = new HashSet<>();

    public Comentario(Long id, String fechaCreacion, String contenido, String nombreUsuarioActual) {
        this.id = id;
        this.fechaCreacion = fechaCreacion;
        this.contenido = contenido;
        this.nombreUsuarioActual = nombreUsuarioActual;
    }

    public Comentario() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getNombreUsuarioActual() {
        return nombreUsuarioActual;
    }

    public void setNombreUsuarioActual(String nombreUsuarioActual) {
        this.nombreUsuarioActual = nombreUsuarioActual;
    }

    
}
