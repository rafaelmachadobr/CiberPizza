package br.unip.ciberpizza.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Pedido encontrarPedidoPorNumero(UUID numero) {
        return pedidoRepository.findById(numero).orElse(null);
    }

    public Pedido atualizarPedido(UUID numero, Pedido pedidoAtualizado) {
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

    public void deletarPedido(UUID numero) {
        pedidoRepository.deleteById(numero);
    }
}