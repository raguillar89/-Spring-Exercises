package com.example.demo.repository;

import com.example.demo.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepos extends JpaRepository<ProdutoModel, Long> {

    boolean existsByNomeProd(String nomeProd);

}
