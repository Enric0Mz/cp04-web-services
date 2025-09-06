package br.com.checkpoint.api_gestao_academica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Agendamento")
@Table(name = "agendamentos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutor_id")
    private Instructor instrutor;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;

    private LocalDateTime dataCancelamento;

    public void cancelar(MotivoCancelamento motivo) {
        this.motivoCancelamento = motivo;
        this.dataCancelamento = LocalDateTime.now();
    }
}
