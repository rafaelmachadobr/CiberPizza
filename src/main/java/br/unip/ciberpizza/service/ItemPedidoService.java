package br.unip.ciberpizza.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ciberpizza.model.ItemPedido;
import br.unip.ciberpizza.model.Pedido;
import br.unip.ciberpizza.model.Tamanho;
import br.unip.ciberpizza.model.Tipo;
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

    public List<ItemPedido> encontrarItensPedidoPorPedido(Pedido pedido) {
        return itemPedidoRepository.findByPedido(pedido);
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

    public Double calcularValorTotal(List<ItemPedido> itensPedido) {
        double valorTotal = 0;

        for (ItemPedido itemPedido : itensPedido) {
            Double precoItem = itemPedido.getProduto().getValor();
            Tamanho tamanho = itemPedido.getTamanho();
            Tipo tipo = itemPedido.getProduto().getTipo();

            if (tamanho.equals(Tamanho.MEDIA) && tipo.equals(Tipo.PIZZA)) {
                precoItem += 10;
            } else if (tamanho.equals(Tamanho.GRANDE) && tipo.equals(Tipo.PIZZA)) {
                precoItem += 20;
            } else if (tamanho.equals(Tamanho.MEDIA) && tipo.equals(Tipo.BEBIDA)) {
                precoItem += 2.5;
            } else if (tamanho.equals(Tamanho.GRANDE) && tipo.equals(Tipo.BEBIDA)) {
                precoItem += 5;
            }

            valorTotal += itemPedido.getQuantidade() * precoItem;
        }

        return valorTotal;
    }
}