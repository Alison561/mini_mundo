package com.example.mini_mundo.controllers;

import com.example.mini_mundo.dtos.FiltroProjetoDto;
import com.example.mini_mundo.dtos.ProjetoDto;
import com.example.mini_mundo.model.Projeto;
import com.example.mini_mundo.service.impl.ProjetoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "projetos")
public class ProjetoController {
    private final ProjetoServiceImpl projetoService;

    public ProjetoController(ProjetoServiceImpl projetoService) {
        this.projetoService = projetoService;
    }

    @GetMapping
    public List<Projeto> listarProjetos(FiltroProjetoDto filtroProjetoDto) {
        return projetoService.listarProjetos(filtroProjetoDto);
    }

    @GetMapping("/{id}")
    public Projeto buscarProjeto( @PathVariable Long id) {
        return projetoService.buscarProjeto(id);
    }

    @PostMapping
    public Projeto cadastrarProjeto(@RequestBody @Valid ProjetoDto projetoDto) {
        return projetoService.cadastrarProjeto(projetoDto);
    }

    @PutMapping("/{id}")
    public Projeto atualizarProjeto(@PathVariable Long id, @RequestBody @Valid ProjetoDto projetoDto) {
        return projetoService.atualizarProjeto(id, projetoDto);
    }

    @DeleteMapping("/{id}")
    public void excluirProjeto(@Parameter(description = "ID do projeto") @PathVariable Long id) {
        projetoService.excluirProjeto(id);
    }

}
