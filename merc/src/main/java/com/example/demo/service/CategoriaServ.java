package com.example.demo.service;

import com.example.demo.model.CategoriaModel;
import com.example.demo.repository.CategoriaRepos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class CategoriaServ {

    final CategoriaRepos categoriaRepos;

    public CategoriaServ(CategoriaRepos categoriaRepos) {
        this.categoriaRepos = categoriaRepos;
    }

    @Transactional
    public CategoriaModel save(CategoriaModel categoriaModel) {
        return categoriaRepos.save(categoriaModel);
    }
    public boolean existsByNomeCateg(String nomeCateg) {
        return categoriaRepos.existsByNomeCateg(nomeCateg);
    }
    public Page<CategoriaModel> findAll(Pageable pageable) {
        return categoriaRepos.findAll(pageable);
    }
    public Optional<CategoriaModel> findById(Long idProd) {
        return categoriaRepos.findById(idProd);
    }

    @Transactional
    public void delete(CategoriaModel categoriaModel) {
        categoriaRepos.delete(categoriaModel);
    }

}
