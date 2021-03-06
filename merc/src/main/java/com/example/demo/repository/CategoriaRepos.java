package com.example.demo.repository;

import com.example.demo.model.CategoriaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepos extends JpaRepository<CategoriaModel, Long> {

    boolean existsByNomeCateg(String nomeCateg);

}
