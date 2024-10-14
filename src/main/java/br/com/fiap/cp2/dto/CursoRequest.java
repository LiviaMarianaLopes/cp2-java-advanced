package br.com.fiap.cp2.dto;

import br.com.fiap.cp2.model.TipoCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CursoRequest(
        @NotBlank(message = "O nome do curso é obrigatório")
        String nome,

        @NotNull(message = "O tipo do curso é obrigatório")
        TipoCurso tipo
) {}
