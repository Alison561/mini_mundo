package com.example.mini_mundo.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDto(
        @NotBlank(message = "O campo nome é obrigatório")
        String nome,
        @NotBlank(message = "O campo email é obrigatório")
        @Email(message = "O campo tem que ser email")
        String email,
        @NotBlank(message = "O campo senha é obrigatório")
        String senha
) {
}
