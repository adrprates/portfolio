package com.portfolio.portfolio.service;

import com.portfolio.portfolio.model.Tecnologia;

import java.util.List;

public interface TecnologiaService {
    List<Tecnologia> getAll();
    Tecnologia getById(Integer id);
    void save(Tecnologia tecnologia);
    void deleteById(Integer id);
}
