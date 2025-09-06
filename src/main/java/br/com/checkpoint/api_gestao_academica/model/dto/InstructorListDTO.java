package br.com.checkpoint.api_gestao_academica.model.dto;

import br.com.checkpoint.api_gestao_academica.model.Especialidade;
import br.com.checkpoint.api_gestao_academica.model.Instructor;

public record InstructorListDTO(
        Long id,
        String nome,
        String email,
        String cnh,
        Especialidade especialidade
) {
    public InstructorListDTO(Instructor instructor) {
        this(
            instructor.getId(),
            instructor.getNome(),
            instructor.getEmail(),
            instructor.getCnh(),
            instructor.getEspecialidade()
        );
    }
}