package com.example.mini_mundo.service;

import com.example.mini_mundo.dtos.FiltroProjetoDto;
import com.example.mini_mundo.dtos.ProjetoDto;
import com.example.mini_mundo.model.Projeto;

import java.util.List;

public interface ProjetoService {
    List<Projeto> listarProjetos(FiltroProjetoDto filtroProjetoDto);
    Projeto buscarProjeto(Long projetoId);
    Projeto cadastrarProjeto(ProjetoDto projetoDto);
    Projeto atualizarProjeto(Long projetoId, ProjetoDto projetoDto);
    void excluirProjeto(Long projetoId);
}
