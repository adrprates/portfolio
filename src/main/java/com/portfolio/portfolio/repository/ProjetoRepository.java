package com.portfolio.portfolio.repository;

import com.portfolio.portfolio.model.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Projeto p JOIN p.tecnologias t " +
            "WHERE t.id = :tecnologiaId")
    boolean existsByTecnologiaId(@Param("tecnologiaId") Integer tecnologiaId);
}