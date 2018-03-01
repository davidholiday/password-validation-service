package com.davidholiday.charter.interview.cdvr.password_validation.components;


import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;

import java.util.List;

/**
 * interface establishing contract for all PasswordValidatorComponents
 */
public interface PasswordValidatorComponent {
    List<Pair<String, Boolean>> isPasswordValid(String password);
}
