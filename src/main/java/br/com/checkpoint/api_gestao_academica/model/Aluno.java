package br.com.checkpoint.api_gestao_academica.model;

import br.com.checkpoint.api_gestao_academica.model.dto.AlunoRegisterDTO;
import br.com.checkpoint.api_gestao_academica.model.dto.AlunoUpdateDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Aluno")
@Table(name = "alunos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Aluno(AlunoRegisterDTO data) {
        this.ativo = true;
        this.nome = data.nome();
        this.email = data.email();
        this.telefone = data.telefone();
        this.cpf = data.cpf();
        this.endereco = new Endereco(data.endereco());
    }

    public void updateInfo(AlunoUpdateDTO data) {
        if (data.nome() != null) {
            this.nome = data.nome();
        }
        if (data.telefone() != null) {
            this.telefone = data.telefone();
        }
        if (data.endereco() != null) {
            this.endereco.updateInfo(data.endereco());
        }
    }

    public void delete() {
        this.ativo = false;
    }
}
