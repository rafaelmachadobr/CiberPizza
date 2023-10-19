package br.unip.ciberpizza.validador;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.unip.ciberpizza.dto.LoginDTO;

@Component
public class LoginClienteValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return LoginDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        LoginDTO login = (LoginDTO) obj;

        if (login.email().isEmpty()) {
            errors.rejectValue("email", "email.vazio");
        }
        if (login.senha().isEmpty()) {
            errors.rejectValue("senha", "senha.vazio");
        }

        if (!isEmailValido(login.email())) {
            errors.rejectValue("email", "email.invalido");
        }
    }

    private boolean isEmailValido(String email) {
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}
