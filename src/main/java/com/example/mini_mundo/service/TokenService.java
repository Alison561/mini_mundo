package com.example.mini_mundo.service;

import com.example.mini_mundo.model.Usuario;

import java.time.Instant;

public interface TokenService {
    String GerarToken(Usuario usuario);
    String validateToken(String token);
    Instant tempoExpiracao();
}
