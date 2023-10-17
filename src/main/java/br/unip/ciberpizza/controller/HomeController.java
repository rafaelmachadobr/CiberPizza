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
@RequestMapping("/")
public class HomeController {
    @Autowired
    private final ProdutoService produtoService;

    public HomeController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ModelAndView home(@ModelAttribute("produto") Produto produto) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("listaPizzas", produtoService.listarPizzas());
        return modelAndView;
    }
}
