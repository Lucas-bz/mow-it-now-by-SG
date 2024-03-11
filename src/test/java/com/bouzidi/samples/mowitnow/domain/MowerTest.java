package com.bouzidi.samples.mowitnow.domain;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class MowerTest {

    private Mower mower;
    private Position position;


    @BeforeEach
    void init() {
        position = Position.of(0, 0);
        mower = new Mower(111, position, Orientation.NORTH);
    }

    @Test
    void test_advance() {
        mower.advance();

        Orientation expectedOrientation = Orientation.NORTH;
        Position expectedPosition = Position.of(0, 1);
        List<Position> expectedPrevious = List.of(position);

        assertEquals(expectedOrientation, mower.getOrientation());
        assertEquals(expectedPosition, mower.getPosition());
        assertEquals(expectedPrevious, mower.getPrevious());
    }

    @Test
    void test_rotateRight() {
        mower.rotateRight();

        Orientation expectedOrientation = Orientation.EAST;
        Position expectedPosition = Position.of(0, 0);
        List<Position> expectedPrevious = List.of();

        assertEquals(expectedOrientation, mower.getOrientation());
        assertEquals(expectedPosition, mower.getPosition());
        assertEquals(expectedPrevious, mower.getPrevious());
    }

    @Test
    void test_rotateLeft() {
        mower.rotateLeft();

        Orientation expectedOrientation = Orientation.WEST;
        Position expectedPosition = Position.of(0, 0);
        List<Position> expectedPrevious = List.of();

        assertEquals(expectedOrientation, mower.getOrientation());
        assertEquals(expectedPosition, mower.getPosition());
        assertEquals(expectedPrevious, mower.getPrevious());

    }
}