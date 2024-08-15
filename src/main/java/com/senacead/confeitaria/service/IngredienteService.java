package com.senacead.confeitaria.service;

import com.senacead.confeitaria.model.Ingrediente;
import com.senacead.confeitaria.repository.IngredienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredienteService {

    @Autowired
    IngredienteRepository ingredienteRepository;

    public Ingrediente buscarPorId(Integer id) {
        return ingredienteRepository.findById(id).orElseThrow();
    }

    public Ingrediente criar(Ingrediente ingrediente) {
        ingrediente.setId(null);
        ingredienteRepository.save(ingrediente);
        return ingrediente;
    }

    public void excluir(Integer id) {
        Ingrediente ingredienteEncontrado = buscarPorId(id);
        ingredienteRepository.deleteById(ingredienteEncontrado.getId());
    }

    public List<Ingrediente> buscarTodosPeloIdReceita(Integer id) {
        return ingredienteRepository.findByReceitaId(id);
    }
}