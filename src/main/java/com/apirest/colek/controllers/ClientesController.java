package com.apirest.colek.controllers;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.colek.entity.Cliente;
import com.apirest.colek.services.Implementation.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClientesController {

    private final ClienteService clienteService;

    private Logger logger = LoggerFactory.getLogger(ClientesController.class);

    public ClientesController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping()
    public ResponseEntity<Object> crearCliente(@Valid @RequestBody Cliente cliente, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>("Revise los campos e intente nuevamente", HttpStatus.BAD_REQUEST);
            
            try {
            
                this.clienteService.crearCliente(cliente);
            } catch (Exception e) {
                return new ResponseEntity<>("El cliente no puede ser vacio", HttpStatus.BAD_REQUEST);
            }
          
        return new ResponseEntity<>("El cliente se a creado correctamente", HttpStatus.CREATED);
    }


    @GetMapping()
    public ResponseEntity<Object> obtenerEmpleadosAsync() {
        try {

        CompletableFuture<List<Cliente>> clientes = this.clienteService.obtenerClientes();

        List<Cliente> clientesDTO = clientes.get();

         return new ResponseEntity<> (clientesDTO, HttpStatus.OK);

        } catch (Exception e) {

          logger.error("Error: "+e.getMessage());

          return new ResponseEntity<> ("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> obtenerClientesPorIdAsync(@PathVariable Long id) {
        try {

        CompletableFuture<Cliente> cliente = this.clienteService.buscarClientePorId(id);

        Cliente clienteDTO = cliente.get();

         return new ResponseEntity<> (clienteDTO, HttpStatus.OK);

        } catch (Exception e) {

          logger.error("Error: "+e.getMessage());

          return new ResponseEntity<> ("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
