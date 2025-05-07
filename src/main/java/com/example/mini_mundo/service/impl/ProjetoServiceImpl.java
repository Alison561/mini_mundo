package com.example.mini_mundo.service.impl;

import com.example.mini_mundo.dtos.ProjetoDto;
import com.example.mini_mundo.model.Projeto;
import com.example.mini_mundo.repository.ProjetoRepository;
import com.example.mini_mundo.service.ProjetoService;
import org.springframework.stereotype.Service;

@Service
public class ProjetoServiceImpl implements ProjetoService {
    private final ProjetoRepository projetoRepository;

    public ProjetoServiceImpl(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
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
}
