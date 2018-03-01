package com.davidholiday.charter.interview.cdvr.password_validation.validators;

import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;
import com.davidholiday.charter.interview.cdvr.password_validation.validators.bundles.PasswordValidatorBundle;

import java.util.List;

public class PasswordValidator {


    private final PasswordValidatorBundle passwordValidatorBundle;


    public PasswordValidator(PasswordValidatorBundle passwordValidator) {
        this.passwordValidatorBundle = passwordValidator;
    }


    public Pair< Boolean, List<Pair<String, Boolean>> > isPasswordValidAndWhy(String password) {
        List<Pair<String, Boolean>> validationResults = passwordValidatorBundle.isPasswordValid(password);

        long invalidCount = validationResults.stream()
                                             .filter(p -> p.getRight() == false)
                                             .count();

        boolean isPasswordValid = (invalidCount == 0) ? (true) : (false);
        return new Pair<>(isPasswordValid, validationResults);
    }

}
