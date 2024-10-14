package br.com.fiap.cp2.dto;

import br.com.fiap.cp2.model.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterDTO(
        @NotBlank(message = "O RG é obrigatório")
        @Size(min = 9, max = 9, message = "O RG deve ter 9 dígitos")
        String rg,
        @NotBlank(message = "A senha é obrigatória")
        String senha,
        @NotNull(message = "O UserRole é obrigatório")
        UserRole role) {}
