package com.example.demo.dto;

import com.example.demo.model.CategoriaModel;

import javax.validation.constraints.NotNull;

public class ProdutoDto {

    @NotNull
    private String nomeProd;
    @NotNull
    private String descricaoProd;
    @NotNull
    private Double precoProd;
    @NotNull
    private Long idCateg;

    public String getNomeProd() {
        return nomeProd;
    }
    public void setNomeProd(String nomeProd) {
        this.nomeProd = nomeProd;
    }
    public String getDescricaoProd() {
        return descricaoProd;
    }
    public void setDescricaoProd(String descricaoProd) {
        this.descricaoProd = descricaoProd;
    }
    public Double getPrecoProd() {
        return precoProd;
    }
    public void setPrecoProd(Double precoProd) {
        this.precoProd = precoProd;
    }
    public Long getIdCateg() {
        return idCateg;
    }
    public void setIdCateg(Long idCateg) {
        this.idCateg = idCateg;
    }
}
