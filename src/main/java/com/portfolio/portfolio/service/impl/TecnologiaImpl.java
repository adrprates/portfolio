package com.portfolio.portfolio.service.impl;

import com.portfolio.portfolio.model.Tecnologia;
import com.portfolio.portfolio.repository.ProjetoRepository;
import com.portfolio.portfolio.repository.TecnologiaRepository;
import com.portfolio.portfolio.service.TecnologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnologiaImpl implements TecnologiaService {

    @Autowired
    private TecnologiaRepository tecnologiaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Override
    public List<Tecnologia> getAll() {
        return tecnologiaRepository.findAll();
    }

    @Override
    public Tecnologia getById(Integer id) {
        Optional<Tecnologia> optional = tecnologiaRepository.findById(id);
        Tecnologia tecnologia = null;
        if(optional.isPresent()){
            tecnologia = optional.get();
        } else {
            throw new RuntimeException("Tecnologia não encontrada para o id: " + id);
        }
        return tecnologia;
    }

    @Override
    public void save(Tecnologia tecnologia) {
        tecnologiaRepository.save(tecnologia);
    }

    @Override
    public void deleteById(Integer id) {
        Tecnologia tecnologia = tecnologiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tecnologia não encontrada"));

        boolean existeEmProjeto = projetoRepository.existsByTecnologiaId(tecnologia.getId());

        if (existeEmProjeto) {
            throw new DataIntegrityViolationException(
                    "Tecnologia usado em um ou mais projetos"
            );
        }

        tecnologiaRepository.delete(tecnologia);

    }
}