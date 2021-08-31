package com.halmaks.sumservice.dto_validations;

import com.halmaks.sumservice.dto.ItemRemoveDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ItemRemoveDtoValidationTests {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldPassValidationWhenTheNameIsMoreThan0Characters() {
        final var itemRemoveDto = new ItemRemoveDto("one");

        Set<ConstraintViolation<ItemRemoveDto>> violations = validator.validate(itemRemoveDto);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWhenNameIsBlank() {
        final var itemRemoveDto = new ItemRemoveDto("");

        Set<ConstraintViolation<ItemRemoveDto>> violations = validator.validate(itemRemoveDto);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWhenNameIsNull() {
        final var itemRemoveDto = new ItemRemoveDto(null);

        Set<ConstraintViolation<ItemRemoveDto>> violations = validator.validate(itemRemoveDto);
        assertFalse(violations.isEmpty());
    }
}
