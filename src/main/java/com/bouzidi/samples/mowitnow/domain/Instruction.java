package com.bouzidi.samples.mowitnow.domain;


import lombok.AllArgsConstructor;

import java.util.Arrays;

/**
 * Enum of possible mower instructions
 */

@AllArgsConstructor
public enum Instruction {

    ROTATE_RIGHT('D') {
        @Override
        public void execute(final Mower mower) {
            mower.rotateRight();
        }
    },

    ROTATE_LEFT('G') {
        @Override
        public void execute(final Mower mower) {
            mower.rotateLeft();
        }
    },

    FORWARD('A') {
        @Override
        public void execute(final Mower mower) {
            mower.advance();
        }
    };

    private final char inner;

    public abstract void execute(final Mower mower);

    public static Instruction fromSymbol(char symbol) {
        return Arrays.stream(Instruction.values()).filter(instruction -> instruction.inner == symbol).findAny().orElse(Instruction.FORWARD);
    }


}
