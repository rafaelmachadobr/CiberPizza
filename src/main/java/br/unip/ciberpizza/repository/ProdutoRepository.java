package br.unip.ciberpizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.ciberpizza.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
}
