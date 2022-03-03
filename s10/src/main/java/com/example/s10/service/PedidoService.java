package com.example.s10.service;

import com.example.s10.model.PedidoModel;
import com.example.s10.repository.PedidoRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

        private PedidoRepository repository;

        public PedidoService(PedidoRepository repository){
            this.repository = repository;
        }

        public Iterable<PedidoModel> listaPedido(){
            return repository.findAll();
        }

        public PedidoModel adicionaPedido(PedidoModel pedido){
            return repository.save(pedido);
        }
}
