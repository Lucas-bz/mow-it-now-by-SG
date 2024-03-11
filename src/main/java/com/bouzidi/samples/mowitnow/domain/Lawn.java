package com.bouzidi.samples.mowitnow.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * Lawn object :
 * A Lawn is defined by width, height and a set of Mowers
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Lawn {

    private int width;

    private int height;

    private Set<Mower> mowerSet;

    public Lawn(int width, int height) {
        this.height = height;
        this.width = width;
        this.mowerSet = new HashSet<>();
    }

    /**
     * Verify that a position is in the Lawn (Grid)
     *
     * @param position given position to be verified
     * @return boolean
     */
    public boolean isOutGrid(Position position) {
        return position.getX() > width || position.getY() > height || position.getX() < 0 || position.getY() < 0;
    }

    /**
     * A static factory to have a deep clone of a given Lawn object
     *
     * @param input given Lawn object
     * @return a deep clone of Lawn
     */
    public static Lawn copyFactory(Lawn input) {

        if (input == null) {
            return null;
        }

        Set<Mower> clonedMowers = new HashSet<>();
        for (Mower mower : input.getMowerSet()) {
            clonedMowers.add(Mower.copyFactory(mower));
        }
        return new Lawn(input.width, input.height, clonedMowers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lawn lawn)) return false;
        return getWidth() == lawn.getWidth() && getHeight() == lawn.getHeight() && Objects.equals(getMowerSet(), lawn.getMowerSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWidth(), getHeight(), getMowerSet());
    }
}
