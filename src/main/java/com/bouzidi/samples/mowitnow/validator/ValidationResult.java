package com.bouzidi.samples.mowitnow.validator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class ValidationResult {

    private boolean isValid;
    private List<String> messages;
}
