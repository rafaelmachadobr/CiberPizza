package br.unip.ciberpizza.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Data
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @Column(nullable = false, length = 1000)
    @NotBlank(message = "A descrição não pode ser vazia")
    private String descricao;

    @Column(nullable = false)
    @Positive(message = "O valor deve ser maior que zero")
    private Double valor;

    @Column(nullable = false, length = 1000)
    @NotBlank(message = "A URL da imagem não pode ser vazia")
    private String urlImagem;

    @Column(nullable = false)
    @NotNull(message = "O tipo não pode ser nulo, deve ser um dos seguintes: PIZZA, BEBIDA")
    @Enumerated(EnumType.STRING)
    private Tipo tipo;
}
