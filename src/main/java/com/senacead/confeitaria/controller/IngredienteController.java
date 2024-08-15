package com.senacead.confeitaria.controller;

import com.senacead.confeitaria.model.Ingrediente;
import com.senacead.confeitaria.model.Receita;
import com.senacead.confeitaria.service.IngredienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IngredienteController {

    @Autowired
    IngredienteService ingredienteService;

    @PostMapping("/gravar-ingrediente")
    public String processarForm(Model model, @ModelAttribute Receita receita, @ModelAttribute Ingrediente ingrediente) {
        ingrediente.setReceita(receita);
        ingredienteService.criar(ingrediente);
        return "redirect:/listagem";
    }

    @GetMapping("/excluir-ingrediente")
    public String excluirIngrediente(Model model, @RequestParam String id) {
        Integer idIngrediente = Integer.parseInt(id);
        ingredienteService.excluir(idIngrediente);
        return "redirect:/listagem";
    }
}