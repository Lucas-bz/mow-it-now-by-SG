package com.bouzidi.samples.mowitnow.validator;

@FunctionalInterface
public interface Validator<K> {

    /**
     * Check a row for errors that would cause problems when translating.
     *
     * @param rows array of fields as strings
     * @return ValidationResult containing boolean isValid and Collection of messages regarding result
     */
    ValidationResult validate(K rows);
}
