package com.example.mini_mundo.controllers;

import com.example.mini_mundo.dtos.ProjetoDto;
import com.example.mini_mundo.model.Projeto;
import com.example.mini_mundo.service.impl.ProjetoServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "projetos")
@Tag(name = "Projeto", description = "API para gerenciamento de projetos")
public class ProjetoController {
    private final ProjetoServiceImpl projetoService;

    public ProjetoController(ProjetoServiceImpl projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    @Transactional
    public Projeto cadastrarProjeto(@RequestBody @Valid ProjetoDto projetoDto) {
        return projetoService.cadastrarProjeto(projetoDto);
    }

}
