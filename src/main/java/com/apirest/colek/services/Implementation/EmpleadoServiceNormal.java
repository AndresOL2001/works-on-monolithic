package com.apirest.colek.services.Implementation;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.colek.entity.Empleado;
import com.apirest.colek.repositories.EmpleadoRepository;

@Transactional
@Service
public class EmpleadoServiceNormal {
    @Autowired
    EmpleadoRepository empleadoRepository;

    public List<Empleado> obtenerEmpleados() {
        return this.empleadoRepository.findAll();
    }

    public Empleado buscarEmpleadoPorId(Long id) {
        return this.empleadoRepository.findById(id).get();
    }

    public boolean eliminarEmpleado(Long id) {
        try {
            this.empleadoRepository.deleteById(id);
            return true;
         } catch (Exception e) {
            return false;
         }   
    }

    public Empleado crearEmpleado(Empleado estado) {
        return this.empleadoRepository.save(estado);
    }
}
