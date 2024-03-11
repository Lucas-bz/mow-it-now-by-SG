package com.bouzidi.samples.mowitnow.parser;


/**
 * Functional Interface to Parse a String to a T Type
 * @param <T>
 */
public interface Parser<T> {

    T parse(String row);
}
