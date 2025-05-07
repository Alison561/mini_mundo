package com.example.mini_mundo.service.impl;

import com.example.mini_mundo.Exceptions.NaoEcontradoException;
import com.example.mini_mundo.dtos.FiltroTarefaDto;
import com.example.mini_mundo.dtos.TarefaDto;
import com.example.mini_mundo.model.Tarefa;
import com.example.mini_mundo.repository.TarefaRepository;
import com.example.mini_mundo.repository.spec.TarefaSpec;
import com.example.mini_mundo.service.TarefaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TarefaserviceImpl implements TarefaService {
    private final TarefaRepository tarefaRepository;
    private final ProjetoServiceImpl projetoServiceImpl;

    public TarefaserviceImpl(TarefaRepository tarefaRepository, ProjetoServiceImpl projetoServiceImpl) {
        this.tarefaRepository = tarefaRepository;
        this.projetoServiceImpl = projetoServiceImpl;
    }

    @Override
    public List<Tarefa> listarTarefas(FiltroTarefaDto filtroTarefaDto) {
        return tarefaRepository.findAll(TarefaSpec.filtrar(filtroTarefaDto.descricao(), filtroTarefaDto.status(), filtroTarefaDto.projetoId()));
    }

    @Override
    public Tarefa buscarTarefa(Long tarefaId) {
        return tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new NaoEcontradoException("Tarefa não encontrado"));
    }
}
