package br.com.checkpoint.api_gestao_academica.model.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoDTO(
        @NotNull
        Long idAluno,

        Long idInstrutor,

        @NotNull
        @Future
        LocalDateTime data
) {
}
