package com.senacead.confeitaria.controller;

import com.senacead.confeitaria.model.Ingrediente;
import com.senacead.confeitaria.model.Receita;
import com.senacead.confeitaria.service.IngredienteService;
import com.senacead.confeitaria.service.ReceitaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReceitaController {

    @Autowired
    ReceitaService receitaService;

    @Autowired
    IngredienteService ingredienteService;

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/inicio")
    public String inicio2() {
        return "index";
    }

    @GetMapping("/inserir-receita")
    public String cadastro(Model model) {
        model.addAttribute("receita", new Receita());
        return "cadastro";
    }

    @PostMapping("/gravar-receita")
    public String processarForm(Model model, @ModelAttribute Receita receita) {
        if (receita.getId() != null) {
            receitaService.atualizar(receita.getId(), receita);
        } else {
            receitaService.criar(receita);
        }
        return "redirect:/listagem";
    }

    @GetMapping("/listagem")
    public String listagemReceitas(Model model) {
        model.addAttribute("receitas", receitaService.buscarTodos());
        return "listagem";
    }

    @GetMapping("/exibir")
    public String mostraDetalhesReceita(Model model, @RequestParam String id) {
        Integer idReceita = Integer.parseInt(id);

        Receita registroEncontrado = receitaService.buscarPorId(idReceita);

        List<Ingrediente> ingredientesEncontrados = ingredienteService.buscarTodosPeloIdReceita(idReceita);

        model.addAttribute("receita", registroEncontrado);
        model.addAttribute("ingrediente", new Ingrediente());
        model.addAttribute("ingredientes", ingredientesEncontrados);
        return "detalhes";
    }

    @GetMapping("/excluir-receita")
    public String excluirReceita(Model model, @RequestParam String id) {
        Integer idReceita = Integer.parseInt(id);
        receitaService.excluir(idReceita);
        return "redirect:/listagem";
    }

    @GetMapping("/alterar-receita")
    public String alterarReceita(Model model, @RequestParam String id) {
        Integer idReceita = Integer.parseInt(id);
        model.addAttribute("receita", receitaService.buscarPorId(idReceita));
        return "cadastro";
    }
}