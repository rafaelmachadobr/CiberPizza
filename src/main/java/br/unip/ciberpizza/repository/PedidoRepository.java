package br.unip.ciberpizza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.unip.ciberpizza.model.Cliente;
import br.unip.ciberpizza.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);

    @Modifying
    @Query("DELETE FROM ItemPedido ip WHERE ip.pedido = :pedido")
    void deleteItemsByPedido(Pedido pedido);

    Pedido findFirstByClienteOrderByNumeroDesc(Cliente cliente);
}
