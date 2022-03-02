package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "categoria")
public class CategoriaModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCateg;
    private String nomeCateg;
    private LocalDateTime registrationDate;

    public Long getIdCateg() {
        return idCateg;
    }
    public void setIdCateg(Long idCateg) {
        this.idCateg = idCateg;
    }
    public String getNomeCateg() {
        return nomeCateg;
    }
    public void setNomeCateg(String nomeCateg) {
        this.nomeCateg = nomeCateg;
    }
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
