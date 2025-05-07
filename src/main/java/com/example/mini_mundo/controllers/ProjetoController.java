package com.example.mini_mundo.controllers;

import com.example.mini_mundo.dtos.FiltroProjetoDto;
import com.example.mini_mundo.dtos.ProjetoDto;
import com.example.mini_mundo.model.Projeto;
import com.example.mini_mundo.service.impl.ProjetoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "projetos")
@Tag(name = "Projeto", description = "API para gerenciamento de projetos")
public class ProjetoController {
    private final ProjetoServiceImpl projetoService;

    public ProjetoController(ProjetoServiceImpl projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public List<Projeto> listarProjetos(FiltroProjetoDto filtroProjetoDto) {
        return projetoService.listarProjetos(filtroProjetoDto);
    }

    @PostMapping
    @Transactional
    public Projeto cadastrarProjeto(@RequestBody @Valid ProjetoDto projetoDto) {
        return projetoService.cadastrarProjeto(projetoDto);
    }

}
