package com.apirest.colek.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="infografias")
public class Infografia {
    @Id
    @Column(name = "infografia_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInfografia;
    private String titulo;
    private String extension;
    private String url;
    @ManyToOne(fetch=FetchType.EAGER)//Si eliminamos el siniestro todas sus documentos igual se eliminan
    @JoinColumn(name = "actividad_id")
    private Actividad idActividad;
   
    public Infografia(Long idInfografia, String titulo, String extension, String url) {
        this.idInfografia = idInfografia;
        this.titulo = titulo;
        this.extension = extension;
        this.url = url;
    }
    public Infografia() {
    }
    public Long getIdInfografia() {
        return idInfografia;
    }
    public void setIdInfografia(Long idInfografia) {
        this.idInfografia = idInfografia;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getExtension() {
        return extension;
    }
    public void setExtension(String extension) {
        this.extension = extension;
    }

    
}
