package com.apirest.colek.services.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.apirest.colek.entity.Empleado;
import com.apirest.colek.repositories.EmpleadoRepository;
import com.apirest.colek.services.Interfaces.IEmpleadoService;

@Service
@Transactional
public class EmpleadoService implements IEmpleadoService{

    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<List<Empleado>> obtenerEmpleados() {
        return CompletableFuture.completedFuture(this.empleadoRepository.findAll());
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<Empleado> buscarEmpleadoPorId(Long id) {
        return CompletableFuture.completedFuture(this.empleadoRepository.findById(id).get());
    }

    @Override
    @Async("asyncExecutor")
    public boolean eliminarEmpleado(Long id) {
        try {
            this.empleadoRepository.deleteById(id);
            return true;
         } catch (Exception e) {
            return false;
         }   
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<Empleado> crearEmpleado(Empleado estado) {
        return CompletableFuture.completedFuture(this.empleadoRepository.save(estado));
    }

    @Override
    public CompletableFuture<List<Empleado>> crearEmpleados(ArrayList<Empleado> empleados) {
        return CompletableFuture.completedFuture(this.empleadoRepository.saveAll(empleados));
    }
    
}
