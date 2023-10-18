package br.unip.ciberpizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.unip.ciberpizza.model.Cliente;
import br.unip.ciberpizza.service.ClienteService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    private final ClienteService clienteService;

    public ProfileController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/perfil")
    public ModelAndView perfilCliente(@RequestParam(name = "idCliente", required = false) String idCliente) {
        if (idCliente != null) {
            Cliente cliente = clienteService.encontrarClientePorId(idCliente);

            if (cliente != null) {
                ModelAndView modelAndView = new ModelAndView("perfil");
                modelAndView.addObject("cliente", cliente);
                return modelAndView;
            }
        }

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/sair")
    public String sair(HttpServletRequest request) {
        return "redirect:/";
    }
}
