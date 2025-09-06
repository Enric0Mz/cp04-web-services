package br.com.checkpoint.api_gestao_academica.model.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AlunoUpdateDTO(
        @NotBlank
        String nome,

        @NotBlank
        String telefone,

        @NotNull
        @Valid
        EnderecoDTO endereco
) {
}
