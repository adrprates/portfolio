package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.model.Projeto;
import com.portfolio.portfolio.model.ProjetoTecnologia;
import com.portfolio.portfolio.model.Tecnologia;
import com.portfolio.portfolio.service.ProjetoService;
import com.portfolio.portfolio.service.TecnologiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private TecnologiaService tecnologiaService;

    @GetMapping({"", "/"})
    public String listar(Model model) {
        List<Projeto> projetos = projetoService.getAll();
        model.addAttribute("list", projetos);
        return "projeto/listar";
    }

    @GetMapping("/criar")
    public String criar(Model model) {
        model.addAttribute("projeto", new Projeto());
        model.addAttribute("todasTecnologias", tecnologiaService.getAll());
        return "projeto/criar";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute @Valid Projeto projeto,
                         BindingResult bindingResult,
                         @RequestParam(value = "tecnologiaIds", required = false) List<Integer> tecnologiaIds,
                         RedirectAttributes redirectAttributes) {

        if (tecnologiaIds == null || tecnologiaIds.isEmpty()) {
            redirectAttributes.addFlashAttribute("tecnologiaError", "O projeto deve ter pelo menos uma tecnologia.");
            return "redirect:/projeto/criar";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.projeto", bindingResult);
            redirectAttributes.addFlashAttribute("projeto", projeto);
            return "redirect:/projeto/criar";
        }

        List<ProjetoTecnologia> projetoTecnologias = tecnologiaIds.stream().map(id -> {
            Tecnologia tec = tecnologiaService.getById(id);
            ProjetoTecnologia pt = new ProjetoTecnologia();
            pt.setProjeto(projeto);
            pt.setTecnologia(tec);
            return pt;
        }).toList();

        projeto.setTecnologias(projetoTecnologias);
        projetoService.save(projeto);
        return "redirect:/projeto";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Projeto projeto = projetoService.getById(id);
        model.addAttribute("projeto", projeto);
        model.addAttribute("todasTecnologias", tecnologiaService.getAll());
        return "projeto/editar";
    }

    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute @Valid Projeto projeto,
                            BindingResult bindingResult,
                            @RequestParam(value = "tecnologiaIds", required = false) List<Integer> tecnologiaIds,
                            RedirectAttributes redirectAttributes) {

        if (tecnologiaIds == null || tecnologiaIds.isEmpty()) {
            redirectAttributes.addFlashAttribute("tecnologiaError", "O projeto deve ter pelo menos uma tecnologia.");
            return "redirect:/projeto/editar/" + projeto.getId();
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.projeto", bindingResult);
            redirectAttributes.addFlashAttribute("projeto", projeto);
            return "redirect:/projeto/editar/" + projeto.getId();
        }

        projeto.getTecnologias().clear();
        List<ProjetoTecnologia> projetoTecnologias = tecnologiaIds.stream().map(id -> {
            Tecnologia tec = tecnologiaService.getById(id);
            ProjetoTecnologia pt = new ProjetoTecnologia();
            pt.setProjeto(projeto);
            pt.setTecnologia(tec);
            return pt;
        }).toList();

        projeto.setTecnologias(projetoTecnologias);
        projetoService.save(projeto);
        return "redirect:/projeto";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            projetoService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Projeto excluído com sucesso!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/projeto";
    }

    @GetMapping("/{projetoId}/remover-tecnologia/{tecnologiaId}")
    public String removerTecnologia(@PathVariable Integer projetoId,
                                    @PathVariable Integer tecnologiaId) {
        Projeto projeto = projetoService.getById(projetoId);
        projeto.getTecnologias().removeIf(pt -> pt.getTecnologia().getId().equals(tecnologiaId));
        projetoService.save(projeto);
        return "redirect:/projeto/editar/" + projetoId;
    }
}