package br.com.checkpoint.api_gestao_academica.model.dto;

import br.com.checkpoint.api_gestao_academica.model.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record CancelamentoAgendamentoDTO(
        @NotNull
        Long idAgendamento,

        @NotNull
        MotivoCancelamento motivo
) {
}
