package com.marcelo.buscaCEP.ports.in.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler exceptionHandler;

    @Mock
    private MethodArgumentNotValidException methodArgumentNotValidException;

    @Mock
    private BindingResult bindingResult;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        exceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void handleValidationExceptions_ShouldReturnErrorMap() {
        // Given
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        FieldError fieldError = new FieldError("objectName", "cep", "CEP inválido");
        when(bindingResult.getFieldErrors()).thenReturn(java.util.Collections.singletonList(fieldError));

        // When
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleValidationExceptions(methodArgumentNotValidException);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("CEP inválido", response.getBody().get("cep"));
    }

    @Test
    void handleValidationExceptions_WithMultipleErrors_ShouldReturnAllErrors() {
        // Given
        when(methodArgumentNotValidException.getBindingResult()).thenReturn(bindingResult);
        FieldError fieldError1 = new FieldError("objectName", "cep", "CEP inválido");
        FieldError fieldError2 = new FieldError("objectName", "logradouro", "Logradouro é obrigatório");
        when(bindingResult.getFieldErrors()).thenReturn(java.util.Arrays.asList(fieldError1, fieldError2));

        // When
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleValidationExceptions(methodArgumentNotValidException);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, String> errors = response.getBody();
        assertNotNull(errors);
        assertEquals(2, errors.size());
        assertEquals("CEP inválido", errors.get("cep"));
        assertEquals("Logradouro é obrigatório", errors.get("logradouro"));
    }

    @Test
    void handleConstraintViolationException_ShouldReturnErrorMap() {
        // Given
        Set<ConstraintViolation<?>> violations = new HashSet<>();
        ConstraintViolation<?> violation = mock(ConstraintViolation.class);
        when(violation.getMessage()).thenReturn("CEP deve estar no formato 99999-999");
        violations.add(violation);
        ConstraintViolationException constraintViolationException = new ConstraintViolationException("Validation failed", violations);

        // When
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleConstraintViolationException(constraintViolationException);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, String> errors = response.getBody();
        assertNotNull(errors);
        assertEquals("CEP deve estar no formato 99999-999", errors.get("erro"));
    }

    @Test
    void handleConstraintViolationException_WithMultipleViolations_ShouldReturnLastError() {
        // Given
        Set<ConstraintViolation<?>> violations = new HashSet<>();
        
        ConstraintViolation<?> violation1 = mock(ConstraintViolation.class);
        when(violation1.getMessage()).thenReturn("Primeiro erro");
        violations.add(violation1);
        
        ConstraintViolation<?> violation2 = mock(ConstraintViolation.class);
        when(violation2.getMessage()).thenReturn("Segundo erro");
        violations.add(violation2);
        
        ConstraintViolationException constraintViolationException = new ConstraintViolationException("Validation failed", violations);

        // When
        ResponseEntity<Map<String, String>> response = exceptionHandler.handleConstraintViolationException(constraintViolationException);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        Map<String, String> errors = response.getBody();
        assertNotNull(errors);
        assertEquals(1, errors.size()); // Only one error because they use the same key "erro"
        assertTrue(errors.get("erro").contains("erro")); // Will contain one of the error messages
    }
}