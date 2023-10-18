package br.unip.ciberpizza.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String numero;

    @Temporal(TemporalType.TIMESTAMP)
    private Date momento;

    @Column(nullable = false)
    @NotNull(message = "O valor não pode ser nulo")
    @Min(value = 0, message = "O valor deve ser maior ou igual a zero")
    private Double valor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status não pode ser nulo, deve ser um dos seguintes: AGUARDANDO_PAGAMENTO, PAGAMENTO_CONFIRMADO, EM_ANDAMENTO, ENVIADO, ENTREGUE, CANCELADO")
    private Status status;

    @Column(nullable = false)
    @NotNull(message = "O tipo de pagamento não pode ser nulo, deve ser um dos seguintes: DINHEIRO, CARTAO_CREDITO, CARTAO_DEBITO, VALE_ALIMENTACAO, VALE_REFEICAO, PIX")
    @Enumerated(EnumType.STRING)
    private Pagamento pagamento;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;
}
