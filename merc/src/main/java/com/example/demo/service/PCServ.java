package com.example.demo.service;

import com.example.demo.model.PCModel;
import com.example.demo.model.ProdutoModel;
import com.example.demo.repository.PCRepos;
import com.example.demo.repository.ProdutoRepos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PCServ {

    final PCRepos pcRepos;

    public PCServ(PCRepos pcRepos) {
        this.pcRepos = pcRepos;
    }

    @Transactional
    public PCModel save(PCModel pcModel) {
        return pcRepos.save(pcModel);
    }
    public Page<PCModel> findAll(Pageable pageable) {
        return pcRepos.findAll(pageable);
    }
    public Optional<PCModel> findById(Long idPC) {
        return pcRepos.findById(idPC);
    }

    @Transactional
    public void delete(PCModel pcModel) {
        pcRepos.delete(pcModel);
    }

}
