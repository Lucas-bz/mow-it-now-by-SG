package com.bouzidi.samples.mowitnow.validator;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Component
public class FileValidator implements Validator<String[]> {


    @Override
    public ValidationResult validate(String[] rows) {

        String lawnPattern = "^\\d+ \\d+$";

        String instructionsPattern = "[GDA]*";

        String mowerPattern = "^\\d+ \\d+ [N|E|W|S]$";

        List<String> messages = new ArrayList<>();
        Pattern pattern;
        if (rows.length == 0) {
            messages.add("File is empty !");
            return ValidationResult.builder()
                    .isValid(false)
                    .messages(messages).build();
        }

        int i = 0;
        while (i < rows.length) {
            if (i == 0) {
                pattern = Pattern.compile(lawnPattern);
                if (!pattern.matcher(rows[i]).matches()) {
                    messages.add("Can't initialize Lawn from entry " + rows[i]);
                }
            } else if (i % 2 != 0) {
                pattern = Pattern.compile(mowerPattern);
                if (!pattern.matcher(rows[i]).matches()) {
                    messages.add("Can't initialize mower from entry " + rows[i]);
                }
            } else if (i % 2 == 0) {
                pattern = Pattern.compile(instructionsPattern);
                if (!pattern.matcher(rows[i]).matches()) {
                    messages.add("Can't initialize Instructions from entry " + rows[i]);
                }
            }

            i++;
        }
        return ValidationResult.builder().messages(messages).isValid(messages.isEmpty()).build();
    }
}
