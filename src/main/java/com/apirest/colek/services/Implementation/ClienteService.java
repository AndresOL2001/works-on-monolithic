package com.apirest.colek.services.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.apirest.colek.entity.Cliente;
import com.apirest.colek.repositories.ClienteRepository;
import com.apirest.colek.services.Interfaces.IClienteService;

@Transactional
@Service
public class ClienteService implements IClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<List<Cliente>> obtenerClientes() {
        return CompletableFuture.completedFuture(this.clienteRepository.findAll());
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<Cliente> buscarClientePorId(Long id) {
        return CompletableFuture.completedFuture(this.clienteRepository.findById(id).get());
    }

    @Override
    @Async("asyncExecutor")
    public boolean eliminarCliente(Long id) {
        try {
            this.clienteRepository.deleteById(id);
            return true;
         } catch (Exception e) {
            return false;
         }   
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<Cliente> crearCliente(Cliente cliente) {
        return CompletableFuture.completedFuture(this.clienteRepository.save(cliente));   
     }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<Cliente> findByNombre(String nombre) {
        return CompletableFuture.completedFuture(this.clienteRepository.findByNombre(nombre));
    }

    @Override
    public CompletableFuture<List<Cliente>> crearClientes(ArrayList<Cliente> clientes) {
        return CompletableFuture.completedFuture(this.clienteRepository.saveAll(clientes));
    }
    
}
