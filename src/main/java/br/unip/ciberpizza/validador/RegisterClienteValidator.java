package br.unip.ciberpizza.validador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.unip.ciberpizza.dto.RegisterDTO;
import br.unip.ciberpizza.service.ClienteService;

@Component
public class RegisterClienteValidator implements Validator {
    @Autowired
    private final ClienteService clienteService;

    public RegisterClienteValidator(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterDTO register = (RegisterDTO) target;

        if (register.nome().isEmpty()) {
            errors.rejectValue("nome", "nome.vazio");
        }
        if (register.email().isEmpty()) {
            errors.rejectValue("email", "email.vazio");
        }
        if (register.cpf().isEmpty()) {
            errors.rejectValue("cpf", "cpf.vazio");
        }
        if (register.enderecoEntrega().isEmpty()) {
            errors.rejectValue("enderecoEntrega", "enderecoEntrega.vazio");
        }
        if (register.telefone().isEmpty()) {
            errors.rejectValue("telefone", "telefone.vazio");
        }
        if (register.senha().isEmpty()) {
            errors.rejectValue("senha", "senha.vazio");
        }

        if (!isEmailValido(register.email())) {
            errors.rejectValue("email", "email.invalido");
        }

        if (!isCpfValido(register.cpf())) {
            errors.rejectValue("cpf", "cpf.invalido");
        }

        if (!isTelefoneValido(register.telefone())) {
            errors.rejectValue("telefone", "telefone.invalido");
        }

        if (isCpfJaCadastrado(register.cpf())) {
            errors.rejectValue("cpf", "cpf.jaCadastrado");
        }

        if (isEmailJaCadastrado(register.email())) {
            errors.rejectValue("email", "email.jaCadastrado");
        }

        if (isTelefoneJaCadastrado(register.telefone())) {
            errors.rejectValue("telefone", "telefone.jaCadastrado");
        }

        if (!isSenhaValida(register.senha())) {
            errors.rejectValue("senha", "senha.invalida");
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
