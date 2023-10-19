package br.unip.ciberpizza.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ciberpizza.model.Cliente;
import br.unip.ciberpizza.model.Pedido;
import br.unip.ciberpizza.repository.PedidoRepository;

@Service
public class PedidoService {
    @Autowired
    private final PedidoRepository pedidoRepository;

    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido salvarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido encontrarPedidoPorNumero(Integer numero) {
        return pedidoRepository.findById(numero).orElse(null);
    }

    public List<Pedido> encontrarPedidosPorCliente(Cliente cliente) {
        return pedidoRepository.findByCliente(cliente);
    }

    public Pedido atualizarPedido(Integer numero, Pedido pedidoAtualizado) {
        Pedido pedidoExistente = pedidoRepository.findById(numero).orElse(null);

        if (pedidoExistente != null) {
            pedidoExistente.setMomento(pedidoAtualizado.getMomento());
            pedidoExistente.setValor(pedidoAtualizado.getValor());
            pedidoExistente.setStatus(pedidoAtualizado.getStatus());
            pedidoExistente.setPagamento(pedidoAtualizado.getPagamento());
            pedidoExistente.setCliente(pedidoAtualizado.getCliente());

            return pedidoRepository.save(pedidoExistente);
        } else {
            return null;
        }
    }

    public void deletarPedido(Integer numero) {
        pedidoRepository.deleteById(numero);
    }
}