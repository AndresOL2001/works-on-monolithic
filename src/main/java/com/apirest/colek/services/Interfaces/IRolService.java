package com.apirest.colek.services.Interfaces;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import com.apirest.colek.entity.Rol;

public interface IRolService {
        
    public CompletableFuture<Optional<Rol>> getByRolName(String roleName);

    public CompletableFuture<Rol> guardarRol(Rol rol);

    public CompletableFuture<List<Rol>> obtenerRoles();

    public CompletableFuture<Rol> obtenerRolPorId(int id);

    public boolean eliminarRol(int id);
}
