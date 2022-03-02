package com.example.demo.repository;

import com.example.demo.model.PCModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PCRepos extends JpaRepository<PCModel, Long> {


}
