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
@Table(name="notificaciones")
public class Notificacion {
    @Id 
    @Column(name = "notificacion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fecha_creacion;
    private String mensaje;
    private String link;

    @OneToMany(mappedBy = "notificacion")
    private Set<Usuarios_Notificaciones> remitentes = new HashSet<>();

    
    public Notificacion(Long id, String fecha_creacion, String mensaje, String link) {
        this.id = id;
        this.fecha_creacion = fecha_creacion;
        this.mensaje = mensaje;
        this.link = link;
    }
    public Notificacion() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFecha_creacion() {
        return fecha_creacion;
    }
    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }

    
}
