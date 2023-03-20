package com.apirest.colek.controllers;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apirest.colek.DTOs.LoginDTO;
import com.apirest.colek.DTOs.Security.JwtDTO;
import com.apirest.colek.DTOs.Security.NuevoUsuarioDTO;
import com.apirest.colek.entity.Rol;
import com.apirest.colek.entity.Usuario;
import com.apirest.colek.security.JWT.JwtProvider;
import com.apirest.colek.services.Implementation.EmpleadoService;
import com.apirest.colek.services.Implementation.RolService;
import com.apirest.colek.services.Implementation.UsuarioService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final RolService rolService;
    private final EmpleadoService empleadoService;
    private final Logger logger = LoggerFactory.getLogger(AuthController.class);


    @Autowired
    public AuthController(UsuarioService usuarioService,AuthenticationManagerBuilder AuthenticationManagerBuilder,JwtProvider jwt,PasswordEncoder passwordEncoder,RolService rolService,EmpleadoService empleadoService) {
        this.usuarioService = usuarioService;
        this.authenticationManagerBuilder = AuthenticationManagerBuilder;
        this.jwtProvider = jwt;
        this.passwordEncoder = passwordEncoder;
        this.rolService = rolService;
        this.empleadoService = empleadoService;
    }



    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDTO loginUser, BindingResult bidBindingResult){
       /*  if(bidBindingResult.hasErrors())
        
            return new ResponseEntity<>("Revise sus credenciales"+bidBindingResult.getFieldErrors(), HttpStatus.BAD_REQUEST); */
        try {
                UsernamePasswordAuthenticationToken authenticationToken= new UsernamePasswordAuthenticationToken(loginUser.getUsuario(), loginUser.getContraseña());
                Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtProvider.generateToken(authentication);
                JwtDTO jwtDto = new JwtDTO(jwt);
                return new ResponseEntity<>(jwtDto, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
                return new ResponseEntity<>("Revise sus credenciales", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<Object> register(@Valid @RequestBody NuevoUsuarioDTO usuario, BindingResult bindingResult) throws InterruptedException, ExecutionException {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>("Revise los campos e intente nuevamente", HttpStatus.BAD_REQUEST);
        Usuario user = new Usuario(usuario.getNombreUsuario(),
                passwordEncoder.encode(usuario.getContraseña()));
        Set<Rol> roles = new HashSet<>();
        user.setIdUsuario(Long.valueOf(usuario.getUsuario_id()));
        try {
        user.setIdEmpleado(empleadoService.buscarEmpleadoPorId(usuario.getEmpleado_id()).get());
        } catch (Exception e) {
            logger.error("Error: ", e.getMessage());
            return new ResponseEntity<>("Error: "+e.getMessage(), HttpStatus.BAD_REQUEST);

        }
        roles.add(rolService.getByRolName("ROLE_USER").get().get());


        if (usuario.getRoles().contains("ROLE_ADMIN"))
            roles.add(rolService.getByRolName("ROLE_ADMIN").get().get());
        user.setRoles(roles);
        
        usuarioService.guardarUsuario(user); 
        return new ResponseEntity<>("Registro exitoso! Inicie sesión", HttpStatus.CREATED);
    }
}

