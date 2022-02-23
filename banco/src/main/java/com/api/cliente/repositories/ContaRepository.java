package com.api.cliente.repositories;

import com.api.cliente.models.ContaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContaRepository extends JpaRepository<ContaModel, UUID> {

    boolean existsByAgencia(String agencia);
    boolean existsByConta(String conta);
}