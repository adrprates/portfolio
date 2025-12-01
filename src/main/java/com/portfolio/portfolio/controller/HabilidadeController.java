package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.model.Habilidade;
import com.portfolio.portfolio.service.HabilidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/habilidade")
public class HabilidadeController {

    @Autowired
    private HabilidadeService habilidadeService;

    @GetMapping({"","/"})
    public String verHomePage(Model model) {
        model.addAttribute("list", habilidadeService.getAll());
        return "habilidade/listar";
    }

    @GetMapping("/criar")
    public String criar(Model model) {
        model.addAttribute("habilidade", new Habilidade());
        return "habilidade/criar";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute @Valid Habilidade habilidade, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "habilidade/criar";
        }
        habilidadeService.save(habilidade);
        return "redirect:/habilidade";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Habilidade habilidade = habilidadeService.getById(id);
        model.addAttribute("habilidade", habilidade);
        return "habilidade/editar";
    }

    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute @Valid Habilidade habilidade, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "habilidade/editar";
        }
        habilidadeService.save(habilidade);
        return "redirect:/habilidade";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            habilidadeService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Habilidade removido com sucesso!");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Erro ao tentar excluir a habilidade.");
        }
        return "redirect:/habilidade";
    }
}