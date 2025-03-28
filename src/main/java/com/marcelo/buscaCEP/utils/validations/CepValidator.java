package com.marcelo.buscaCEP.utils.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CepValidator implements ConstraintValidator<ValidCep, String> {

     
    private static final String CEP_REGEX = "^[0-9]{5}-?[0-9]{3}$"; 

    @Override
    public void initialize(ValidCep constraintAnnotation) {
    }

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext context) {
        if (cep == null || cep.isEmpty()) {
            return false;
        }
        return cep.matches(CEP_REGEX);
    }
}