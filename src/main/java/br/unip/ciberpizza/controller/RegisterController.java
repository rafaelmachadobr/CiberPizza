package br.unip.ciberpizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.unip.ciberpizza.dto.RegisterDTO;
import br.unip.ciberpizza.model.Cliente;
import br.unip.ciberpizza.service.ClienteService;
import br.unip.ciberpizza.validador.RegisterClienteValidator;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/criar-conta")
public class RegisterController {
    @Autowired
    private final ClienteService clienteService;

    @Autowired
    private final RegisterClienteValidator clienteValidador;

    public RegisterController(ClienteService clienteService, RegisterClienteValidator clienteValidador) {
        this.clienteService = clienteService;
        this.clienteValidador = clienteValidador;
    }

    @GetMapping
    public ModelAndView register(@RequestParam(name = "idCliente", required = false) String idCliente) {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("registerDTO", new RegisterDTO(null, null, null, null, null, null));

        if (idCliente != null) {
            Cliente cliente = clienteService.encontrarClientePorId(idCliente);

            if (cliente != null) {
                modelAndView.addObject("idCliente", idCliente);
            }
        }

        return modelAndView;
    }

    @PostMapping
    public String cadastrarCliente(@Valid @ModelAttribute RegisterDTO registerDTO, Errors errors) {
        clienteValidador.validate(registerDTO, errors);

        if (errors.hasErrors()) {
            return "register";
        }

        System.out.println(registerDTO.nome());

        Cliente cliente = new Cliente(registerDTO.nome(), registerDTO.email(), registerDTO.cpf(),
                registerDTO.enderecoEntrega(), registerDTO.telefone(), registerDTO.senha());

        clienteService.salvarCliente(cliente);

        return "redirect:/login";
    }

}
