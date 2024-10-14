package br.com.fiap.cp2.dto;

import br.com.fiap.cp2.model.Sexo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record DiplomaRequest(
        @NotNull(message = "O diplomado é obrigatório")
        Long diplomadoId,

        @NotNull(message = "O curso é obrigatório")
        Long cursoId,

        @NotNull(message = "A data de conclusão é obrigatória")
        LocalDate dataConclusao,

        @NotNull(message = "O sexo do reitor é obrigatório")
        Sexo sexoReitor,

        @NotBlank(message = "O nome do reitor é obrigatório")
        String nomeReitor
) {}
