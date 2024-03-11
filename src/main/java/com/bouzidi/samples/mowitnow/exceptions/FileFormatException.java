package com.bouzidi.samples.mowitnow.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;


/**
 * Exception class to be thrown when given a wrong file format
 */
@AllArgsConstructor
@Getter
public class FileFormatException extends RuntimeException {

    final transient List<String> messages;
}
