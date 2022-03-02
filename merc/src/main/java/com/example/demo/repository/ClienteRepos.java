package com.example.demo.repository;

import com.example.demo.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepos extends JpaRepository<ClienteModel, Long> {

    boolean existsByNomeCli(String nomeCli);

}
