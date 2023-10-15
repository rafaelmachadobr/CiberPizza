package br.unip.ciberpizza.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID numero;

    @Temporal(TemporalType.TIMESTAMP)
    private Date momento;

    @Column(nullable = false)
    @NotNull(message = "O valor não pode ser nulo")
    @Min(value = 0, message = "O valor deve ser maior ou igual a zero")
    private Double valor;

    @Column(nullable = false)
    @NotBlank(message = "O status não pode ser vazio")
    private String status;

    @Column(nullable = false)
    @NotBlank(message = "O pagamento não pode ser vazio")
    private String pagamento;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;
}
