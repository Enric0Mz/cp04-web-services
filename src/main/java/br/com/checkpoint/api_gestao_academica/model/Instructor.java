package br.com.checkpoint.api_gestao_academica.model;

import br.com.checkpoint.api_gestao_academica.model.dto.InstructorDTO;
import br.com.checkpoint.api_gestao_academica.model.dto.InstructorUpdateDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Instructor")
@Table(name = "instructors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    private String cnh;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private boolean ativo;

    public Instructor(InstructorDTO data) {
        this.ativo = true;
        this.nome = data.nome();
        this.email = data.email();
        this.cnh = data.cnh();
        this.especialidade = data.especialidade();
        this.endereco = new Endereco(data.endereco());
    }

    public void updateInfo(InstructorUpdateDTO data) {
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