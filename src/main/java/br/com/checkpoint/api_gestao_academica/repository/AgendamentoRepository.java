package br.com.checkpoint.api_gestao_academica.repository;

import br.com.checkpoint.api_gestao_academica.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    boolean existsByInstrutorIdAndDataAndMotivoCancelamentoIsNull(Long idInstrutor, LocalDateTime data);

    boolean existsByAlunoIdAndDataAndMotivoCancelamentoIsNull(Long idAluno, LocalDateTime data);

    @Query("SELECT COUNT(a) FROM Agendamento a WHERE a.aluno.id = :idAluno AND FUNCTION('DATE', a.data) = FUNCTION('DATE', :data) AND a.motivoCancelamento IS NULL")
    long countByAlunoIdAndDataDia(Long idAluno, LocalDateTime data);
}
