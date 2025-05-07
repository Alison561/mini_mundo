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

    @Override
    @Transactional
    public Tarefa cadastrarTarefa(TarefaDto tarefaDto) {
        if (tarefaDto.dataInicio() != null && tarefaDto.dataFim() != null &&
                tarefaDto.dataFim().isBefore(tarefaDto.dataInicio())) {
            throw new RuntimeException("A data de fim não pode ser anterior à data de início.");
        }
        Tarefa tarefaPredecessora = null;
        if (tarefaDto.tarefaPredecessoraId() != null) {
            tarefaPredecessora = buscarTarefa(tarefaDto.tarefaPredecessoraId());
        }
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao(tarefaDto.descricao());
        tarefa.setProjeto(projetoServiceImpl.buscarProjeto(tarefaDto.projetoId()));
        tarefa.setDataInicio(tarefaDto.dataInicio());
        tarefa.setDataFim(tarefaDto.dataFim());
        tarefa.setTarefaPredecessora(tarefaPredecessora);
        tarefa.setStatus(tarefaDto.status());
        return tarefaRepository.save(tarefa);
    }

    @Override
    @Transactional
    public Tarefa atualizarTarefa(Long tarefaId, TarefaDto tarefaDto) {
        if (tarefaDto.dataInicio() != null && tarefaDto.dataFim() != null &&
                tarefaDto.dataFim().isBefore(tarefaDto.dataInicio())) {
            throw new RuntimeException("A data de fim não pode ser anterior à data de início.");
        }
        Tarefa tarefaPredecessora = null;
        if (tarefaDto.tarefaPredecessoraId() != null) {
            tarefaPredecessora = buscarTarefa(tarefaDto.tarefaPredecessoraId());
            if (tarefaPredecessora.getId().equals(tarefaDto.tarefaPredecessoraId())) {
                throw new RuntimeException("A tarefa não pode ser predecessora de si mesma.");
            }
        }
        Tarefa tarefa = buscarTarefa(tarefaId);
        tarefa.setDescricao(tarefaDto.descricao());
        tarefa.setProjeto(projetoServiceImpl.buscarProjeto(tarefaDto.projetoId()));
        tarefa.setDataInicio(tarefaDto.dataInicio());
        tarefa.setDataFim(tarefaDto.dataFim());
        tarefa.setTarefaPredecessora(buscarTarefa(tarefaDto.tarefaPredecessoraId()));
        tarefa.setStatus(tarefaDto.status());
        return tarefaRepository.save(tarefa);
    }

    @Override
    public void excluirTarefa(Long tarefaId) {
        Tarefa tarefa = buscarTarefa(tarefaId);
        if (tarefaRepository.existsByTarefaPredecessora(tarefa)) {
            throw new RuntimeException("A tarefa não pode ser excluída, pois é predecessora de outra.");
        }
        tarefaRepository.delete(tarefa);
    }
}
