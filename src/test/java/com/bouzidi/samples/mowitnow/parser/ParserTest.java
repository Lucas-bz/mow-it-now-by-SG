package com.bouzidi.samples.mowitnow.parser;

import com.bouzidi.samples.mowitnow.config.ParserConfig;
import com.bouzidi.samples.mowitnow.domain.Instruction;
import com.bouzidi.samples.mowitnow.domain.Lawn;
import com.bouzidi.samples.mowitnow.domain.Mower;
import com.bouzidi.samples.mowitnow.domain.Orientation;
import com.bouzidi.samples.mowitnow.domain.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(classes = {ParserConfig.class})
class ParserTest {


    @Autowired
    private Parser<Mower> mowerParser;

    @Autowired
    private Parser<Lawn> lawnParser;

    @Autowired
    private Parser<Queue<Instruction>> instructionParser;


    @Test
    void testValidMower() {
        String valid = "1 5 W";

        Orientation expectedOrientation = Orientation.WEST;
        Position expectedPosition = Position.of(1, 5);

        Mower actual = mowerParser.parse(valid);

        assertEquals(expectedOrientation, actual.getOrientation());
        assertEquals(expectedPosition, actual.getPosition());

    }

    @Test
    void testValidLawn() {
        String valid = "1 5";
        Lawn expected = new Lawn(1, 5);
        Lawn actual = lawnParser.parse(valid);
        assertEquals(expected, actual);
    }

    @Test
    void testValidInstructions() {
        String valid = "AAAGD";
        Queue<Instruction> expected = new ArrayDeque<>();
        expected.add(Instruction.FORWARD);
        expected.add(Instruction.FORWARD);
        expected.add(Instruction.FORWARD);
        expected.add(Instruction.ROTATE_LEFT);
        expected.add(Instruction.ROTATE_RIGHT);
        Queue<Instruction> actual = instructionParser.parse(valid);
        assertEquals(expected.stream().toList(), actual.stream().toList());
    }


}
