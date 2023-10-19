package br.unip.ciberpizza.model;

import java.util.List;

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
    private String id;

    @Column(nullable = false)
    @NotBlank(message = "O nome não pode ser vazio")
    private String nome;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "O email não pode ser vazio")
    @Email(message = "O email deve ser válido")
    private String email;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "O CPF não pode ser vazio")
    @Pattern(regexp = "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}-[0-9]{2}", message = "O CPF deve ser válido, no formato xxx.xxx.xxx-xx")
    private String cpf;

    @Column(nullable = false)
    @NotBlank(message = "O endereço de entrega não pode ser vazio")
    private String enderecoEntrega;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "O telefone não pode ser vazio")
    @Pattern(regexp = "\\([0-9]{2}\\) [0-9]{4,5}-[0-9]{4}", message = "O telefone deve ser válido, no formato (xx) xxxx-xxxx ou (xx) xxxxx-xxxx")
    private String telefone;

    @Column(nullable = false)
    @NotBlank(message = "A senha não pode ser vazia")
    @Pattern(regexp = "[a-zA-Z0-9]{8,}", message = "A senha deve ter pelo menos 8 caracteres, podendo ser letras ou números")
    private String senha;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(String nome, String email, String cpf, String enderecoEntrega, String telefone, String senha) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.enderecoEntrega = enderecoEntrega;
        this.telefone = telefone;
        this.senha = senha;
    }
}
