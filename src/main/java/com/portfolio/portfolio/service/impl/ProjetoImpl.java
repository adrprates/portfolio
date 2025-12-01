package com.portfolio.portfolio.service.impl;

import com.portfolio.portfolio.model.Projeto;
import com.portfolio.portfolio.model.ProjetoTecnologia;
import com.portfolio.portfolio.repository.ProjetoRepository;
import com.portfolio.portfolio.service.ProjetoService;
import com.portfolio.portfolio.service.TecnologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoImpl implements ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Override
    public List<Projeto> getAll() {
        return projetoRepository.findAll();
    }

    @Override
    public Projeto getById(Integer id) {
        return projetoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado: " + id));
    }

    @Override
    public void save(Projeto projeto) {
        if(projeto.getTecnologias() != null){
            for(ProjetoTecnologia pt : projeto.getTecnologias()){
                pt.setProjeto(projeto);
            }
        }

        projetoRepository.save(projeto);
    }

    @Override
    public void deleteById(Integer id) {
        Projeto projeto = getById(id);
        projetoRepository.delete(projeto);
    }
}