package com.senacead.confeitaria.controller;

import com.senacead.confeitaria.model.Receita;
import com.senacead.confeitaria.service.ReceitaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receitas")
public class ReceitaAPIController {

    @Autowired
    ReceitaService receitaService;

    @PostMapping("/adicionar")
    public ResponseEntity<Receita> criar(@RequestBody Receita receita) {
        Receita novaReceita = receitaService.criar(receita);
        return new ResponseEntity<>(novaReceita, HttpStatus.CREATED);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Receita> pesquisar(@PathVariable Integer id) {
        Receita receitaEncontrada = receitaService.buscarPorId(id);
        return new ResponseEntity<>(receitaEncontrada, HttpStatus.OK);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<Receita>> listar() {
        List<Receita> listaTodasReceitas = receitaService.buscarTodos();
        return new ResponseEntity<>(listaTodasReceitas, HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Receita> editarReceita(@PathVariable Integer id, @RequestBody Receita receita) {
        Receita receitaAtualizada = receitaService.atualizar(id, receita);
        return new ResponseEntity<>(receitaAtualizada, HttpStatus.OK);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        receitaService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}