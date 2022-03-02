package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "pc")
public class PCModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPC;
    private Long idProd;
    private Long idCli;
    private LocalDateTime registrationDate;

    public Long getIdPC() {
        return idPC;
    }
    public void setIdPC(Long idPC) {
        this.idPC = idPC;
    }
    public Long getIdProd() {
        return idProd;
    }
    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }
    public Long getIdCli() {
        return idCli;
    }
    public void setIdCli(Long idCli) {
        this.idCli = idCli;
    }
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
}
