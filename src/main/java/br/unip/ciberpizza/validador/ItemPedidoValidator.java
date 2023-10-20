package br.unip.ciberpizza.validador;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.unip.ciberpizza.dto.ItemPedidoDTO;

@Component
public class ItemPedidoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ItemPedidoDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ItemPedidoDTO itemPedido = (ItemPedidoDTO) target;

        if (itemPedido.quantidade() == null) {
            errors.rejectValue("quantidade", "quantidade.vazio");
        } else

        if (itemPedido.quantidade() <= 0) {
            errors.rejectValue("quantidade", "quantidade.invalido");
        }
    }

}
