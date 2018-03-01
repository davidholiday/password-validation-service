package com.davidholiday.charter.interview.cdvr.password_validation.components;

import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * PasswordValidatorComponentDecorator that adds validation checks for subsequent repetition of sub-sequences
 */
public class WithCharacterSequenceValidationComponent extends PasswordValidatorComponentDecorator {

    public static final String VALIDATOR_CRITERION =
            "Must not contain any sequence of characters immediately followed by the same sequence.";

    public static final String REGEX = "(.+?)(\\1+?)";


    public WithCharacterSequenceValidationComponent(PasswordValidatorComponent passwordValidator) {
        super(passwordValidator);
    }


    /**
     * tells you whether or not the supplied password has subsequent repeated sub-sequences
     *
     * @param password the password to be validated
     * @return boolean indicating pass/fail of validation
     */
    public List<Pair<String, Boolean>> isPasswordValid(String password) {

        // we're using 'find' and not 'match' because we want to know if there are substrings that meet the criteria
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(password);

        // if the matcher finds repeated anything (ie reports true) then the password is invalid -- hence the negation
        boolean isValid = !matcher.find();

        Pair<String, Boolean> resultPair = new Pair<>(VALIDATOR_CRITERION, isValid);

        return Stream.concat(
                super.isPasswordValid(password).stream(),
                Stream.of(resultPair)
        ).collect(Collectors.toCollection(ArrayList::new));
    }

}
