package br.unip.ciberpizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.ciberpizza.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, String> {
}
