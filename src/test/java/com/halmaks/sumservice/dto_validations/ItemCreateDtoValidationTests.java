package com.halmaks.sumservice.dto_validations;

import com.halmaks.sumservice.dto.ItemCreateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemCreateDtoValidationTests {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldPassValidationWhenTheNameIsMoreThan0CharactersAndValueIsNotNull() {
        final var itemCreateDto = new ItemCreateDto("one", 1);

        Set<ConstraintViolation<ItemCreateDto>> violations = validator.validate(itemCreateDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWhenTheNameIsBlank() {
        final var itemCreateDto = new ItemCreateDto("", 1);

        Set<ConstraintViolation<ItemCreateDto>> violations = validator.validate(itemCreateDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWhenValueIsNull() {
        final var itemCreateDto = new ItemCreateDto("one", null);

        Set<ConstraintViolation<ItemCreateDto>> violations = validator.validate(itemCreateDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWhenTheNameIsNull() {
        final var itemCreateDto = new ItemCreateDto(null, 1);

        Set<ConstraintViolation<ItemCreateDto>> violations = validator.validate(itemCreateDto);
        assertFalse(violations.isEmpty());
    }
}
