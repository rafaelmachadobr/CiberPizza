package br.unip.ciberpizza.dto;

public record CadastroDTO(
                String nome,
                String email,
                String cpf,
                String enderecoEntrega,
                String telefone,
                String senha,
                String confirmarSenha) {
}
