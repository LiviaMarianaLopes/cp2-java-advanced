package br.com.fiap.cp2.dto;

import java.util.List;
import java.util.UUID;

public record LoginResponse(String token, List<UUID> diplomaUuids) {}