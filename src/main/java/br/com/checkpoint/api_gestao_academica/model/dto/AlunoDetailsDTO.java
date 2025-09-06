package br.com.checkpoint.api_gestao_academica.model.dto;

import br.com.checkpoint.api_gestao_academica.model.Aluno;

public record AlunoDetailsDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        EnderecoDTO endereco
) {
    public AlunoDetailsDTO(Aluno aluno) {
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getTelefone(),
                aluno.getCpf(),
                new EnderecoDTO(
                        aluno.getEndereco().getLogradouro(),
                        aluno.getEndereco().getNumero(),
                        aluno.getEndereco().getComplemento(),
                        aluno.getEndereco().getBairro(),
                        aluno.getEndereco().getCidade(),
                        aluno.getEndereco().getUf(),
                        aluno.getEndereco().getCep()
                )
        );
    }
}