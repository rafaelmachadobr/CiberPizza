package br.unip.ciberpizza.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.unip.ciberpizza.dto.ItemPedidoDTO;
import br.unip.ciberpizza.model.Cliente;
import br.unip.ciberpizza.model.ItemPedido;
import br.unip.ciberpizza.model.Pedido;
import br.unip.ciberpizza.model.Produto;
import br.unip.ciberpizza.model.Tamanho;
import br.unip.ciberpizza.model.Tipo;
import br.unip.ciberpizza.service.ClienteService;
import br.unip.ciberpizza.service.ItemPedidoService;
import br.unip.ciberpizza.service.PedidoService;
import br.unip.ciberpizza.service.ProdutoService;
import br.unip.ciberpizza.validador.ItemPedidoValidator;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private final ProdutoService produtoService;

    @Autowired
    private final ClienteService clienteService;

    @Autowired
    private final PedidoService pedidoService;

    @Autowired
    private final ItemPedidoService itemPedidoService;

    @Autowired
    private final ItemPedidoValidator itemPedidoValidator;

    public PedidoController(ProdutoService produtoService, ClienteService clienteService, PedidoService pedidoService,
            ItemPedidoService itemPedidoService, ItemPedidoValidator itemPedidoValidator) {
        this.produtoService = produtoService;
        this.clienteService = clienteService;
        this.pedidoService = pedidoService;
        this.itemPedidoService = itemPedidoService;
        this.itemPedidoValidator = itemPedidoValidator;
    }

    @GetMapping
    public ModelAndView fazerPedido(@RequestParam(name = "idCliente", required = false) String idCliente) {
        if (idCliente != null) {
            Cliente cliente = clienteService.encontrarClientePorId(idCliente);
            List<Pedido> pedidos = pedidoService.encontrarPedidosPorCliente(cliente);

            if (cliente != null) {
                if (pedidoService.encontrarPedidosPorCliente(cliente).isEmpty()) {
                    return new ModelAndView("redirect:/pedido/criar?idCliente=" + idCliente);
                } else {
                    ModelAndView modelAndView = new ModelAndView("pedido");
                    modelAndView.addObject("itemPedidoDTO", new ItemPedidoDTO(1, null));
                    modelAndView.addObject("listaProdutos", produtoService.listarProdutos());
                    modelAndView.addObject("idCliente", idCliente);

                    if (!pedidos.isEmpty()) {
                        Integer numeroPedido = pedidos.get(pedidos.size() - 1).getNumero();
                        modelAndView.addObject("numeroPedido", numeroPedido);
                    }

                    return modelAndView;
                }
            }
        }

        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/criar")
    public ModelAndView criarPedido(@RequestParam(name = "idCliente", required = false) String idCliente) {
        if (idCliente != null) {
            Cliente cliente = clienteService.encontrarClientePorId(idCliente);

            if (cliente != null) {
                if (pedidoService.encontrarPedidosPorCliente(cliente).isEmpty()) {
                    Pedido pedido = new Pedido();
                    pedido.setCliente(cliente);
                    pedidoService.salvarPedido(pedido);

                    return new ModelAndView(
                            "redirect:/pedido?idCliente=" + idCliente);
                } else {
                    ModelAndView modelAndView = new ModelAndView("pedido");
                    modelAndView.addObject("listaProdutos", produtoService.listarProdutos());
                    modelAndView.addObject("idCliente", idCliente);
                    return modelAndView;
                }
            }
        }

        return new ModelAndView("redirect:/login");
    }

    @PostMapping("/adicionarItem")
    public String adicionarItemPedido(@ModelAttribute ItemPedidoDTO itemPedidoDTO,
            @RequestParam(name = "idCliente", required = false) String idCliente,
            @RequestParam(name = "idProduto", required = false) String idProduto,
            @RequestParam(name = "numeroPedido", required = false) String numeroPedido,
            @RequestParam(name = "valorProduto", required = false) String valorProduto, Errors errors) {
        itemPedidoValidator.validate(itemPedidoDTO, errors);

        if (errors.hasErrors()) {
            return "pedido?error=true";
        }

        Pedido pedido = pedidoService.encontrarPedidoPorNumero(Integer.parseInt(numeroPedido));

        Tamanho tamanho = Tamanho.valueOf(itemPedidoDTO.tamanho());

        Produto produto = produtoService.encontrarProdutoPorId(idProduto);

        Double preco = Double.parseDouble(valorProduto);

        if (tamanho.equals(Tamanho.MEDIA) && produto.getTipo().equals(Tipo.PIZZA)) {
            preco += 10;
        } else if (tamanho.equals(Tamanho.GRANDE) && produto.getTipo().equals(Tipo.PIZZA)) {
            preco += 20;
        } else if (tamanho.equals(Tamanho.MEDIA) && produto.getTipo().equals(Tipo.BEBIDA)) {
            preco += 2.5;
        } else if (tamanho.equals(Tamanho.GRANDE) && produto.getTipo().equals(Tipo.BEBIDA)) {
            preco += 5;
        }

        preco *= itemPedidoDTO.quantidade();

        ItemPedido itemPedido = new ItemPedido(itemPedidoDTO.quantidade(), tamanho, pedido, produto, preco);

        itemPedidoService.salvarItemPedido(itemPedido);

        return "redirect:/pedido?idCliente=" + pedido.getCliente().getId();
    }

}
