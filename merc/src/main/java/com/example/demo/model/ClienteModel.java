package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cliente")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCli;
    private String nomeCli;
    private String cpf;
    private String dataNascimento;
    private LocalDateTime registrationDate;

    public Long getIdCli() {
        return idCli;
    }
    public void setIdCli(Long idCli) {
        this.idCli = idCli;
    }
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
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
