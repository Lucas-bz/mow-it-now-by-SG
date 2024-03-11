package com.bouzidi.samples.mowitnow.config;


import com.bouzidi.samples.mowitnow.domain.Instruction;
import com.bouzidi.samples.mowitnow.domain.Lawn;
import com.bouzidi.samples.mowitnow.domain.Mower;
import com.bouzidi.samples.mowitnow.domain.Orientation;
import com.bouzidi.samples.mowitnow.domain.Position;
import com.bouzidi.samples.mowitnow.parser.Parser;
import lombok.Data;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayDeque;
import java.util.Queue;

@Configuration
@Data
public class ParserConfig {

    @Getter
    private static int counter = 0;

    @Bean
    public Parser<Lawn> lawnParser() {
        return row -> {
            String[] split = row.split(" ");
            return new Lawn(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        };
    }

    @Bean
    public Parser<Mower> mowerParser() {
        return row -> {
            String[] split = row.split(" ");
            return new Mower(getNextId(), Position.of(Integer.parseInt(split[0]), Integer.parseInt(split[1])),
                    Orientation.fromSymbol(split[2]));
        };
    }

    @Bean
    public Parser<Queue<Instruction>> instructionParser() {
        return row -> {
            Queue<Instruction> instructions = new ArrayDeque<>();
            row.chars().forEach(c -> instructions.add(Instruction.fromSymbol((char) c)));
            return instructions;
        };
    }

    private static int getNextId() {
        return counter++;
    }

}
