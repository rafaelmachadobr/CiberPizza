package br.unip.ciberpizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.unip.ciberpizza.model.Contato;
import br.unip.ciberpizza.model.Produto;
import br.unip.ciberpizza.service.ContatoService;
import br.unip.ciberpizza.service.ProdutoService;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private final ProdutoService produtoService;

    @Autowired
    private final ContatoService contatoService;

    public HomeController(ProdutoService produtoService, ContatoService contatoService) {
        this.produtoService = produtoService;
        this.contatoService = contatoService;
    }

    @GetMapping
    public ModelAndView home(@ModelAttribute("produto") Produto produto,
            @RequestParam(name = "idCliente", required = false) String idCliente) {
        ModelAndView modelAndView = new ModelAndView("home");

        modelAndView.addObject("contato", new Contato());
        if (idCliente != null) {
            modelAndView.addObject("idCliente", idCliente);
        }

        modelAndView.addObject("listaPizzas", produtoService.listarPizzas());
        return modelAndView;
    }

    @PostMapping("/contato")
    public String salvarContato(@ModelAttribute Contato contato, @RequestParam("idCliente") String idCliente) {
        contatoService.salvarContato(contato);
        return "redirect:/?idCliente=" + idCliente;
    }
}
