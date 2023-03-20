package com.apirest.colek.DTOs.Empleado;

import java.math.BigDecimal;

public class EmpleadoDTO {

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

    private String idCliente;

    public EmpleadoDTO(Long id, String area, String puesto, String region, String zona, String cedis, String nombre,
            String socio, String celular, String correo, BigDecimal puntos, String idCliente) {
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
        this.idCliente = idCliente;
    }

    public EmpleadoDTO() {
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

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    
}
