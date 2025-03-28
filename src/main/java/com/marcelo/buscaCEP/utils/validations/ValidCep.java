package com.marcelo.buscaCEP.utils.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = {CepValidator.class} ) 
@Target({ ElementType.FIELD, ElementType.PARAMETER }) 
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCep {

	String message() default "CEP inválido"; 

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
