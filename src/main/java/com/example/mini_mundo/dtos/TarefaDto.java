package com.example.mini_mundo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record TarefaDto(

        @NotBlank(message = "A descrição é obrigatória.")
        String descricao,
        @NotNull(message = "O projeto é obrigatório.")
        Long projetoId,
        LocalDate dataInicio,
        LocalDate dataFim,
        Long tarefaPredecessoraId,
        @Pattern(
                regexp = "Concluída|Não Concluída",
                message = "O status deve ser 'Concluída' ou 'Não Concluída'."
        )
        String status

) {
}
