package br.com.fiap.cp2.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(
        @NotBlank(message = "O RG é obrigatório")
        String rg,
        @NotBlank(message = "A senha é obrigatória")
        String senha) {}
