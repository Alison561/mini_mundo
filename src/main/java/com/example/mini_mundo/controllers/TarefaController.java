package com.example.mini_mundo.controllers;

import com.example.mini_mundo.dtos.FiltroTarefaDto;
import com.example.mini_mundo.model.Tarefa;
import com.example.mini_mundo.service.impl.TarefaserviceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "tarefas")
public class TarefaController {
    private final TarefaserviceImpl tarefaservice;

    public TarefaController(TarefaserviceImpl tarefaservice) {
        this.tarefaservice = tarefaservice;
    }

    @GetMapping("/{id}")
    public Tarefa buscarTarefa(@PathVariable Long id) {
        return tarefaservice.buscarTarefa(id);
    }

    @GetMapping
    public List<Tarefa> listarTarefas(FiltroTarefaDto filtroTarefaDto) {
        return tarefaservice.listarTarefas(filtroTarefaDto);
    }
}
