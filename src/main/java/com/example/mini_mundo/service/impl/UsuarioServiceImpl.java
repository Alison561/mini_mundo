package com.example.mini_mundo.service.impl;

import com.example.mini_mundo.repository.UsuarioRepository;
import com.example.mini_mundo.service.UsuarioService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }
    @Override
    public UserDetails BuscarUsuarioEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
