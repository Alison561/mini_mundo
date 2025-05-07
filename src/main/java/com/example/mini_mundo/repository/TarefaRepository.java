package com.example.mini_mundo.repository;

import com.example.mini_mundo.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TarefaRepository extends JpaRepository<Tarefa,Long>, JpaSpecificationExecutor<Tarefa> {
    Boolean existsByTarefaPredecessora(Tarefa tarefa);
}
