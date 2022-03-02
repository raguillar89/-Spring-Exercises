package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "produto")
public class ProdutoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProd;
    private String nomeProd;
    private String descricaoProd;
    private Double precoProd;
    private LocalDateTime registrationDate;
    private Long idCateg;

    public Long getIdProd() {
        return idProd;
    }
    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }
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
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    public Long getIdCateg() {
        return idCateg;
    }
    public void setIdCateg(Long idCateg) {
        this.idCateg = idCateg;
    }
}
