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

import br.unip.ciberpizza.dto.CadastroDTO;
import br.unip.ciberpizza.model.Cliente;
import br.unip.ciberpizza.service.ClienteService;
import br.unip.ciberpizza.validador.CadastroClienteValidator;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {
    @Autowired
    private final ClienteService clienteService;

    @Autowired
    private final CadastroClienteValidator clienteValidador;

    public CadastroController(ClienteService clienteService, CadastroClienteValidator clienteValidador) {
        this.clienteService = clienteService;
        this.clienteValidador = clienteValidador;
    }

    @GetMapping
    public ModelAndView cadastro(@RequestParam(name = "idCliente", required = false) String idCliente) {
        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("cadastroDTO", new CadastroDTO(null, null, null, null, null, null, null));

        if (idCliente != null) {
            Cliente cliente = clienteService.encontrarClientePorId(idCliente);

            if (cliente != null) {
                modelAndView.addObject("idCliente", idCliente);
            }
        }

        return modelAndView;
    }

    @PostMapping
    public String cadastrarCliente(@Valid @ModelAttribute CadastroDTO cadastroDTO, Errors errors) {
        clienteValidador.validate(cadastroDTO, errors);


        if (errors.hasErrors()) {
            return "cadastro";
        }

        if (!cadastroDTO.senha().equals(cadastroDTO.confirmarSenha())) {
            return "redirect:/cadastro?error=true";
        }

        Cliente cliente = new Cliente(cadastroDTO.nome(), cadastroDTO.email(), cadastroDTO.cpf(),
                cadastroDTO.enderecoEntrega(), cadastroDTO.telefone(), cadastroDTO.senha());

        clienteService.salvarCliente(cliente);

        return "redirect:/login";
    }

}
