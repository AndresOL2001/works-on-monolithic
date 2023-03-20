package com.apirest.colek.services.Interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.apirest.colek.entity.Cliente;

public interface IClienteService {
    public CompletableFuture<List<Cliente>> obtenerClientes();
    public CompletableFuture<Cliente> buscarClientePorId(Long id);
    public boolean eliminarCliente(Long id);
    public CompletableFuture<Cliente> crearCliente(Cliente estado);
    public CompletableFuture<List<Cliente>> crearClientes(ArrayList<Cliente> clientes);

    public CompletableFuture<Cliente> findByNombre(String id);
}
