package com.senacead.confeitaria.model;

import java.util.ArrayList;
import java.util.List;

public class Dados {

    private static final List<Receita> listaReceitas = new ArrayList<>();
    private static final List<Ingrediente> listaIngredientes = new ArrayList<>();

    public static void adicionarReceita(Receita receita) {
        receita.setId(listaReceitas.size() + 1);
        listaReceitas.add(receita);
    }

    public static List<Receita> listarReceitas() {
        return listaReceitas;
    }

    public static void excluirReceita(Integer idReceita) {
        listaReceitas.removeIf(r -> r.getId().equals(idReceita));
    }

    public static Receita buscarReceita(Integer idReceita) {
        return listaReceitas.stream().filter(r -> r.getId().equals(idReceita)).findFirst().orElse(null);
    }

    public static void editarReceita(Receita receita) {
        listaReceitas.stream().filter(r -> r.getId().equals(receita.getId())).forEach(r -> {
            r.setNome(receita.getNome());
            r.setDescricao(receita.getDescricao());
        });
    }

    public static void adicionarIngrediente(Ingrediente ingrediente) {
        ingrediente.setId(listaIngredientes.size() + 1);
        listaIngredientes.add(ingrediente);
    }

    public static void excluirIngrediente(Integer idIngrediente) {
        listaIngredientes.removeIf(i -> i.getId().equals(idIngrediente));
    }

    public static List<Ingrediente> listarIngredientes(Integer idReceita) {
        List<Ingrediente> ingredientesEncontrados = new ArrayList<>();
        for (Ingrediente i : listaIngredientes) {
            if (i.getReceita().getId().equals(idReceita)) {
                ingredientesEncontrados.add(i);
            }
        }
        return ingredientesEncontrados;
    }
}