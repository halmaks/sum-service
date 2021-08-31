package com.halmaks.sumservice.dto_validations;

import com.halmaks.sumservice.dto.SumDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SumDtoValidationTests {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldPassValidationWhenTheBothNamesIsMoreThan0Characters() {
        final var sumDto = new SumDto("one", "two");

        Set<ConstraintViolation<SumDto>> violations = validator.validate(sumDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWhenFirstNameIsBlank() {
        final var sumDto = new SumDto("", "two");

        Set<ConstraintViolation<SumDto>> violations = validator.validate(sumDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWhenSecondNameIsBlank() {
        final var sumDto = new SumDto("one", "");

        Set<ConstraintViolation<SumDto>> violations = validator.validate(sumDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWhenSecondNameIsNull() {
        final var sumDto = new SumDto("one", null);

        Set<ConstraintViolation<SumDto>> violations = validator.validate(sumDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWhenFirstNameIsNull() {
        final var sumDto = new SumDto(null, "two");

        Set<ConstraintViolation<SumDto>> violations = validator.validate(sumDto);
        assertFalse(violations.isEmpty());
    }
}
