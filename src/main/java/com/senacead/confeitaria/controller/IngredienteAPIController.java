package com.senacead.confeitaria.controller;

import com.senacead.confeitaria.model.Ingrediente;
import com.senacead.confeitaria.service.IngredienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteAPIController {

    @Autowired
    IngredienteService ingredienteService;

    @PostMapping("/adicionar")
    public ResponseEntity<Ingrediente> criar(@RequestBody Ingrediente ingrediente) {
        Ingrediente novoIngrediente = ingredienteService.criar(ingrediente);
        return new ResponseEntity<>(novoIngrediente, HttpStatus.CREATED);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id) {
        ingredienteService.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{idReceita}")
    public ResponseEntity<List<Ingrediente>> listarIngredientes(@PathVariable Integer idReceita) {
        List<Ingrediente> lista = ingredienteService.buscarTodosPeloIdReceita(idReceita);
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}