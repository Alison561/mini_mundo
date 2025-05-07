package com.example.mini_mundo.service.impl;

import com.example.mini_mundo.dtos.UsuarioDto;
import com.example.mini_mundo.model.Usuario;
import com.example.mini_mundo.repository.UsuarioRepository;
import com.example.mini_mundo.service.UsuarioService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public Usuario CadastrarUsuario(UsuarioDto usuarioDto) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDto.nome());
        usuario.setEmail(usuarioDto.email());
        String senha = new BCryptPasswordEncoder().encode(usuarioDto.senha());
        usuario.setSenha(senha);
        return usuarioRepository.save(usuario);
    }
}
