package br.edu.ifsp.prw3.conserto_api.usuario;

import jakarta.validation.constraints.NotBlank;

public record dadosAutenticacao(

        @NotBlank
        String login,

        @NotBlank
        String senha) {

}

