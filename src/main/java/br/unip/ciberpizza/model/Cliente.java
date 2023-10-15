package br.unip.ciberpizza.model;

import java.util.List;
import java.util.UUID;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "O email não pode ser vazio")
    @Email(message = "O email deve ser válido")
    private String email;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "O CPF não pode ser vazio")
    @CPF(message = "O CPF deve ser válido, no formato xxx.xxx.xxx-xx")
    private String cpf;

    @Column(nullable = false)
    @NotBlank(message = "O endereço de entrega não pode ser vazio")
    private String enderecoEntrega;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "O telefone não pode ser vazio")
    @Pattern(regexp = "\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}", message = "O telefone deve ser válido, no formato (xx) xxxx-xxxx ou (xx) xxxxx-xxxx")
    private String telefone;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
}
