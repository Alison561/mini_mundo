package com.example.mini_mundo.controllers;

import com.example.mini_mundo.dtos.UsuarioDto;
import com.example.mini_mundo.model.Usuario;
import com.example.mini_mundo.service.impl.UsuarioServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "usuarios")
public class UsuarioController {
    private final UsuarioServiceImpl usuarioService;

    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Usuario cadastrarUsuario(@RequestBody UsuarioDto usuarioDto){
        return usuarioService.CadastrarUsuario(usuarioDto);
    }

}
