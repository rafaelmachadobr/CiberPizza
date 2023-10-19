package br.unip.ciberpizza.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.unip.ciberpizza.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Cliente findByEmailAndSenha(String email, String senha);

    Cliente findByCpf(String cpf);

    Cliente findByEmail(String email);

    Cliente findByTelefone(String telefone);
}
