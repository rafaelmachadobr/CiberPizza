package br.unip.ciberpizza.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.ciberpizza.model.Bebida;

public interface BebidaRepository extends JpaRepository<Bebida, UUID> {
}
