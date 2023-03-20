package com.apirest.colek.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @Column(name = "rol_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer IdRol;
    
    private String nombre;

    public Rol(int idRol, String nombre) {
        IdRol = idRol;
        this.nombre = nombre;
    }
    public Rol() {
    }
    public Integer getIdRol() {
        return IdRol;
    }
    public void setIdRol(int idRol) {
        IdRol = idRol;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    


}
