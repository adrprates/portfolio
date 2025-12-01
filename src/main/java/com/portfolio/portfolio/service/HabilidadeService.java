package com.portfolio.portfolio.service;

import com.portfolio.portfolio.model.Habilidade;

import java.util.List;

public interface HabilidadeService {
    List<Habilidade> getAll();
    Habilidade getById(Integer id);
    void save(Habilidade habilidade);
    void deleteById(Integer id);
}
