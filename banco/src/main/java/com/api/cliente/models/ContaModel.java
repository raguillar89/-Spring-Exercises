package com.api.cliente.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CONTA")
public class ContaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = false, length = 100)
    private String idCliente;
    @Column(nullable = false, unique = true, length = 4)
    private String agencia;
    @Column(nullable = false, unique = true, length = 7)
    private String conta;
    @Column(nullable = true, unique = false, length = 20)
    private float saldo;
    @Column(nullable = false)
    private LocalDateTime registrationDate;

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getIdCliente() {
        return idCliente;
    }
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
    public String getAgencia() {
        return agencia;
    }
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
    public String getConta() {
        return conta;
    }
    public void setConta(String conta) {
        this.conta = conta;
    }
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    private float transfer;
    private float reduceSaldo;
    private float profitSaldo;

    public float getTransfer() {
        return transfer;
    }
    public void setTransfer(float transfer) {
        this.transfer = transfer;
    }
    public float getReduceSaldo(){
        return saldo - transfer;
    }
    public float setReduceSaldo(float reduceSaldo){
        this.reduceSaldo = saldo - transfer;
        return reduceSaldo;
    }
    public float getProfitSaldo(){
        return saldo + transfer;
    }
    public float setProfitSaldo(float profitSaldo){
        this.profitSaldo = saldo + transfer;
        return profitSaldo;
    }

}
