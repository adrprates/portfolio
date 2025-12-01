package com.portfolio.portfolio.repository;

import com.portfolio.portfolio.model.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadeRepository extends JpaRepository<Habilidade, Integer> {
}