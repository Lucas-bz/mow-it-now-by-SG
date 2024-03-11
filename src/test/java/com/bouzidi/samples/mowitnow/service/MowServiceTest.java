package com.bouzidi.samples.mowitnow.service;

import com.bouzidi.samples.mowitnow.config.ParserConfig;
import com.bouzidi.samples.mowitnow.domain.Instruction;
import com.bouzidi.samples.mowitnow.domain.Lawn;
import com.bouzidi.samples.mowitnow.domain.Mower;
import com.bouzidi.samples.mowitnow.domain.Orientation;
import com.bouzidi.samples.mowitnow.domain.Position;
import com.bouzidi.samples.mowitnow.exceptions.FileFormatException;
import com.bouzidi.samples.mowitnow.parser.Parser;
import com.bouzidi.samples.mowitnow.validator.FileValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MowService.class)
@ContextConfiguration(classes = {ParserConfig.class, FileValidator.class})
class MowServiceTest {


    private final String[] validArray = {"5 5", "1 2 N", "GAD"};

    private final String[] invalidArray = {"5 X", "1 N", "XXX"};

    @Autowired
    private Parser<Lawn> lawnParser;

    @Autowired
    private Parser<Mower> mowerParser;

    @Autowired
    private Parser<Queue<Instruction>> instructionParser;

    @Autowired
    private FileValidator validator;

    @Autowired
    private MowService mowService;


    private static Lawn initial;
    private static Lawn expected;


    @BeforeEach
    void setup() {
        Queue<Instruction> instructions = new ArrayDeque<>();
        instructions.add(Instruction.ROTATE_LEFT);
        instructions.add(Instruction.FORWARD);
        instructions.add(Instruction.ROTATE_RIGHT);
        int initialValue = ParserConfig.getCounter();

        initial = new Lawn(5, 5, Set.of(new Mower(initialValue, Position.of(1, 2),
                new ArrayList<>(), Orientation.NORTH, instructions)));
        expected = new Lawn(5, 5, Set.of(new Mower(initialValue, Position.of(0, 2),
                List.of(Position.of(1, 2)), Orientation.NORTH, instructions)));

    }


    @Test
    void test_processEntry_isValid() {
        Lawn result = mowService.processEntry(validArray);
        assertEquals(initial.getWidth(), result.getWidth());
        assertEquals(initial.getHeight(), result.getHeight());
        assertEquals(initial.getMowerSet().size(), result.getMowerSet().size());
    }

    @Test
    void test_2_executeMowers() {
        Lawn result = mowService.executeMowers(initial);
        assertThat(result.getMowerSet(), Matchers.containsInAnyOrder(expected.getMowerSet().toArray()));


    }

    @Test
    void test_fail_format() {
        FileFormatException thrown = Assertions.assertThrows(FileFormatException.class, () -> mowService.processEntry(invalidArray));
        assertEquals("Can't initialize Lawn from entry 5 X", thrown.getMessages().get(0));

    }
}