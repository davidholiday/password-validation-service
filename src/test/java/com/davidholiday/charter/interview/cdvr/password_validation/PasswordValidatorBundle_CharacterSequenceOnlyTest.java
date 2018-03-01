package com.davidholiday.charter.interview.cdvr.password_validation;

import com.davidholiday.charter.interview.cdvr.password_validation.validators.PasswordValidator;
import com.davidholiday.charter.interview.cdvr.password_validation.validators.bundles.PasswordValidatorBundle_CharacterSequenceOnly;
import org.junit.Assert;
import org.junit.Test;


public class PasswordValidatorBundle_CharacterSequenceOnlyTest {

    private final PasswordValidatorBundle_CharacterSequenceOnly characterSequenceOnlyValidatorBundle =
            new PasswordValidatorBundle_CharacterSequenceOnly();

    private final PasswordValidator passwordValidator = new PasswordValidator(characterSequenceOnlyValidatorBundle);


    @Test
    public void testPasswordHasSingleConsecutiveRepeatedCharacter() {
        String passwordHasSingleConsecutiveRepeatedCharacter = "foo";

        boolean isPasswordHasSingleConsecutiveRepeatedCharacterValid =
                passwordValidator.isPasswordValidAndWhy(passwordHasSingleConsecutiveRepeatedCharacter).getLeft();

        Assert.assertEquals("a password that has consecutive repeated characters shouldn't register as valid!",
                            false,
                            isPasswordHasSingleConsecutiveRepeatedCharacterValid);
    }


    @Test
    public void testPasswordHasRepeatedNonConsecutiveSequence() {
        String passwordHasRepeatedNonConsecutiveSequence = "abcxabc";

        boolean isPasswordHasRepeatedNonConsecutiveSequenceValid =
                passwordValidator.isPasswordValidAndWhy(passwordHasRepeatedNonConsecutiveSequence).getLeft();

        Assert.assertEquals("a password that has non-consecutive repeated sequences should register as valid!",
                            true,
                            isPasswordHasRepeatedNonConsecutiveSequenceValid);
    }


    @Test
    public void testPasswordHasRepeatedConsecutiveSequence() {
        String passwordHasRepeatedConsecutiveSequence = "987abcabc";

        boolean isPasswordHasRepeatedConsecutiveSequenceValid =
                passwordValidator.isPasswordValidAndWhy(passwordHasRepeatedConsecutiveSequence).getLeft();

        Assert.assertEquals("a password that has consecutive repeated sequences shouldn't register as valid!",
                            false,
                            isPasswordHasRepeatedConsecutiveSequenceValid);
    }

}
