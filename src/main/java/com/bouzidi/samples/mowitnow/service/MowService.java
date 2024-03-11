package com.bouzidi.samples.mowitnow.service;


import com.bouzidi.samples.mowitnow.domain.Instruction;
import com.bouzidi.samples.mowitnow.domain.Lawn;
import com.bouzidi.samples.mowitnow.domain.Mower;
import com.bouzidi.samples.mowitnow.exceptions.FileFormatException;
import com.bouzidi.samples.mowitnow.parser.Parser;
import com.bouzidi.samples.mowitnow.validator.FileValidator;
import com.bouzidi.samples.mowitnow.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;


/**
 * Mower Service is able to process an array of Strings, validate and process It
 */

@Service
public class MowService {


    private final Parser<Lawn> lawnParser;

    private final Parser<Mower> mowerParser;

    private final Parser<Queue<Instruction>> instructionParser;


    private final FileValidator validator;

    public MowService(@Autowired Parser<Lawn> lawnParser, @Autowired Parser<Mower> mowerParser,
                      @Autowired Parser<Queue<Instruction>> instructionParser, @Autowired FileValidator validator) {
        this.lawnParser = lawnParser;
        this.mowerParser = mowerParser;
        this.instructionParser = instructionParser;
        this.validator = validator;
    }

    public Lawn processEntry(String[] rows) {
        ValidationResult validationResult = validator.validate(rows);
        if (validationResult.isValid()) {
            Lawn lawn = lawnParser.parse(rows[0]);
            int i = 1;
            Set<Mower> mowers = new HashSet<>();
            while (i < rows.length) {
                Mower mower = mowerParser.parse(rows[i++]);
                mower.setInstructions(instructionParser.parse(rows[i++]));
                mowers.add(mower);
            }
            lawn.setMowerSet(mowers);
            return lawn;
        } else {
            throw new FileFormatException(validationResult.getMessages());
        }

    }

    public Lawn executeMowers(Lawn initial) {
        Lawn lawn = Lawn.copyFactory(initial);
        for (Mower mower : lawn.getMowerSet()) {
            mower.getInstructions().forEach(instruction -> {
                if (instruction == Instruction.FORWARD) {
                    if (!lawn.isOutGrid(mower.getOrientation().getNext(mower.getPosition()))) {
                        instruction.execute(mower);
                    }
                } else {
                    instruction.execute(mower);
                }
            });
        }
        return lawn;
    }
}
