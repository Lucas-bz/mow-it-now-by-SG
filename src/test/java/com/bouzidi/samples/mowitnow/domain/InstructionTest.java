package com.bouzidi.samples.mowitnow.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class InstructionTest {


    private final Mower mower = new Mower(1, Position.of(0, 0), Orientation.NORTH);

    @Test
    void execute() {
        Instruction instruction = Instruction.FORWARD;
        instruction.execute(mower);

        assertEquals(Position.of(0, 1), mower.getPosition());
        assertEquals(Orientation.NORTH, mower.getOrientation());

        instruction = Instruction.ROTATE_RIGHT;
        instruction.execute(mower);

        assertEquals(Position.of(0, 1), mower.getPosition());
        assertEquals(Orientation.EAST, mower.getOrientation());

        instruction = Instruction.FORWARD;
        instruction.execute(mower);

        assertEquals(Position.of(1, 1), mower.getPosition());
        assertEquals(Orientation.EAST, mower.getOrientation());

        instruction = Instruction.ROTATE_LEFT;
        instruction.execute(mower);
        instruction.execute(mower);

        assertEquals(Position.of(1, 1), mower.getPosition());
        assertEquals(Orientation.WEST, mower.getOrientation());

    }

    @Test
    void fromSymbol() {
        assertEquals(Instruction.FORWARD, Instruction.fromSymbol('A'));
        assertEquals(Instruction.ROTATE_LEFT, Instruction.fromSymbol('G'));
        assertEquals(Instruction.ROTATE_RIGHT, Instruction.fromSymbol('D'));


        // When unknown Symbol, assert instruction is forward
        assertEquals(Instruction.FORWARD, Instruction.fromSymbol('X'));
    }
}