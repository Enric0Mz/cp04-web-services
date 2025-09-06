package br.com.checkpoint.api_gestao_academica.model.dto;

import br.com.checkpoint.api_gestao_academica.model.Aluno;

public record AlunoListDTO(
        Long id,
        String nome,
        String email,
        String cpf
) {
    public AlunoListDTO(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getCpf());
    }
}
