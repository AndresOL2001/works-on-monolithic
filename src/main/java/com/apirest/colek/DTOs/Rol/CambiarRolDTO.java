package com.apirest.colek.DTOs.Rol;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;

public class CambiarRolDTO {
    @NotBlank
    private String nombreUsuario;
    
    private Set<String> roles = new HashSet<>();

    public CambiarRolDTO(@NotBlank String nombreUsuario, Set<String> roles) {
        this.nombreUsuario = nombreUsuario;
        this.roles = roles;
    }

    public CambiarRolDTO() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "cambiarRolDTO [nombreUsuario=" + nombreUsuario + ", roles=" + roles + "]";
    }
}
