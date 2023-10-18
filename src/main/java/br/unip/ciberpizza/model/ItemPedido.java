package br.unip.ciberpizza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @NotNull(message = "O pedido não pode ser nulo")
    @Positive(message = "A quantidade deve ser maior que zero")
    private int quantidade;

    @Column(nullable = false)
    @NotNull(message = "O tamanho não pode ser nulo, escolha um tamanho: PEQUENA, MEDIA, GRANDE")
    @Enumerated(EnumType.STRING)
    private Tamanho tamanho;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;

    @Transient
    private double preco;
}
