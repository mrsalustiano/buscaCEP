package com.marcelo.buscaCEP.utils.validations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import jakarta.validation.ConstraintValidatorContext;

class CepValidatorTest {

	  private CepValidator validator;
	  private ConstraintValidatorContext context;
	  
	  
	  @BeforeEach
	    void setUp() {
	        validator = new CepValidator();
	        context = mock(ConstraintValidatorContext.class);
	    }
	  
	  @Test
	    void shouldInitializeWithoutErrors() {
	        ValidCep validCep = mock(ValidCep.class);
	        validator.initialize(validCep);
	        // Just verifying no exception is thrown
	    }

	    @ParameterizedTest
	    @ValueSource(strings = {
	        "12345-678",  // With hyphen
	        "12345678"    // Without hyphen
	    })
	    void shouldValidateCorrectCeps(String validCep) {
	        assertTrue(validator.isValid(validCep, context));
	    }

	    @ParameterizedTest
	    @ValueSource(strings = {
	        "",           // Empty string
	        "1234-5678",  // Wrong hyphen position
	        "1234567",    // Too short
	        "123456789",  // Too long
	        "abcde-fgh",  // Non-numeric characters
	        "12345_678",  // Wrong separator
	        "12345 678",  // Space instead of hyphen
	        "123456-78",  // Wrong format
	        "1234-56789"  // Wrong format with hyphen
	    })
	    void shouldNotValidateInvalidCeps(String invalidCep) {
	        assertFalse(validator.isValid(invalidCep, context));
	    }

	    @Test
	    void shouldNotValidateNullCep() {
	        assertFalse(validator.isValid(null, context));
	    }

	    @Test
	    void shouldNotValidateEmptyCep() {
	        assertFalse(validator.isValid("", context));
	    }  
}
