package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.service.HabilidadeService;
import com.portfolio.portfolio.service.ProjetoService;
import com.portfolio.portfolio.service.TecnologiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private HabilidadeService habilidadeService;

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private TecnologiaService tecnologiaService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("projetos", projetoService.getAll());
        model.addAttribute("habilidades", habilidadeService.getAll());
        model.addAttribute("tecnologias", tecnologiaService.getAll());
        return "portfolio";
    }


}