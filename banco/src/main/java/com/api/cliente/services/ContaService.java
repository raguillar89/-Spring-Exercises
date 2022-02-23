package com.api.cliente.services;

import com.api.cliente.models.ContaModel;
import com.api.cliente.repositories.ContaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContaService {

    final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Transactional
    public ContaModel save(ContaModel contaModel) {
        return contaRepository.save(contaModel);
    }

    public boolean existsByAgencia(String agencia) {
        return contaRepository.existsByAgencia(agencia);
    }
    public boolean existsByConta(String conta) {
        return contaRepository.existsByConta(conta);
    }

    public Page<ContaModel> findAll(Pageable pageable) {
        return contaRepository.findAll(pageable);
    }
    public Optional<ContaModel> findById(UUID id) {
        return contaRepository.findById(id);
    }

    @Transactional
    public void delete(ContaModel contaModel) {
        contaRepository.delete(contaModel);
    }
}