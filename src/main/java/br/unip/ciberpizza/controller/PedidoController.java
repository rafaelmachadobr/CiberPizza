package br.unip.ciberpizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.unip.ciberpizza.model.Produto;
import br.unip.ciberpizza.service.ProdutoService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private final ProdutoService produtoService;

    public PedidoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ModelAndView fazerPedido(@ModelAttribute("produto") Produto produto) {
        ModelAndView modelAndView = new ModelAndView("pedido");
        modelAndView.addObject("listaProdutos", produtoService.listarProdutos());
        return modelAndView;
    }
}
