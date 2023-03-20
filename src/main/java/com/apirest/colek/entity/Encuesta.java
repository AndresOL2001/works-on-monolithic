package com.apirest.colek.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="encuestas")
public class Encuesta {
    @Id
    @Column(name = "encuesta_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEncuesta;

    private String titulo;

    private int numeroPreguntas;

    @ManyToOne(fetch=FetchType.EAGER)//Si eliminamos el siniestro todas sus documentos igual se eliminan
    @JoinColumn(name = "actividad_id")
    private Actividad idActividad;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="idEncuesta",cascade=CascadeType.ALL)//Si eliminamos el siniestro todas sus documentos igual se eliminan
    private List<Pregunta> preguntas;
    
    public Encuesta(Long idEncuesta, String titulo, int numeroPreguntas, Long idTarea) {
        this.idEncuesta = idEncuesta;
        this.titulo = titulo;
        this.numeroPreguntas = numeroPreguntas;
    }


    public Encuesta() {
    }

    
    public Long getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Long idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumeroPreguntas() {
        return numeroPreguntas;
    }

    public void setNumeroPreguntas(int numeroPreguntas) {
        this.numeroPreguntas = numeroPreguntas;
    }
    
}
