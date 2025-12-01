package com.portfolio.portfolio.service;

import com.portfolio.portfolio.model.Projeto;

import java.util.List;

public interface ProjetoService {
    List<Projeto> getAll();
    Projeto getById(Integer id);
    void save(Projeto projeto);
    void deleteById(Integer id);
}