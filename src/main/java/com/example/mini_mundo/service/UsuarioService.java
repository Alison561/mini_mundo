package com.example.mini_mundo.service;

import com.example.mini_mundo.dtos.UsuarioDto;
import com.example.mini_mundo.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioService {
    UserDetails BuscarUsuarioEmail(String email);
    Usuario CadastrarUsuario(UsuarioDto usuarioDto);
}
