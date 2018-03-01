package com.davidholiday.charter.interview.cdvr.password_validation;

import com.davidholiday.charter.interview.cdvr.password_validation.bundles.PasswordValidatorBundle;
import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;

import java.util.List;


/**
 * wrapper for a given password validator bundle. think of the bundle like an engine and this as the rest of the car
 */
public class PasswordValidator {

    private final PasswordValidatorBundle passwordValidatorBundle;


    /**
     * constructor
     *
     * @param passwordValidatorBundle the set of validation checks to apply to passwords
     */
    public PasswordValidator(PasswordValidatorBundle passwordValidatorBundle) {
        this.passwordValidatorBundle = passwordValidatorBundle;
    }


    /**
     * wraps around the validator bundle's isPasswordValid method to return a meta-boolean telling you whether or not
     * all the checks passed.
     *
     * @param password the password to be validated
     *
     * @return a tuple containing both a boolean indicating global validation status and a list of individual
     *   validation results.
     */
    public Pair< Boolean, List<Pair<String, Boolean>> > isPasswordValidAndWhy(String password) {
        List<Pair<String, Boolean>> validationResults = passwordValidatorBundle.isPasswordValid(password);

        long invalidCount = validationResults.stream()
                                             .filter(p -> p.getRight() == false)
                                             .count();

        boolean isPasswordValid = (invalidCount == 0) ? (true) : (false);
        return new Pair<>(isPasswordValid, validationResults);
    }

}
