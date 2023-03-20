package com.apirest.colek.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.colek.DTOs.Rol.CambiarRolDTO;
import com.apirest.colek.entity.Rol;
import com.apirest.colek.entity.Usuario;
import com.apirest.colek.services.Implementation.RolService;
import com.apirest.colek.services.Implementation.UsuarioService;

@RequestMapping("/api/roles")
@RestController
public class RolController {
    
    private final RolService rolService;
    private final UsuarioService usuarioService;

    @Autowired
    public RolController(RolService rolService,UsuarioService usuarioService) {
        this.rolService = rolService;
        this.usuarioService = usuarioService;
    }

    @PostMapping()
    public ResponseEntity<Object> crearRol(@Valid @RequestBody Rol rol, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>("Revise los campos e intente nuevamente", HttpStatus.BAD_REQUEST);
            
            try {
                Rol rolAux = this.rolService.guardarRol(rol).get();
                System.out.println(rolAux);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: El campo rol no puede estar vacío"+e.getMessage(), HttpStatus.BAD_REQUEST);
            }
          
        return new ResponseEntity<>("El rol se ha creado correctamente", HttpStatus.CREATED);
    }   

    @DeleteMapping("{id}")
    public ResponseEntity<Object> eliminarRol(@PathVariable int id) {
            
        try {
            rolService.eliminarRol(id);
        } catch (Exception e) {
            return new ResponseEntity<>("Error:No encontró ningún rol con ese id", HttpStatus.BAD_REQUEST);
        }
      
    return new ResponseEntity<>("Rol eliminado correctamente", HttpStatus.OK);
}

    @GetMapping()
    public ResponseEntity<Object> obtenerRoles() {
            List<Rol> roles = new ArrayList<>(); 
            try {
                roles = rolService.obtenerRoles().get();
            } catch (Exception e) {
                return new ResponseEntity<>("Error"+e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
          
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> obtenerRoles(@PathVariable int id) {
        Rol rol = new Rol(); 
        try {
            rol = rolService.obtenerRolPorId(id).get();
        } catch (Exception e) {
            return new ResponseEntity<>("Error no existe ningun rol con ese id", HttpStatus.BAD_REQUEST);
        }
      
    return new ResponseEntity<>(rol, HttpStatus.OK);
    }

    @PostMapping("/cambiarRol")
    public ResponseEntity<Object> cambiarRol(@Valid @RequestBody CambiarRolDTO rol, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>("Revise los campos e intente nuevamente", HttpStatus.BAD_REQUEST);
            
            Usuario user = new Usuario();
            Set<Rol> roles = new HashSet<>();
            System.out.println(rol);

            try {
                 user = usuarioService.obtenerUsuarioPorNombre(rol.getNombreUsuario()).get();

            } catch (Exception e) {
                return new ResponseEntity<>("Error: No existe ningún usuario con ese nombre", HttpStatus.BAD_REQUEST);
            }

            for (String rolAux: rol.getRoles()) {
                try {
                    Rol rolBD = rolService.getByRolName(rolAux).get().get();
                    roles.add(rolBD);

                } catch (Exception e) {
                    return new ResponseEntity<>("No existe el rol "+rolAux, HttpStatus.BAD_REQUEST);

                }
           
            }

            user.setRoles(roles);
            usuarioService.guardarUsuario(user);
        return new ResponseEntity<>("Registro exitoso! Inicie sesión", HttpStatus.CREATED);
    }
}
