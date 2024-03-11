package com.bouzidi.samples.mowitnow.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Orientation {

    EAST('E') {
        @Override
        public Position getNext(Position position) {
            return Position.of(position.getX() + 1, position.getY());
        }

        @Override
        public Orientation rotateRight() {
            return Orientation.SOUTH;
        }

        @Override
        public Orientation rotateLeft() {
            return Orientation.NORTH;
        }
    }, NORTH('N') {
        @Override
        public Position getNext(Position position) {
            return Position.of(position.getX(), position.getY() + 1);
        }

        @Override
        public Orientation rotateRight() {
            return Orientation.EAST;
        }

        @Override
        public Orientation rotateLeft() {
            return Orientation.WEST;
        }
    }, WEST('W') {
        @Override
        public Position getNext(Position position) {
            return Position.of(position.getX() - 1, position.getY());
        }

        @Override
        public Orientation rotateRight() {
            return Orientation.NORTH;
        }

        @Override
        public Orientation rotateLeft() {
            return Orientation.SOUTH;
        }
    }, SOUTH('S') {
        @Override
        public Position getNext(Position position) {
            return Position.of(position.getX(), position.getY() - 1);
        }

        @Override
        public Orientation rotateRight() {
            return Orientation.WEST;
        }

        @Override
        public Orientation rotateLeft() {
            return Orientation.EAST;
        }
    };

    private final char inner;


    public abstract Position getNext(Position position);

    public abstract Orientation rotateRight();

    public abstract Orientation rotateLeft();

    public static Orientation fromSymbol(String symbol) {
        return Arrays.stream(Orientation.values()).filter(o -> o.inner == symbol.charAt(0)).findAny().orElse(Orientation.NORTH);
    }

}
