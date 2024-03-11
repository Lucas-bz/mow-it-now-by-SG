package com.bouzidi.samples.mowitnow.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Mower {


    private int id;
    private Position position;

    private List<Position> previous;

    private Orientation orientation;

    private Queue<Instruction> instructions;

    public Mower(int id, Position position, Orientation orientation) {
        this.id = id;
        this.position = position;
        this.orientation = orientation;
        this.previous = new ArrayList<>();
        this.instructions = new ArrayDeque<>();
    }


    public void advance() {
        Position current = this.position;
        this.previous.add(this.position);
        this.position = orientation.getNext(current);

    }

    public void rotateRight() {
        this.orientation = orientation.rotateRight();
    }

    public void rotateLeft() {
        this.orientation = orientation.rotateLeft();
    }


    /**
     * A static factory to have a deep clone of a given Mower object
     *
     * @param input given Mower object
     * @return a deep clone of Mower
     */

    public static Mower copyFactory(Mower input) {
        if (input == null) {
            return null;
        }
        return new Mower(input.getId(), Position.copyFactory(input.position), input.previous == null ? null : input.previous.stream().map(Position::copyFactory).collect(Collectors.toList()), input.getOrientation(), input.instructions);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mower mower)) return false;
        return getId() == mower.getId() && Objects.equals(getPosition(), mower.getPosition()) && Objects.equals(getPrevious(), mower.getPrevious()) && getOrientation() == mower.getOrientation() && Objects.equals(getInstructions(), mower.getInstructions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPosition(), getPrevious(), getOrientation(), getInstructions());
    }
}
