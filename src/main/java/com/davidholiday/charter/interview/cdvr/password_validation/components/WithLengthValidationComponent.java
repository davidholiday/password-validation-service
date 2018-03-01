package com.davidholiday.charter.interview.cdvr.password_validation.components;

import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * PasswordValidatorComponentDecorator that adds validation check for min/max password length
 */
public class WithLengthValidationComponent extends PasswordValidatorComponentDecorator {

    public static final String VALIDATOR_CRITERION = "Must be between 5 and 12 characters in length.";
    public static final int LENGTH_FLOOR = 5;
    public static final int LENGTH_CEILING = 12;

    public WithLengthValidationComponent(PasswordValidatorComponent passwordValidator) {
        super(passwordValidator);
    }

    /**
     * tells you whether or not the supplied password is withing the min/max length bounds
     *
     * @param password the password to be validated
     * @return boolean indicating pass/fail of validation
     */
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
