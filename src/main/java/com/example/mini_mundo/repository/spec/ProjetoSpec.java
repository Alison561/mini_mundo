package com.example.mini_mundo.repository.spec;

import com.example.mini_mundo.model.Projeto;
import org.springframework.data.jpa.domain.Specification;

public class ProjetoSpec {
    private static Specification<Projeto> nome(String nome) {
        return (root, query, builder) -> {
            if (nome != null && !nome.isEmpty()) {
                return builder.like(builder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%");
            }
            return null;
        };
    }

    private static Specification<Projeto> descricao(String descricao) {
        return (root, query, builder) -> {
            if (descricao != null && !descricao.isEmpty()) {
                return builder.like(builder.lower(root.get("descricao")), "%" + descricao.toLowerCase() + "%");
            }
            return null;
        };
    }

    private static Specification<Projeto> status(String status) {
        return (root, query, builder) -> {
            if (status != null && !status.isEmpty()) {
                return builder.equal(root.get("status"), status);
            }
            return null;
        };
    }

    public static Specification<Projeto> filtrar(String nome, String descricao, String status) {
        return Specification
                .where(nome(nome))
                .and(descricao(descricao))
                .and(status(status));
    }
}
