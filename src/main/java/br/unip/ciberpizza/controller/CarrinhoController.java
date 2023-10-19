package br.unip.ciberpizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.unip.ciberpizza.model.Cliente;
import br.unip.ciberpizza.service.ClienteService;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {
    @Autowired
    private final ClienteService clienteService;

    public CarrinhoController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ModelAndView carrinho(@RequestParam(name = "idCliente", required = false) String idCliente) {
        if (idCliente != null) {
            Cliente cliente = clienteService.encontrarClientePorId(idCliente);

            if (cliente != null) {
                ModelAndView modelAndView = new ModelAndView("carrinho");
                modelAndView.addObject("idCliente", idCliente);
                return modelAndView;
            }
        }

        return new ModelAndView("redirect:/login");
    }

}
