package com.apirest.colek.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.colek.entity.Rol;

public interface RolRepository extends JpaRepository<Rol,Integer>{
    Optional<Rol> findByNombre(String nombre);

}
