package com.example.demo.service;

import com.example.demo.model.ProdutoModel;
import com.example.demo.repository.ProdutoRepos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;


@Service
public class ProdutoServ {

    final ProdutoRepos produtoRepos;

    public ProdutoServ(ProdutoRepos produtoRepos) {
        this.produtoRepos = produtoRepos;
    }

    @Transactional
    public ProdutoModel save(ProdutoModel produtoModel) {
        return produtoRepos.save(produtoModel);
    }
    public boolean existsByNomeProd(String nomeProd) {
        return produtoRepos.existsByNomeProd(nomeProd);
    }
    public Page<ProdutoModel> findAll(Pageable pageable) {
        return produtoRepos.findAll(pageable);
    }
    public Optional<ProdutoModel> findById(Long idProd) {
        return produtoRepos.findById(idProd);
    }

    @Transactional
    public void delete(ProdutoModel produtoModel) {
        produtoRepos.delete(produtoModel);
    }

}
