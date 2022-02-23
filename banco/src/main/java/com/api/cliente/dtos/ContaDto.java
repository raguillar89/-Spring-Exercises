package com.api.cliente.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ContaDto {

    @NotBlank
    @Size(max = 100)
    private String idCliente;
    @NotBlank
    @Size(max = 4)
    private String agencia;
    @NotBlank
    @Size(max = 7)
    private String conta;
    @NotBlank
    @Size(max = 20)
    private float saldo;

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
    public float getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    private float transfer;

    public float getTransfer() {
        return transfer;
    }
    public void setTransfer(float transfer) {
        this.transfer = transfer;
    }
    public float getReduceSaldo(){
        return saldo - transfer;
    }
    public float getProfitSaldo(){
        return saldo + transfer;
    }
}
