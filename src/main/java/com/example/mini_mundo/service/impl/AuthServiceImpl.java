package com.example.mini_mundo.service.impl;

import com.example.mini_mundo.dtos.DadosLoginDto;
import com.example.mini_mundo.dtos.TokenDto;
import com.example.mini_mundo.model.Usuario;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements UserDetailsService {
    private final UsuarioServiceImpl usuarioService;
    private final AuthenticationManager authenticationManager;
    private final TokenServiceImpl tokenService;

    public AuthServiceImpl(UsuarioServiceImpl usuarioService, @Lazy AuthenticationManager authenticationManager, TokenServiceImpl tokenService){
        this.usuarioService = usuarioService;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public TokenDto logar(DadosLoginDto dadosLoginDto){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(dadosLoginDto.email(), dadosLoginDto.senha());
        Authentication auth = this.authenticationManager.authenticate(usernamePassword);
        return new TokenDto(tokenService.GerarToken((Usuario) auth.getPrincipal()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioService.BuscarUsuarioEmail(username);
    }
}
