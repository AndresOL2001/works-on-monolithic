package com.apirest.colek.services.Implementation;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.apirest.colek.entity.Usuario;
import com.apirest.colek.repositories.UsuarioRepository;
import com.apirest.colek.services.Interfaces.IUsuarioService;

@Service
@Transactional
public class UsuarioService implements IUsuarioService{

    @Autowired
    UsuarioRepository userRepository;

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<Usuario> obtenerUsuarioPorNombre(String username) {
        return CompletableFuture.completedFuture(this.userRepository.obtenerUsuarioPorNombreUsuario(username));
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<Usuario> guardarUsuario(Usuario usuario) {
        return CompletableFuture.completedFuture(this.userRepository.save(usuario));
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<Usuario> obtenerUsuarioPorId(Long id) {
        return CompletableFuture.completedFuture(this.userRepository.findById(id).get());
    }

    @Override
    @Async("asyncExecutor")
    public CompletableFuture<List<Usuario>> obtenerUsuarios() {
        return CompletableFuture.completedFuture(this.userRepository.findAll());
    }


}
