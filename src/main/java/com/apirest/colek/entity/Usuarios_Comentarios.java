package com.apirest.colek.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuarios_comentarios") 
public class Usuarios_Comentarios {
    @Id
    @Column(name = "usuario_comentario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name="comentario_id")
    private Comentario comentario;

    @ManyToOne
    @JoinColumn(name="actividad_id")
    private Actividad actividad;

    public Usuarios_Comentarios(Long id, Usuario usuario, Comentario comentario, Actividad actividad) {
        this.id = id;
        this.usuario = usuario;
        this.comentario = comentario;
        this.actividad = actividad;
    }

    public Usuarios_Comentarios() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comentario getComentario() {
        return comentario;
    }

    public void setComentario(Comentario comentario) {
        this.comentario = comentario;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

   

}
