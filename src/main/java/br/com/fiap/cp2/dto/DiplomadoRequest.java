package br.com.fiap.cp2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DiplomadoRequest(
        @NotBlank(message = "O nome do diplomado é obrigatório")
        String nome,

        @NotBlank(message = "A nacionalidade é obrigatória")
        String nacionalidade,

        @NotBlank(message = "A naturalidade é obrigatória")
        String naturalidade,

        @NotBlank(message = "O RG é obrigatório")
        @Size(min = 9, max = 9, message = "O RG deve ter 9 dígitos")
        String rg
) {}