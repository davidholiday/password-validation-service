package com.davidholiday.charter.interview.cdvr.password_validation.validators.components;

import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WithLengthValidationComponent extends PasswordValidatorComponentDecorator {

    public static final String VALIDATOR_CRITERION = "Must be between 5 and 12 characters in length.";
    public static final int LENGTH_FLOOR = 5;
    public static final int LENGTH_CEILING = 12;

    public WithLengthValidationComponent(PasswordValidatorComponent passwordValidator) {
        super(passwordValidator);
    }

    public List<Pair<String, Boolean>> isPasswordValid(String password) {

        boolean isValid =
                (password.length() >= LENGTH_FLOOR) & (password.length() <= LENGTH_CEILING);

        Pair<String, Boolean> resultPair = new Pair<>(VALIDATOR_CRITERION, isValid);

        return Stream.concat(
                super.isPasswordValid(password).stream(),
                Stream.of(resultPair)
        ).collect(Collectors.toCollection(ArrayList::new));

    }

}
