package com.davidholiday.charter.interview.cdvr.password_validation.validators.components;


import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;

import java.util.List;


public interface PasswordValidatorComponent {
    List<Pair<String, Boolean>> isPasswordValid(String password);
}
