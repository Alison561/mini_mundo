package com.example.mini_mundo.service;

import com.example.mini_mundo.dtos.FiltroProjetoDto;
import com.example.mini_mundo.dtos.ProjetoDto;
import com.example.mini_mundo.model.Projeto;

import java.util.List;

public interface ProjetoService {
    List<Projeto> listarProjetos(FiltroProjetoDto filtroProjetoDto);
    Projeto cadastrarProjeto(ProjetoDto projetoDto);
}
