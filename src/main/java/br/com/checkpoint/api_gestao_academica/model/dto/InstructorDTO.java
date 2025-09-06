package br.com.checkpoint.api_gestao_academica.model.dto;

import br.com.checkpoint.api_gestao_academica.model.Especialidade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InstructorDTO(
        @NotBlank(message = "Nome é obrigatório")
        String nome,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "CNH é obrigatória")
        String cnh,

        @NotNull(message = "Especialidade é obrigatória")
        Especialidade especialidade,

        @NotNull(message = "Endereço é obrigatório")
        @Valid // Valida também os campos do DTO de Endereço
        EnderecoDTO endereco
) {}