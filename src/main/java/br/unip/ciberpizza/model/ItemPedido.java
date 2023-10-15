package br.unip.ciberpizza.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @NotNull(message = "O pedido não pode ser nulo")
    @Positive(message = "A quantidade deve ser maior que zero")
    private int quantidade;

    @Column(nullable = false)
    @NotBlank(message = "O tamanho não pode ser vazio")
    private String tamanho;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;
}
