package br.unip.ciberpizza.validador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.unip.ciberpizza.dto.CadastroDTO;
import br.unip.ciberpizza.service.ClienteService;

@Component
public class CadastroClienteValidator implements Validator {
    @Autowired
    private final ClienteService clienteService;

    public CadastroClienteValidator(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CadastroDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CadastroDTO cadastro = (CadastroDTO) target;

        if (cadastro.nome().isEmpty()) {
            errors.rejectValue("nome", "nome.vazio");
        }
        if (cadastro.email().isEmpty()) {
            errors.rejectValue("email", "email.vazio");
        }
        if (cadastro.cpf().isEmpty()) {
            errors.rejectValue("cpf", "cpf.vazio");
        }
        if (cadastro.enderecoEntrega().isEmpty()) {
            errors.rejectValue("enderecoEntrega", "enderecoEntrega.vazio");
        }
        if (cadastro.telefone().isEmpty()) {
            errors.rejectValue("telefone", "telefone.vazio");
        }
        if (cadastro.senha().isEmpty()) {
            errors.rejectValue("senha", "senha.vazio");
        }
        if (cadastro.confirmarSenha().isEmpty()) {
            errors.rejectValue("confirmarSenha", "confirmarSenha.vazio");
        }

        if (!isEmailValido(cadastro.email())) {
            errors.rejectValue("email", "email.invalido");
        }

        if (!isCpfValido(cadastro.cpf())) {
            errors.rejectValue("cpf", "cpf.invalido");
        }

        if (!isTelefoneValido(cadastro.telefone())) {
            errors.rejectValue("telefone", "telefone.invalido");
        }

        if (isCpfJaCadastrado(cadastro.cpf())) {
            errors.rejectValue("cpf", "cpf.jaCadastrado");
        }

        if (isEmailJaCadastrado(cadastro.email())) {
            errors.rejectValue("email", "email.jaCadastrado");
        }

        if (isTelefoneJaCadastrado(cadastro.telefone())) {
            errors.rejectValue("telefone", "telefone.jaCadastrado");
        }

        if (!isSenhaValida(cadastro.senha())) {
            errors.rejectValue("senha", "senha.invalida");
        }

        if (!isSenhaValida(cadastro.confirmarSenha())) {
            errors.rejectValue("confirmarSenha", "confirmarSenha.invalida");
        }
    }

    private boolean isEmailValido(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    private boolean isCpfValido(String cpf) {
        return cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
    }

    private boolean isTelefoneValido(String telefone) {
        return telefone.matches("^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$");
    }

    private boolean isCpfJaCadastrado(String cpf) {
        return clienteService.encontrarClientePorCpf(cpf) != null;
    }

    private boolean isEmailJaCadastrado(String email) {
        return clienteService.encontrarClientePorEmail(email) != null;
    }

    private boolean isTelefoneJaCadastrado(String telefone) {
        return clienteService.encontrarClientePorTelefone(telefone) != null;
    }

    private boolean isSenhaValida(String senha) {
        return senha.matches("^[a-zA-Z0-9]{8,}$");
    }
}
