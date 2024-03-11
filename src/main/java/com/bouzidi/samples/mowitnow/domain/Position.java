package com.bouzidi.samples.mowitnow.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor(staticName = "of")
public class Position {


    private int x;
    private int y;


    public static Position copyFactory(Position input) {
        return input == null ? null : Position.of(input.x, input.y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return getX() == position.getX() && getY() == position.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }
}
