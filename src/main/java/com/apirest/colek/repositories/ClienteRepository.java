package com.apirest.colek.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apirest.colek.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{

    Cliente findByNombre(String id);
}
