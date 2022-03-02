package com.example.demo.dto;

import javax.validation.constraints.NotNull;

public class CategoriaDto {

    @NotNull
    private String nomeCateg;

    public String getNomeCateg() {
        return nomeCateg;
    }
    public void setNomeCateg(String nomeCateg) {
        this.nomeCateg = nomeCateg;
    }

}
