package br.com.checkpoint.api_gestao_academica.model;

import br.com.checkpoint.api_gestao_academica.model.dto.EnderecoDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;

    public Endereco(EnderecoDTO data) {
        this.logradouro = data.logradouro();
        this.numero = data.numero();
        this.complemento = data.complemento();
        this.bairro = data.bairro();
        this.cidade = data.cidade();
        this.uf = data.uf();
        this.cep = data.cep();
    }

    public void updateInfo(EnderecoDTO data) {
        if (data.logradouro() != null) {
            this.logradouro = data.logradouro();
        }
        if (data.numero() != null) {
            this.numero = data.numero();
        }
        if (data.complemento() != null) {
            this.complemento = data.complemento();
        }
        if (data.bairro() != null) {
            this.bairro = data.bairro();
        }
        if (data.cidade() != null) {
            this.cidade = data.cidade();
        }
        if (data.uf() != null) {
            this.uf = data.uf();
        }
        if (data.cep() != null) {
            this.cep = data.cep();
        }
    }
}
