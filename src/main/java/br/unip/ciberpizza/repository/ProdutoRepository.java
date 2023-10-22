package br.unip.ciberpizza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.unip.ciberpizza.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
    @Query("SELECT p FROM Produto p ORDER BY p.tipo DESC, p.nome ASC")
    public List<Produto> findAllOrderedByTipoAndNome();
}
