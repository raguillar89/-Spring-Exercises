package com.api.cliente.services;

import com.api.cliente.models.ClienteModel;
import com.api.cliente.repositories.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public ClienteModel save(ClienteModel clienteModel) {
        return clienteRepository.save(clienteModel);
    }

    public boolean existsByClient(String client) {
        return clienteRepository.existsByClient(client);
    }
    public boolean existsByCpf(String cpf) {
        return clienteRepository.existsByCpf(cpf);
    }

    public Page<ClienteModel> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }
    public Optional<ClienteModel> findById(UUID id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public void delete(ClienteModel clienteModel) {
        clienteRepository.delete(clienteModel);
    }
}