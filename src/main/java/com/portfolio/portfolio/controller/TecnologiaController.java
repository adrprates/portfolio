package com.portfolio.portfolio.controller;

import com.portfolio.portfolio.model.Tecnologia;
import com.portfolio.portfolio.service.TecnologiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/tecnologia")
public class TecnologiaController {

    @Autowired
    private TecnologiaService tecnologiaService;

    @GetMapping({"","/"})
    public String verHomePage(Model model) {
        model.addAttribute("list", tecnologiaService.getAll());
        return "tecnologia/listar";
    }

    @GetMapping("/criar")
    public String criar(Model model) {
        model.addAttribute("tecnologia", new Tecnologia());
        return "tecnologia/criar";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute @Valid Tecnologia tecnologia, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "tecnologia/criar";
        }
        tecnologiaService.save(tecnologia);
        return "redirect:/tecnologia";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        Tecnologia tecnologia = tecnologiaService.getById(id);
        model.addAttribute("tecnologia", tecnologia);
        return "tecnologia/editar";
    }

    @PostMapping("/atualizar")
    public String atualizar(@ModelAttribute @Valid Tecnologia tecnologia, BindingResult resultado) {
        if (resultado.hasErrors()) {
            return "tecnologia/editar";
        }
        tecnologiaService.save(tecnologia);
        return "redirect:/tecnologia";
    }

    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            tecnologiaService.deleteById(id);
            redirectAttributes.addFlashAttribute("successMessage", "Tecnologia removido com sucesso!");
        } catch (IllegalStateException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Não é possível excluir a tecnologia, pois ela está associada a um ou mais projetos.");
        }
        return "redirect:/tecnologia";
    }
}