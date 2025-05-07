package com.example.mini_mundo.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioService {
    UserDetails BuscarUsuarioEmail(String email);
}
