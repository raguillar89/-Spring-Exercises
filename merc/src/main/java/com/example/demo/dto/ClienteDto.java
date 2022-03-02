package com.example.demo.dto;

import javax.validation.constraints.NotNull;

public class ClienteDto {

    @NotNull
    private String nomeCli;
    @NotNull
    private String cpf;
    @NotNull
    private String dataNascimento;

    public String getNomeCli() {
        return nomeCli;
    }
    public void setNomeCli(String nomeCli) {
        this.nomeCli = nomeCli;
    }
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
