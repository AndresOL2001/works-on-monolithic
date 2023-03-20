package com.apirest.colek.services.Implementation;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.apirest.colek.entity.Rol;
import com.apirest.colek.repositories.RolRepository;
import com.apirest.colek.services.Interfaces.IRolService;

@Transactional
@Service
public class RolService implements IRolService{

    @Autowired
    RolRepository rolRepository;

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<Optional<Rol>> getByRolName(String roleName) {
        return CompletableFuture.completedFuture(this.rolRepository.findByNombre(roleName));
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<Rol> guardarRol(Rol rol) {
        return CompletableFuture.completedFuture(this.rolRepository.save(rol));
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<List<Rol>> obtenerRoles() {
        return CompletableFuture.completedFuture(this.rolRepository.findAll());
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<Rol> obtenerRolPorId(int id) {
        return CompletableFuture.completedFuture(this.rolRepository.findById(id).get());
    }

    @Override
    public boolean eliminarRol(int id) {
        try {
            this.rolRepository.deleteById(id);
            return true;
         } catch (Exception e) {
            return false;
         }   
    }
    
}
