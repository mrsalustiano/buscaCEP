package com.marcelo.buscaCEP.application.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.marcelo.buscaCEP.utils.validations.ValidCep;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

class CepRequestDtoTest {

    private Validator validator;
    private CepRequestDto cepRequestDto;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        cepRequestDto = new CepRequestDto();
    }

    @Test
    void whenCepIsValid_thenNoValidationViolations() {
        // Given
        cepRequestDto.setCep("12345-678");

        // When
        var violations = validator.validate(cepRequestDto);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    void whenCepIsValidWithoutHyphen_thenNoValidationViolations() {
        // Given
        cepRequestDto.setCep("12345678");

        // When
        var violations = validator.validate(cepRequestDto);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    void whenCepIsNull_thenValidationViolation() {
        // Given
        cepRequestDto.setCep(null);

        // When
        var violations = validator.validate(cepRequestDto);

        // Then
        assertEquals(2, violations.size());
        assertEquals("CEP inválido", 
            violations.iterator().next().getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "1234-567",    // Too short
        "123456789",   // Too long
        "abcde-fgh",   // Contains letters
        "12345-67a",   // Contains letters
        "1234-5678",   // Hyphen in wrong position
        "12345 678",   // Contains space
        "-12345678",   // Hyphen in wrong position
        "12345678-"    // Hyphen in wrong position
    })
    void whenCepIsInvalid_thenValidationViolation(String invalidCep) {
        // Given
        cepRequestDto.setCep(invalidCep);

        // When
        var violations = validator.validate(cepRequestDto);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream()
            .anyMatch(v -> v.getConstraintDescriptor()
                          .getAnnotation() instanceof ValidCep));
    }

    @Test
    void testEqualsAndHashCode() {
        // Given
        CepRequestDto dto1 = new CepRequestDto();
        CepRequestDto dto2 = new CepRequestDto();
        
        dto1.setCep("12345-678");
        dto2.setCep("12345-678");

        // Then
        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testToString() {
        // Given
        cepRequestDto.setCep("12345-678");

        // When
        String toString = cepRequestDto.toString();

        // Then
        assertTrue(toString.contains("12345-678"));
    }

    @Test
    void testGetterAndSetter() {
        // Given
        String cep = "12345-678";

        // When
        cepRequestDto.setCep(cep);

        // Then
        assertEquals(cep, cepRequestDto.getCep());
    }

    @Test
    void whenCreatingNewInstance_thenFieldsAreNull() {
        // When
        CepRequestDto newDto = new CepRequestDto();

        // Then
        assertNull(newDto.getCep());
    }

    @Test
    void testBuilder() {
        // When
        CepRequestDto dto = CepRequestDto.builder()
                .cep("12345-678")
                .build();

        // Then
        assertEquals("12345-678", dto.getCep());
    }

    @Test
    void testNoArgsConstructor() {
        // When
        CepRequestDto dto = new CepRequestDto();

        // Then
        assertNotNull(dto);
        assertNull(dto.getCep());
    }
}
