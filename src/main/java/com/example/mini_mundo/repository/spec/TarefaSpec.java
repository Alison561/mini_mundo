package com.example.mini_mundo.repository.spec;

import com.example.mini_mundo.model.Tarefa;
import org.springframework.data.jpa.domain.Specification;

public class TarefaSpec {
    private static Specification<Tarefa> descricao(String descricao) {
        return (root, query, builder) -> {
            if (descricao != null && !descricao.isEmpty()) {
                return builder.like(builder.lower(root.get("descricao")), "%" + descricao.toLowerCase() + "%");
            }
            return null;
        };
    }

    private static Specification<Tarefa> projeto(Long projeto) {
        return (root, query, builder) -> {
            if (projeto != null) {
                return builder.equal(root.get("projeto").get("id"), projeto);
            }
            return null;
        };
    }

    private static Specification<Tarefa> status(String status) {
        return (root, query, builder) -> {
            if (status != null && !status.isEmpty()) {
                return builder.equal(root.get("status"), status);
            }
            return null;
        };
    }

    public static Specification<Tarefa> filtrar(String descricao, String status,  Long projeto) {
        return Specification
                .where(descricao(descricao))
                .and(status(status))
                .and(projeto(projeto))
                ;
    }
}
