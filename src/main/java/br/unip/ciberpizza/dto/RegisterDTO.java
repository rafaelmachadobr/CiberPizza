package br.unip.ciberpizza.dto;

public record RegisterDTO(
        String nome,
        String email,
        String cpf,
        String enderecoEntrega,
        String telefone,
        String senha,
        String confirmarSenha) {
}
