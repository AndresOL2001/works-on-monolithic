package com.apirest.colek.DTOs.Security;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

public class NuevoUsuarioDTO {
    private int usuario_id;
    
    @NotBlank
    private String nombreUsuario;
    @NotBlank
    private String contraseña;
    private Set<String> roles = new HashSet<>();
    private Long empleado_id;

    
    public NuevoUsuarioDTO() {
    }

    public NuevoUsuarioDTO(@NotBlank String userName, @NotBlank String password, Set<String> roles,Long empleado_id) {

        this.nombreUsuario = userName;
        this.contraseña = password;
        this.roles = roles;
        this.empleado_id = empleado_id;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Long getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(Long empleado_id) {
        this.empleado_id = empleado_id;
    }
  
}
