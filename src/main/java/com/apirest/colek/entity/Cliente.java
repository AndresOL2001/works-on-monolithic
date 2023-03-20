package com.apirest.colek.entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="clientes")
public class Cliente {
    @Id
    @Column(name = "cliente_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;

    private String color1;

    private String color2;

    private String logo;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="idCliente",cascade=CascadeType.ALL)//Si eliminamos el siniestro todas sus documentos igual se eliminan
    private List<Empleado> empleados;

    public Cliente() {
    }

    public Cliente(Long id, String nombre, String color1, String color2, String logo, int verificacion) {
        Id = id;
        this.nombre = nombre;
        this.color1 = color1;
        this.color2 = color2;
        this.logo = logo;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }



    public Long getId() {
        return Id;
    }



    public void setId(Long id) {
        Id = id;
    }

    
}
