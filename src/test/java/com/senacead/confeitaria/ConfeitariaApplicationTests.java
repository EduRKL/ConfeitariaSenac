package com.senacead.confeitaria;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.senacead.confeitaria.model.Ingrediente;
import com.senacead.confeitaria.model.Receita;
import com.senacead.confeitaria.service.IngredienteService;
import com.senacead.confeitaria.service.ReceitaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
class ConfeitariaApplicationTests {

    @Autowired
    private ReceitaService receitaService;

    @Autowired
    private IngredienteService ingredienteService;

    private Receita receita;

    @BeforeEach
    void setUp() {
        receita = new Receita();
        receita.setNome("Bolo de Chocolate");
        receita.setDescricao("Um delicioso bolo de chocolate");
    }

    @Test
    @Rollback
    void testCriarReceita() {
        Receita novaReceita = receitaService.criar(receita);
        assertNotNull(novaReceita);
        assertEquals("Bolo de Chocolate", novaReceita.getNome());
    }

    @Test
    @Rollback        
    void testBuscarReceita() {
        Receita novaReceita = receitaService.criar(receita);
        Receita receitaEncontrada = receitaService.buscarPorId(novaReceita.getId());
        assertNotNull(receitaEncontrada);
        assertEquals(novaReceita.getId(), receitaEncontrada.getId());
    }

    @Test
    @Rollback        
    void testAdicionarIngrediente() {
        Receita novaReceita = receitaService.criar(receita);
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setDescricao("2 xícaras de farinha");
        ingrediente.setReceita(novaReceita);

        Ingrediente novoIngrediente = ingredienteService.criar(ingrediente);
        assertNotNull(novoIngrediente);
        assertEquals("2 xícaras de farinha", novoIngrediente.getDescricao());
    }

    @Test
    @Rollback        
    void testExcluirIngrediente() {
        Receita novaReceita = receitaService.criar(receita);
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setDescricao("2 xícaras de farinha");
        ingrediente.setReceita(novaReceita);

        Ingrediente novoIngrediente = ingredienteService.criar(ingrediente);
        assertNotNull(novoIngrediente);

        ingredienteService.excluir(novoIngrediente.getId());
        boolean ingredienteExiste = ingredienteService.buscarTodosPeloIdReceita(novaReceita.getId())
                .stream()
                .anyMatch(ing -> ing.getId().equals(novoIngrediente.getId()));

        assertTrue(!ingredienteExiste);
    }
}