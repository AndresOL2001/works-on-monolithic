package com.apirest.colek.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.colek.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado,Long>{
    
}
