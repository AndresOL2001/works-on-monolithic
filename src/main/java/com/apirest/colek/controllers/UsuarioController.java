package com.apirest.colek.controllers;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.colek.entity.Usuario;
import com.apirest.colek.services.Implementation.UsuarioService;

@RequestMapping("/api/usuarios")
@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> obtenerEmpleadoPorIdAsync(@PathVariable Long id) {
        try {

        CompletableFuture<Usuario> usuario = this.usuarioService.obtenerUsuarioPorId(id);

        Usuario usuarioDTO = usuario.get();

         return new ResponseEntity<> (usuarioDTO, HttpStatus.OK);

        } catch (Exception e) {

          logger.error("Error: "+e.getMessage());

          return new ResponseEntity<> ("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
