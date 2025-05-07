package com.example.mini_mundo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProjetoDto(
        @NotBlank(message = "O nome do projeto é obrigatório.")
        String nome,
        String descricao,
        @NotBlank(message = "O status tt do projeto é obrigatório.")
        @Pattern(regexp = "Ativo|Inativo", message = "O status deve ser 'Ativo' ou 'Inativo'.")
        String status,
        @PositiveOrZero(message = "O orçamento deve ser um valor positivo.")
        BigDecimal orcamento
) {
}
