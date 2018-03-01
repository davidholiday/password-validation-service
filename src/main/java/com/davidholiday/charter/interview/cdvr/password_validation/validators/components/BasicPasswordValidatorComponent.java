package com.davidholiday.charter.interview.cdvr.password_validation.validators.components;

import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicPasswordValidatorComponent implements PasswordValidatorComponent {

    public static final String VALIDATOR_CRITERION = "Must not be empty.";

    public List<Pair<String, Boolean>> isPasswordValid(String password) {
        boolean isValid = !password.isEmpty();
        Pair<String, Boolean> resultPair = new Pair<>(VALIDATOR_CRITERION, isValid);
        return Stream.of(resultPair).collect(Collectors.toList());
    }

}
