package br.com.fiap.cp2.dto;

import java.time.LocalDate;
import java.util.UUID;

public record DiplomaResponse(
        UUID id,
        DiplomadoResponse diplomado,
        CursoResponse curso,
        LocalDate dataConclusao,
        String sexoReitor,
        String nomeReitor
) {}
