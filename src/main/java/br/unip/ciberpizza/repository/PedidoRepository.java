package br.unip.ciberpizza.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.ciberpizza.model.Cliente;
import br.unip.ciberpizza.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
