package br.unip.ciberpizza.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.ciberpizza.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, UUID> {
}
