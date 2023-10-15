package br.unip.ciberpizza.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ciberpizza.model.Produto;
import br.unip.ciberpizza.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto encontrarProdutoPorId(UUID id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public void deletarProduto(UUID id) {
        produtoRepository.deleteById(id);
    }

    public Produto atualizarProduto(UUID id, Produto produtoAtualizado) {
        Produto produtoExistente = produtoRepository.findById(id).orElse(null);

        if (produtoExistente != null) {
            produtoExistente.setNome(produtoAtualizado.getNome());
            produtoExistente.setDescricao(produtoAtualizado.getDescricao());
            produtoExistente.setValor(produtoAtualizado.getValor());

            return produtoRepository.save(produtoExistente);
        } else {
            return null;
        }
    }
}
