package com.davidholiday.charter.interview.cdvr.password_validation.validators.components;


import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;

import java.util.List;


public abstract class PasswordValidatorComponentDecorator implements PasswordValidatorComponent {

    protected final PasswordValidatorComponent passwordValidator;

    public PasswordValidatorComponentDecorator(PasswordValidatorComponent passwordValidator) {
        this.passwordValidator = passwordValidator;
    }

    @Override
    public List<Pair<String, Boolean>> isPasswordValid(String password) {
        return this.passwordValidator.isPasswordValid(password);
    }

}
