package br.unip.ciberpizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.unip.ciberpizza.model.Cliente;
import br.unip.ciberpizza.model.Pedido;
import br.unip.ciberpizza.service.ClienteService;
import br.unip.ciberpizza.service.PedidoService;

@Controller
@RequestMapping("/historico")
public class HistoricoPedidoController {
    @Autowired
    private final ClienteService clienteService;

    @Autowired
    private final PedidoService pedidoService;

    public HistoricoPedidoController(ClienteService clienteService, PedidoService pedidoService) {
        this.clienteService = clienteService;
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ModelAndView historicoPedidos(@RequestParam(name = "idCliente", required = false) String idCliente) {
        ModelAndView modelAndView = new ModelAndView();

        if (idCliente != null) {
            Cliente cliente = clienteService.encontrarClientePorId(idCliente);

            if (cliente != null) {
                List<Pedido> pedidos = pedidoService.encontrarPedidosPorCliente(cliente);
                Integer numeroDePedidos = pedidos.size();

                if (!pedidos.isEmpty()) {
                    Pedido ultimoPedido = pedidoService.encontrarUltimoPedido(cliente);

                    if (numeroDePedidos == 0 || ultimoPedido == null) {
                        modelAndView.setViewName("historico-pedidos-vazio");
                    } else {
                        modelAndView.setViewName("historico-pedidos");
                        modelAndView.addObject("pedidos", pedidos);
                    }

                } else {
                    modelAndView.setViewName("historico-pedidos-vazio");
                }

                modelAndView.addObject("idCliente", idCliente);

            } else {
                modelAndView.setViewName("redirect:/login");
            }

            return modelAndView;
        }

        return new ModelAndView("redirect:/login");
    }
}