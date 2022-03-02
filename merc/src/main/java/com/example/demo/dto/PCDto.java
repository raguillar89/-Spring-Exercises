package com.example.demo.dto;

import javax.validation.constraints.NotNull;

public class PCDto {

    @NotNull
    private Long idProd;
    @NotNull
    private Long idCli;

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
}
