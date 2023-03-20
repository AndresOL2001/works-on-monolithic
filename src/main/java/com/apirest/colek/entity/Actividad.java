package com.apirest.colek.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="actividades")
public class Actividad {
    @Id
    @Column(name = "actividad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividad;

    private String fechaCreacion;

    private String tipo;

    private String duracion;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="idActividad",cascade=CascadeType.ALL)//Si eliminamos el siniestro todas sus documentos igual se eliminan
    private List<Archivo> archivos;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="idActividad",cascade=CascadeType.ALL)//Si eliminamos el siniestro todas sus documentos igual se eliminan
    private List<Video> videos;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="idActividad",cascade=CascadeType.ALL)//Si eliminamos el siniestro todas sus documentos igual se eliminan
    private List<Encuesta> encuestas;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="idActividad",cascade=CascadeType.ALL)//Si eliminamos el siniestro todas sus documentos igual se eliminan
    private List<Infografia> infografias;

    @OneToMany(mappedBy = "actividad")
    private Set<Usuarios_Actividades> actividades_usuarios = new HashSet<>();

    @OneToMany(mappedBy = "actividad")
    private Set<Usuarios_Comentarios> actividades_comentarios = new HashSet<>();
   
    public Actividad() {
    }

    

    public Actividad(Long idActividad, String fechaCreacion, String tipo, String duracion) {
        this.idActividad = idActividad;
        this.fechaCreacion = fechaCreacion;
        this.tipo = tipo;
        this.duracion = duracion;
    }



    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Long getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Long idActividad) {
        this.idActividad = idActividad;
    }



    public String getTipo() {
        return tipo;
    }



    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    

}
