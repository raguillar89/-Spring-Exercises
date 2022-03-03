package com.example.s10.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="pedido")
public class PedidoModel {

        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "nome")
        private String nome;
        @Column(name = "descricao")
        private String descricao;
        @Column(name = "valor")
        private Double valor;
        @Column(name = "dataPedido")
        private LocalDate dataPedido;

}
