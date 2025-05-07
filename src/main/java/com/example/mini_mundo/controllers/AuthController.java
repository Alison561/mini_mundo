package com.example.mini_mundo.controllers;

import com.example.mini_mundo.dtos.DadosLoginDto;
import com.example.mini_mundo.dtos.TokenDto;
import com.example.mini_mundo.service.impl.AuthServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "auth")
public class AuthController {

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public TokenDto login(@RequestBody DadosLoginDto dadosLoginDto){
        return authService.logar(dadosLoginDto);
    }

}
