package br.unip.ciberpizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.unip.ciberpizza.dto.PedidoDTO;
import br.unip.ciberpizza.model.Cliente;
import br.unip.ciberpizza.model.ItemPedido;
import br.unip.ciberpizza.model.Pedido;
import br.unip.ciberpizza.model.StatusPedido;
import br.unip.ciberpizza.service.ClienteService;
import br.unip.ciberpizza.service.ItemPedidoService;
import br.unip.ciberpizza.service.PedidoService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
    @Autowired
    private final ClienteService clienteService;

    @Autowired
    private final PedidoService pedidoService;

    @Autowired
    private final ItemPedidoService itemPedidoService;

    public CarrinhoController(ClienteService clienteService, PedidoService pedidoService,
            ItemPedidoService itemPedidoService) {
        this.clienteService = clienteService;
        this.pedidoService = pedidoService;
        this.itemPedidoService = itemPedidoService;
    }

    @GetMapping
    public ModelAndView carrinho(@RequestParam(name = "idCliente", required = false) String idCliente) {
        if (idCliente != null) {
            ModelAndView modelAndView;

            Cliente cliente = clienteService.encontrarClientePorId(idCliente);
            if (cliente != null) {
                List<Pedido> pedidos = pedidoService.encontrarPedidosPorCliente(cliente);

                if (!pedidos.isEmpty()) {
                    Pedido ultimoPedido = pedidoService.encontrarUltimoPedido(cliente);

                    if (ultimoPedido.getStatus() == StatusPedido.FINALIZADO
                            || ultimoPedido.getStatus() == StatusPedido.CANCELADO) {
                        modelAndView = new ModelAndView("carrinho-vazio");
                        modelAndView.addObject("idCliente", idCliente);
                        return modelAndView;
                    }

                    List<ItemPedido> itensPedido = itemPedidoService.encontrarItensPedidoPorPedido(ultimoPedido);

                    if (!itensPedido.isEmpty()) {
                        modelAndView = new ModelAndView("carrinho");
                        modelAndView.addObject("itensPedido", itensPedido);
                        modelAndView.addObject("pedidoDTO", new PedidoDTO(null));
                        modelAndView.addObject("numeroPedido", ultimoPedido.getNumero());
                        modelAndView.addObject("cliente", cliente);

                        Double valorTotal = itemPedidoService.calcularValorTotal(itensPedido);
                        ultimoPedido.setValor(valorTotal + 10);

                        pedidoService.salvarPedido(ultimoPedido);

                        modelAndView.addObject("valorTotal", valorTotal);
                    } else {
                        modelAndView = new ModelAndView("carrinho-vazio");
                    }
                } else {
                    modelAndView = new ModelAndView("carrinho-vazio");
                }

                modelAndView.addObject("idCliente", idCliente);
            } else {
                modelAndView = new ModelAndView("redirect:/login");
            }

            return modelAndView;
        }

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/removerItem/{idItemPedido}")
    public String removerItemPedido(@PathVariable String idItemPedido,
            @RequestParam(name = "idCliente", required = false) String idCliente) {
        ItemPedido itemPedido = itemPedidoService.encontrarItemPedidoPorId(idItemPedido);

        if (itemPedido != null) {
            itemPedidoService.deletarItemPedido(idItemPedido);
        }

        return "redirect:/carrinho?idCliente=" + idCliente;
    }

}
