package br.unip.ciberpizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.unip.ciberpizza.dto.AtualizarPerfilDTO;
import br.unip.ciberpizza.model.Cliente;
import br.unip.ciberpizza.service.ClienteService;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    private final ClienteService clienteService;

    public PerfilController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ModelAndView perfilCliente(@RequestParam(name = "idCliente", required = false) String idCliente) {
        if (idCliente != null) {
            Cliente cliente = clienteService.encontrarClientePorId(idCliente);

            if (cliente != null) {
                ModelAndView modelAndView = new ModelAndView("perfil");
                modelAndView.addObject("atualizarPerfilDTO", new AtualizarPerfilDTO(null));
                modelAndView.addObject("cliente", cliente);
                modelAndView.addObject("idCliente", idCliente);
                return modelAndView;
            }
        }

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/sair")
    public String sair(HttpServletRequest request) {
        return "redirect:/";
    }

    @PostMapping("/atualizar")
    public String atualizarEnderecoEntrega(
            @RequestParam(name = "idCliente", required = false) String idCliente,
            @ModelAttribute("atualizarPerfilDTO") AtualizarPerfilDTO atualizarPerfilDTO) {
        Cliente cliente = clienteService.encontrarClientePorId(idCliente);

        if (cliente != null) {
            cliente.setEnderecoEntrega(atualizarPerfilDTO.enderecoEntrega());
            clienteService.salvarCliente(cliente);
        } else {
            return "redirect:/login";
        }

        return "redirect:/perfil?idCliente=" + idCliente;
    }
}
