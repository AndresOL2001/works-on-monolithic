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
@Table(name="archivos")
public class Archivo {
    @Id
    @Column(name = "archivo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArchivo;
    private String titulo;
    private String extension;
    private String url;
    @ManyToOne(fetch=FetchType.EAGER)//Si eliminamos el siniestro todas sus documentos igual se eliminan
    @JoinColumn(name = "actividad_id")
    private Actividad idActividad;
    
    
    public Archivo() {
    }
    
    public Archivo(Long idArchivo,String titulo, String extension, String url) {
        this.idArchivo = idArchivo;
        this.titulo = titulo;
        this.extension = extension;
        this.url = url;
    }



    public Long getIdArchivo() {
        return idArchivo;
    }
    public void setIdArchivo(Long idArchivo) {
        this.idArchivo = idArchivo;
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
