package com.senacead.confeitaria.service;

import com.senacead.confeitaria.model.Receita;
import com.senacead.confeitaria.repository.ReceitaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceitaService {

    @Autowired
    ReceitaRepository receitaRepository;

    public Receita buscarPorId(Integer id) {
        return receitaRepository.findById(id).orElseThrow();
    }

    public Receita criar(Receita receita) {
        receita.setId(null); // Garante que o ID será gerado automaticamente
        receitaRepository.save(receita);
        return receita;
    }

    public List<Receita> buscarTodos() {
        return receitaRepository.findAll();
    }

    public Receita atualizar(Integer id, Receita receita) {
        Receita receitaEncontrada = buscarPorId(id);

        // Atualiza apenas os campos relevantes
        receitaEncontrada.setNome(receita.getNome());
        receitaEncontrada.setDescricao(receita.getDescricao());
        receitaRepository.save(receitaEncontrada);
        return receitaEncontrada;
    }

    public void excluir(Integer id) {
        Receita receitaEncontrada = buscarPorId(id);
        receitaRepository.deleteById(receitaEncontrada.getId());
    }
}