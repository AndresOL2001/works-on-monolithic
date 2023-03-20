package com.apirest.colek.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apirest.colek.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
    @Query("select u from Usuario u where u.nombreUsuario=?1")
    public Usuario obtenerUsuarioPorNombreUsuario( String nombreUsuario);
}
