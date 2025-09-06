package br.com.checkpoint.api_gestao_academica.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
        @NotBlank(message = "Logradouro é obrigatório")
        String logradouro,

        String numero,
        String complemento,
        @NotBlank(message = "Bairro é obrigatório")
        String bairro,

        @NotBlank(message = "Cidade é obrigatória")
        String cidade,

        @NotBlank(message = "UF é obrigatório")
        @Size(min = 2, max = 2, message = "UF deve ter 2 caracteres")
        String uf,

        @NotBlank(message = "CEP é obrigatório")
        @Pattern(regexp = "\\d{8}", message = "CEP deve conter 8 dígitos")
        String cep
) {}