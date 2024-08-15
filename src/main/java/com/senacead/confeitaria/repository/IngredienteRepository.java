package com.senacead.confeitaria.repository;

import com.senacead.confeitaria.model.Ingrediente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {

    List<Ingrediente> findByReceitaId(Integer id);
}