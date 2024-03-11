package com.bouzidi.samples.mowitnow.validator;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.junit.jupiter.api.Assertions.*;

class FileValidatorTest {


    private final String[] validArray = {"5 5", "1 2 N", "GAGAGAGAA", "3 3 E", "AADAADADDA"};
    private final String[] notValidArray = {"5 D", "1 2 N", "GAGADXGAA", "3 A E", "AADAADADDA"};
    private final String[] emptyArray = {};

    private final FileValidator validator = new FileValidator();


    @Test
    void test_validate_isValid() {
        assertTrue(validator.validate(validArray).isValid());
    }


    @Test
    void test_validate_isNotValid() {
        ValidationResult result = validator.validate(notValidArray);
        assertFalse(result.isValid());
        assertEquals(3, result.getMessages().size());
    }


    @Test
    void test_validate_emptyFile() {
        ValidationResult result = validator.validate(emptyArray);
        assertFalse(result.isValid());
        assertThat(result.getMessages(), contains("File is empty !"));

    }
}