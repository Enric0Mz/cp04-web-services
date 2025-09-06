package br.com.checkpoint.api_gestao_academica.model.dto;

import br.com.checkpoint.api_gestao_academica.model.Agendamento;

import java.time.LocalDateTime;

public record AgendamentoDetailsDTO(
        Long id,
        Long idAluno,
        String nomeAluno,
        Long idInstrutor,
        String nomeInstrutor,
        LocalDateTime data
) {
    public AgendamentoDetailsDTO(Agendamento agendamento) {
        this(
                agendamento.getId(),
                agendamento.getAluno().getId(),
                agendamento.getAluno().getNome(),
                agendamento.getInstrutor().getId(),
                agendamento.getInstrutor().getNome(),
                agendamento.getData()
        );
    }
}
