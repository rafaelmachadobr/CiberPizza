package br.unip.ciberpizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.unip.ciberpizza.model.Produto;
import br.unip.ciberpizza.service.ProdutoService;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private final ProdutoService produtoService;

    public HomeController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ModelAndView home(@ModelAttribute("produto") Produto produto,
            @RequestParam(name = "idCliente", required = false) String idCliente) {
        ModelAndView modelAndView = new ModelAndView("home");

        if (idCliente != null) {
            System.out.println("ID DO CLIENTE: " + idCliente);
            modelAndView.addObject("idCliente", idCliente);
        }

        modelAndView.addObject("listaPizzas", produtoService.listarPizzas());
        return modelAndView;
    }
}
