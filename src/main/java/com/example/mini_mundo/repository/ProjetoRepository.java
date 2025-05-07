package com.example.mini_mundo.repository;

import com.example.mini_mundo.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProjetoRepository extends JpaRepository<Projeto, Long>, JpaSpecificationExecutor<Projeto> {
    Boolean existsByNome(String nome);

    Boolean existsByNomeAndIdNot(String nome, Long id);    
}
