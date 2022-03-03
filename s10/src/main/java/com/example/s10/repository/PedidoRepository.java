package com.example.s10.repository;

import com.example.s10.model.PedidoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends CrudRepository<PedidoModel, Long>{
}
