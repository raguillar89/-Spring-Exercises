package com.example.s10.dto;

import com.example.s10.model.PedidoModel;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class NovoPedidoDto {

        @NotNull @NotEmpty
        private String nome;
        @NotNull @NotEmpty
        private String descricao;
        @NotNull @NotEmpty
        private String valor;
        @org.hibernate.validator.constraints.br.CPF
        private String cpf;

        public PedidoModel converter(){
            PedidoModel pedido = new PedidoModel();
            pedido.setNome(nome);
            pedido.setDescricao(descricao);
            pedido.setValor(Double.parseDouble(valor));
            pedido.setDataPedido(LocalDate.now());
            return pedido;
        }
}
