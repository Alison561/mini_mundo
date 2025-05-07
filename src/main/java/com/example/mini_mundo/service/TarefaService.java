package com.example.mini_mundo.service;

import com.example.mini_mundo.dtos.FiltroTarefaDto;
import com.example.mini_mundo.dtos.TarefaDto;
import com.example.mini_mundo.model.Tarefa;

import java.util.List;

public interface TarefaService {

    List<Tarefa> listarTarefas(FiltroTarefaDto filtroTarefaDto);
    Tarefa buscarTarefa(Long tarefaId);
    Tarefa cadastrarTarefa(TarefaDto tarefaDto);
}
