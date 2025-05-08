package com.example.mini_mundo.service.impl;

import com.example.mini_mundo.Exceptions.NaoEcontradoException;
import com.example.mini_mundo.dtos.FiltroProjetoDto;
import com.example.mini_mundo.dtos.ProjetoDto;
import com.example.mini_mundo.model.Projeto;
import com.example.mini_mundo.repository.ProjetoRepository;
import com.example.mini_mundo.repository.spec.ProjetoSpec;
import com.example.mini_mundo.service.ProjetoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoServiceImpl implements ProjetoService {
    private final ProjetoRepository projetoRepository;

    public ProjetoServiceImpl(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    @Override
    public List<Projeto> listarProjetos(FiltroProjetoDto filtroProjetoDto) {
        return projetoRepository.findAll(ProjetoSpec.filtrar(filtroProjetoDto.nome(), filtroProjetoDto.descricao(),filtroProjetoDto.status()));
    }

    @Override
    public Projeto buscarProjeto(Long projetoId) {
        return projetoRepository.findById(projetoId)
                .orElseThrow(() -> new NaoEcontradoException("Projeto não encontrado"));
    }

    @Override
    public Projeto cadastrarProjeto(ProjetoDto projetoDto) {
        if (projetoRepository.existsByNome(projetoDto.nome())){
            throw new RuntimeException("Já existe um projeto com esse nome.");
        }

        Projeto projeto = new Projeto();
        projeto.setNome(projetoDto.nome());
        projeto.setDescricao(projetoDto.descricao());
        projeto.setStatus(projetoDto.status());
        projeto.setOrcamento(projetoDto.orcamento());
        return projetoRepository.save(projeto);
    }

    @Override
    public Projeto atualizarProjeto(Long projetoId, ProjetoDto projetoDto) {
        if (projetoRepository.existsByNomeAndIdNot(projetoDto.nome(), projetoId)){
            throw new RuntimeException("Já existe um projeto com esse nome.");
        }

        Projeto projeto = buscarProjeto(projetoId);
        projeto.setNome(projetoDto.nome());
        projeto.setDescricao(projetoDto.descricao());
        projeto.setStatus(projetoDto.status());
        projeto.setOrcamento(projetoDto.orcamento());
        return projetoRepository.save(projeto);
    }

    @Override
    public void excluirProjeto(Long projetoId) {
        Projeto projeto = buscarProjeto(projetoId);
        if (!projeto.getTarefas().isEmpty()){
            throw new RuntimeException("O projeto não pode ser excluída, pois tem tarefas vinculadas.");
        }
        projetoRepository.delete(projeto);
    }
}
