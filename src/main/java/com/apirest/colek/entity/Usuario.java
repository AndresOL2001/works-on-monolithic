package com.apirest.colek.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usuarios",
indexes = { @Index(name = "IDX_USUARIO", columnList = "nombreUsuario,verificacion") })
@JsonIgnoreProperties({"usuarios_Actividades","remitentes","contraseña","verificacion","idEmpleado","comentarios"})
public class Usuario {
    @Id
    @Column(name = "usuario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombreUsuario;

    private String contraseña;

    private int verificacion;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_roles",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id")
            )
    private Set<Rol> roles = new HashSet<>();

   /*  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuarios_comentarios",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "comentario_id")
            )
    private Set<Comentario> comentarios = new HashSet<>(); */
    @OneToMany(mappedBy = "usuario")
    private Set<Usuarios_Comentarios> usuarios_Comentarios = new HashSet<>();
    
    @OneToMany(mappedBy = "usuario")
    private Set<Usuarios_Actividades> usuarios_Actividades = new HashSet<>();

    @OneToMany(mappedBy = "usuario")
    private Set<Usuarios_Notificaciones> remitentes = new HashSet<>();

    @ManyToOne(fetch=FetchType.LAZY)//Si eliminamos el siniestro todas sus documentos igual se eliminan
    @JoinColumn(name = "empleado_id")
    private Empleado idEmpleado;

    public Usuario(String nombreUsuario, String contraseña) {
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
    }
    public Usuario(Long idUsuario, String nombreUsuario, String contraseña, int verificacion, Set<Rol> roles) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.verificacion = verificacion;
        this.roles = roles;
    }
    public Usuario() {
    }
    
    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public int getVerificacion() {
        return verificacion;
    }
    public void setVerificacion(int verificacion) {
        this.verificacion = verificacion;
    }
    public Set<Rol> getRoles() {
        return roles;
    }
    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
 /*    public Set<Comentario> getComentarios() {
        return comentarios;
    }
    public void setComentarios(Set<Comentario> comentarios) {
        this.comentarios = comentarios;
    } */
    public Set<Usuarios_Actividades> getUsuarios_Actividades() {
        return usuarios_Actividades;
    }
    public void setUsuarios_Actividades(Set<Usuarios_Actividades> usuarios_Actividades) {
        this.usuarios_Actividades = usuarios_Actividades;
    }
    public Set<Usuarios_Notificaciones> getRemitentes() {
        return remitentes;
    }
    public void setRemitentes(Set<Usuarios_Notificaciones> remitentes) {
        this.remitentes = remitentes;
    }
    public Empleado getIdEmpleado() {
        return idEmpleado;
    }
    public void setIdEmpleado(Empleado idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    
}
