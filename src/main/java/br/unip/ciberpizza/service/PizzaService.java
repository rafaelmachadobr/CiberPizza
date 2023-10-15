package br.unip.ciberpizza.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unip.ciberpizza.model.Pizza;
import br.unip.ciberpizza.repository.PizzaRepository;

@Service
public class PizzaService {
    @Autowired
    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public Pizza salvarPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public List<Pizza> listarPizzas() {
        return pizzaRepository.findAll();
    }

    public Pizza encontrarPizzaPorId(UUID id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    public Pizza atualizarPizza(UUID id, Pizza pizzaAtualizada) {
        Pizza pizzaExistente = pizzaRepository.findById(id).orElse(null);

        if (pizzaExistente != null) {
            pizzaExistente.setNome(pizzaAtualizada.getNome());
            pizzaExistente.setDescricao(pizzaAtualizada.getDescricao());
            pizzaExistente.setValor(pizzaAtualizada.getValor());

            return pizzaRepository.save(pizzaExistente);
        } else {
            return null;
        }
    }

    public void deletarPizza(UUID id) {
        pizzaRepository.deleteById(id);
    }
}
