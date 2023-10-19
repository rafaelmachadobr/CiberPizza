package br.unip.ciberpizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.unip.ciberpizza.dto.LoginDTO;
import br.unip.ciberpizza.model.Cliente;
import br.unip.ciberpizza.service.ClienteService;
import br.unip.ciberpizza.validador.LoginClienteValidator;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private final LoginClienteValidator clienteValidador;

    @Autowired
    private final ClienteService clienteService;

    public LoginController(LoginClienteValidator clienteValidador, ClienteService clienteService) {
        this.clienteValidador = clienteValidador;
        this.clienteService = clienteService;
    }

    @GetMapping
    public ModelAndView login(@RequestParam(name = "idCliente", required = false) String idCliente) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("loginDTO", new LoginDTO(null, null));

        if (idCliente != null) {
            Cliente cliente = clienteService.encontrarClientePorId(idCliente);

            if (cliente != null) {
                modelAndView.addObject("idCliente", idCliente);
            }
        }

        return modelAndView;
    }

    @PostMapping
    public String autenticarCliente(@Valid @ModelAttribute LoginDTO loginDTO, Errors errors) {
        clienteValidador.validate(loginDTO, errors);

        if (errors.hasErrors()) {
            return "login";
        }

        Cliente cliente = clienteService.encontrarClientePorEmailESenha(loginDTO.email(), loginDTO.senha());

        if (cliente == null) {
            return "redirect:/login?error=true";
        } else {
            return "redirect:/?idCliente=" + cliente.getId();
        }
    }

}
