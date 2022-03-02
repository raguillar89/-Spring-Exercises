package com.example.demo.service;

import com.example.demo.model.ClienteModel;
import com.example.demo.repository.ClienteRepos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ClienteServ {

    final ClienteRepos clienteRepos;

    public ClienteServ(ClienteRepos clienteRepos) {
        this.clienteRepos = clienteRepos;
    }

    @Transactional
    public ClienteModel save(ClienteModel clienteModel) {
        return clienteRepos.save(clienteModel);
    }
    public boolean existsByNomeCli(String nomeCli) {
        return clienteRepos.existsByNomeCli(nomeCli);
    }
    public Page<ClienteModel> findAll(Pageable pageable) {
        return clienteRepos.findAll(pageable);
    }
    public Optional<ClienteModel> findById(Long idCli) {
        return clienteRepos.findById(idCli);
    }

    @Transactional
    public void delete(ClienteModel clienteModel) {
        clienteRepos.delete(clienteModel);
    }

}
