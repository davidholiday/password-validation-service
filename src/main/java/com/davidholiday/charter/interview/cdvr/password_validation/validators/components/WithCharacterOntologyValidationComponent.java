package com.davidholiday.charter.interview.cdvr.password_validation.validators.components;

import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WithCharacterOntologyValidationComponent extends PasswordValidatorComponentDecorator {

    public static final String VALIDATOR_CRITERION =
            "Must consist of a mixture of lowercase letters and numerical digits only, with at least one of each.";

    public static final String REGEX = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]*$";

    public WithCharacterOntologyValidationComponent(PasswordValidatorComponent passwordValidator) {
        super(passwordValidator);
    }

    public List<Pair<String, Boolean>> isPasswordValid(String password) {
        boolean isValid = password.matches(REGEX);
        Pair<String, Boolean> resultPair = new Pair<>(VALIDATOR_CRITERION, isValid);

        return Stream.concat(
                super.isPasswordValid(password).stream(),
                Stream.of(resultPair)
        ).collect(Collectors.toCollection(ArrayList::new));
    }

}
