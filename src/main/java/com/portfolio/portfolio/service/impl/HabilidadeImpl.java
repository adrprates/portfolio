package com.portfolio.portfolio.service.impl;

import com.portfolio.portfolio.model.Habilidade;
import com.portfolio.portfolio.repository.HabilidadeRepository;
import com.portfolio.portfolio.service.HabilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabilidadeImpl implements HabilidadeService {

    @Autowired
    private HabilidadeRepository habilidadeRepository;

    @Override
    public List<Habilidade> getAll() {
        return habilidadeRepository.findAll();
    }

    @Override
    public Habilidade getById(Integer id) {
        Optional<Habilidade> optional = habilidadeRepository.findById(id);
        Habilidade habilidade = null;
        if(optional.isPresent()){
            habilidade = optional.get();
        } else {
            throw new RuntimeException("Habilidade não encontrada para o id: " + id);
        }
        return habilidade;
    }

    @Override
    public void save(Habilidade habilidade) {
        habilidadeRepository.save(habilidade);
    }

    @Override
    public void deleteById(Integer id) {
        habilidadeRepository.deleteById(id);
    }
}