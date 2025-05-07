package com.example.mini_mundo.service;

import com.example.mini_mundo.dtos.ProjetoDto;
import com.example.mini_mundo.model.Projeto;

public interface ProjetoService {
    Projeto cadastrarProjeto(ProjetoDto projetoDto);
}
