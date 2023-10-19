package br.unip.ciberpizza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.ciberpizza.model.ItemPedido;
import br.unip.ciberpizza.model.Pedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, String> {
    List<ItemPedido> findByPedido(Pedido pedido);
}
