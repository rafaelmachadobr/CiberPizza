package br.unip.ciberpizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.unip.ciberpizza.model.Cliente;
import br.unip.ciberpizza.model.Produto;
import br.unip.ciberpizza.service.ClienteService;
import br.unip.ciberpizza.service.ProdutoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private final ProdutoService produtoService;

    @Autowired
    private final ClienteService clienteService;

    public PedidoController(ProdutoService produtoService, ClienteService clienteService) {
        this.produtoService = produtoService;
        this.clienteService = clienteService;
    }

    @GetMapping
    public ModelAndView fazerPedido(@RequestParam(name = "idCliente", required = false) String idCliente,
            @ModelAttribute("produto") Produto produto) {
        if (idCliente != null) {
            Cliente cliente = clienteService.encontrarClientePorId(idCliente);

            if (cliente != null) {
                ModelAndView modelAndView = new ModelAndView("pedido");
                modelAndView.addObject("listaProdutos", produtoService.listarProdutos());
                modelAndView.addObject("idCliente", idCliente);
                return modelAndView;
            }
        }

        return new ModelAndView("redirect:/login");
    }

}
