package br.unip.ciberpizza.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ciberpizza.model.ItemPedido;
import br.unip.ciberpizza.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
    @Autowired
    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public ItemPedido salvarItemPedido(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public List<ItemPedido> listarItensPedido() {
        return itemPedidoRepository.findAll();
    }

    public ItemPedido encontrarItemPedidoPorId(String id) {
        return itemPedidoRepository.findById(id).orElse(null);
    }

    public ItemPedido atualizarItemPedido(String id, ItemPedido itemPedidoAtualizado) {
        ItemPedido itemPedidoExistente = itemPedidoRepository.findById(id).orElse(null);

        if (itemPedidoExistente != null) {
            itemPedidoExistente.setQuantidade(itemPedidoAtualizado.getQuantidade());
            itemPedidoExistente.setTamanho(itemPedidoAtualizado.getTamanho());
            itemPedidoExistente.setPedido(itemPedidoAtualizado.getPedido());
            itemPedidoExistente.setProduto(itemPedidoAtualizado.getProduto());

            return itemPedidoRepository.save(itemPedidoExistente);
        } else {
            return null;
        }
    }

    public void deletarItemPedido(String id) {
        itemPedidoRepository.deleteById(id);
    }

    private void calcularPrecoComTamanho(ItemPedido itemPedido) {
        switch (itemPedido.getTamanho()) {
            case PEQUENA:
                itemPedido.setPreco(itemPedido.getProduto().getValor());
                break;
            case MEDIA:
                itemPedido.setPreco(itemPedido.getProduto().getValor() * 1.1);
                break;
            case GRANDE:
                itemPedido.setPreco(itemPedido.getProduto().getValor() * 2);
                break;
        }
    }
}