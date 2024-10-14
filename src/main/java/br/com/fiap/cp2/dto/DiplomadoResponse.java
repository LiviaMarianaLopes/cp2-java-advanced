package br.com.fiap.cp2.dto;

public record DiplomadoResponse(
        Long id,
        String nome,
        String nacionalidade,
        String naturalidade,
        String rg
) {}
