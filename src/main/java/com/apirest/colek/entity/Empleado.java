package com.apirest.colek.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="empleados",indexes = @Index(columnList = "empleado_id,nombre"))
public class Empleado {
    @Id
    @Column(name = "empleado_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String area;

    private String puesto;

    private String region;

    private String zona;

    private String cedis;

    private String nombre;

    private String socio;

    private String celular;

    private String correo;

    private BigDecimal puntos;

    @OneToMany(fetch=FetchType.LAZY, mappedBy="idEmpleado",cascade=CascadeType.ALL)//Si eliminamos el siniestro todas sus documentos igual se eliminan
    private List<Usuario> usuarios;

    @ManyToOne(fetch=FetchType.EAGER)//Si eliminamos el siniestro todas sus documentos igual se eliminan
    @JoinColumn(name = "cliente_id")
    private Cliente idCliente;



    public Empleado(Long id, String area, String puesto, String region, String zona, String cedis, String nombre,
            String socio, String celular, String correo, BigDecimal puntos, List<Usuario> usuarios, Cliente idCliente) {
        this.id = id;
        this.area = area;
        this.puesto = puesto;
        this.region = region;
        this.zona = zona;
        this.cedis = cedis;
        this.nombre = nombre;
        this.socio = socio;
        this.celular = celular;
        this.correo = correo;
        this.puntos = puntos;
        this.usuarios = usuarios;
        this.idCliente = idCliente;
    }

    public void inicializarTodo(){
       this.area = "";
       this.puesto="";
       this.region="";
       this.zona="";
       this.cedis = "";
       this.nombre="";
       this.socio="";
       this.celular="";
       this.correo="";
       this.idCliente=null;
       this.puntos=new BigDecimal(0);
    }

    public Empleado() {
    }

    
    public Cliente getIdCliente() {
        return idCliente;
    }


    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

 
    public String getCedis() {
        return cedis;
    }


    public void setCedis(String cedis) {
        this.cedis = cedis;
    }


  

    public BigDecimal getPuntos() {
        return puntos;
    }


    public void setPuntos(BigDecimal puntos) {
        this.puntos = puntos;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getSocio() {
        return socio;
    }

    public void setSocio(String socio) {
        this.socio = socio;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }




    
}
