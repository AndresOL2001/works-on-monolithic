package com.apirest.colek.services.Interfaces;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.apirest.colek.entity.Usuario;

public interface IUsuarioService {
    public CompletableFuture<Usuario> obtenerUsuarioPorNombre(String username);

    public CompletableFuture<Usuario> guardarUsuario(Usuario usuario);

    public CompletableFuture<Usuario> obtenerUsuarioPorId(Long id);

    public CompletableFuture<List<Usuario>> obtenerUsuarios();
}
