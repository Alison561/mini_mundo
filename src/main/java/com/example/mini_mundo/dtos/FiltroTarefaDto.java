package com.example.mini_mundo.dtos;

public record FiltroTarefaDto(
        String descricao,
        Long projetoId,
        String status
) {
}
