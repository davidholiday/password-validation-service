package com.davidholiday.charter.interview.cdvr.password_validation;

import com.davidholiday.charter.interview.cdvr.password_validation.validators.PasswordValidator;
import com.davidholiday.charter.interview.cdvr.password_validation.validators.bundles.PasswordValidatorBundle_LengthOnly;
import org.junit.Assert;
import org.junit.Test;


public class PasswordValidatorBundle_LengthOnlyTest {

    private final PasswordValidatorBundle_LengthOnly lengthOnlyValidatorBundle =
            new PasswordValidatorBundle_LengthOnly();

    private final PasswordValidator passwordValidator = new PasswordValidator(lengthOnlyValidatorBundle);

    @Test
    public void testPasswordTooShort() {
        String passwordTooShort = "bork";

        boolean isTooShortValid = passwordValidator.isPasswordValidAndWhy(passwordTooShort).getLeft();
        Assert.assertEquals("a password that is too short shouldn't register as valid!",
                            false,
                            isTooShortValid);
    }


    @Test
    public void testPasswordTooLong() {
        String passwordTooLong = "thispasswordislongerthanjustthewordborkthoughiamgladiworkedborkinherethreetimes";

        boolean isTooLongValid = passwordValidator.isPasswordValidAndWhy(passwordTooLong).getLeft();
        Assert.assertEquals("a password that is too short shouldn't register as valid!",
                            false,
                            isTooLongValid);
    }


    @Test
    public void testPasswordValidLength() {
        String passwordJustRight = "startrek1701";

        boolean isJustRightValue = passwordValidator.isPasswordValidAndWhy(passwordJustRight).getLeft();
        Assert.assertEquals("a password that is inside the length range should register as valid!",
                            true,
                            isJustRightValue);
    }


}
