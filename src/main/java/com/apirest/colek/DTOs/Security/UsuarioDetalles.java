package com.apirest.colek.DTOs.Security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.apirest.colek.entity.Usuario;

public class UsuarioDetalles implements UserDetails{

    private String nombreUsuario;
    private String contraseña;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioDetalles(String userName, String password,
    Collection<? extends GrantedAuthority> authorities) {
        this.nombreUsuario = userName;
        this.contraseña = password;
        this.authorities = authorities;
        }

        public static UsuarioDetalles build(Usuario usuario) {
            List<GrantedAuthority> authorities = usuario.getRoles().stream().map(role-> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
            return new UsuarioDetalles(usuario.getNombreUsuario(), usuario.getContraseña(), authorities);
          }

          @Override
          public Collection<? extends GrantedAuthority> getAuthorities() {
              return authorities;
          }
      
          @Override
          public String getPassword() {
              return contraseña;
          }
      
          @Override
          public String getUsername() {
              return nombreUsuario;
          }
      
          @Override
          public boolean isAccountNonExpired() {
              return true;
          }
      
          @Override
          public boolean isAccountNonLocked() {
              return true;
          }
      
          @Override
          public boolean isCredentialsNonExpired() {
              return true;
          }
      
          @Override
          public boolean isEnabled() {
              return true;
          }
    
}
