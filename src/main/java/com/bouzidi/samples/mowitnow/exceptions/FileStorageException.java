/*
 * Copyright (c) 2020.
 * FileStorageException.java created by farouk
 */

package com.bouzidi.samples.mowitnow.exceptions;

/**
 * Exception class to be thrown when the service isn't able to store input file
 */

public class FileStorageException extends RuntimeException {


    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
