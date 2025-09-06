package br.com.checkpoint.api_gestao_academica.service;

import br.com.checkpoint.api_gestao_academica.model.dto.AgendamentoDTO;
import br.com.checkpoint.api_gestao_academica.model.dto.CancelamentoAgendamentoDTO;
import br.com.checkpoint.api_gestao_academica.infra.exceptions.ValidacaoException;
import br.com.checkpoint.api_gestao_academica.model.Agendamento;
import br.com.checkpoint.api_gestao_academica.model.Instructor;
import br.com.checkpoint.api_gestao_academica.repository.AgendamentoRepository;
import br.com.checkpoint.api_gestao_academica.repository.AlunoRepository;
import br.com.checkpoint.api_gestao_academica.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    public Agendamento agendar(AgendamentoDTO dados) {
        if (!alunoRepository.existsById(dados.idAluno())) {
            throw new ValidacaoException("ID do aluno informado não existe.");
        }
        if (dados.idInstrutor() != null && !instructorRepository.existsById(dados.idInstrutor())) {
            throw new ValidacaoException("ID do instrutor informado não existe.");
        }

        validarRegrasDeNegocio(dados);

        var aluno = alunoRepository.getReferenceById(dados.idAluno());
        var instrutor = escolherInstrutor(dados);

        if (instrutor == null) {
            throw new ValidacaoException("Não há instrutor disponível nesta data/hora.");
        }

        var agendamento = new Agendamento(null, aluno, instrutor, dados.data(), null, null);
        return agendamentoRepository.save(agendamento);
    }

    public void cancelar(CancelamentoAgendamentoDTO dados) {
        if (!agendamentoRepository.existsById(dados.idAgendamento())) {
            throw new ValidacaoException("ID do agendamento informado não existe.");
        }

        var agendamento = agendamentoRepository.getReferenceById(dados.idAgendamento());
        var agora = LocalDateTime.now();

        if (Duration.between(agora, agendamento.getData()).toHours() < 24) {
            throw new ValidacaoException("O cancelamento deve ser feito com no mínimo 24 horas de antecedência.");
        }

        agendamento.cancelar(dados.motivo());
    }

    private void validarRegrasDeNegocio(AgendamentoDTO dados) {
        var dataAgendamento = dados.data();
        var agora = LocalDateTime.now();

        if (Duration.between(agora, dataAgendamento).toMinutes() < 30) {
            throw new ValidacaoException("Agendamento deve ser feito com no mínimo 30 minutos de antecedência.");
        }

        var diaDaSemana = dataAgendamento.getDayOfWeek();
        if (diaDaSemana.equals(DayOfWeek.SUNDAY)) {
            throw new ValidacaoException("A auto-escola não funciona aos domingos.");
        }

        var horaAbertura = dataAgendamento.toLocalDate().atTime(6, 0);
        var horaFechamento = dataAgendamento.toLocalDate().atTime(21, 0);
        if (dataAgendamento.isBefore(horaAbertura) || dataAgendamento.isAfter(horaFechamento)) {
            throw new ValidacaoException("Horário de funcionamento é das 06:00 às 21:00.");
        }

        var aluno = alunoRepository.getReferenceById(dados.idAluno());
        if (!aluno.isAtivo()) {
            throw new ValidacaoException("Aluno está inativo no sistema.");
        }

        if (dados.idInstrutor() != null) {
            var instrutor = instructorRepository.getReferenceById(dados.idInstrutor());
            if (!instrutor.isAtivo()) {
                throw new ValidacaoException("Instrutor está inativo no sistema.");
            }
            if (agendamentoRepository.existsByInstrutorIdAndDataAndMotivoCancelamentoIsNull(dados.idInstrutor(), dataAgendamento)) {
                throw new ValidacaoException("Instrutor já possui outra instrução neste horário.");
            }
        }

        if (agendamentoRepository.countByAlunoIdAndDataDia(dados.idAluno(), dataAgendamento) >= 2) {
            throw new ValidacaoException("Aluno já possui duas instruções agendadas para este dia.");
        }
    }

    private Instructor escolherInstrutor(AgendamentoDTO dados) {
        if (dados.idInstrutor() != null) {
            return instructorRepository.getReferenceById(dados.idInstrutor());
        }

        List<Instructor> instrutoresDisponiveis = instructorRepository.findAllByAtivoTrue().stream()
                .filter(i -> !agendamentoRepository.existsByInstrutorIdAndDataAndMotivoCancelamentoIsNull(i.getId(), dados.data()))
                .toList();

        if (instrutoresDisponiveis.isEmpty()) {
            return null;
        }

        return instrutoresDisponiveis.get((int) (Math.random() * instrutoresDisponiveis.size()));
    }
}