package com.davidholiday.charter.interview.cdvr.password_validation.util;

import com.davidholiday.charter.interview.cdvr.password_validation.PasswordValidator;
import com.davidholiday.charter.interview.cdvr.password_validation.bundles.PasswordValidatorBundle_Full;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


/**
 * sanity checks the transformation methods used to prep validation results to be sent as a RESTful response
 */
public class TestValidatorResultsXformer {

    private final PasswordValidatorBundle_Full fullValidatorBundle =
            new PasswordValidatorBundle_Full();

    private final PasswordValidator passwordValidator = new PasswordValidator(fullValidatorBundle);

    @Test
    public void testXformValidatorResultsToJsonString() {
        String password = "foo1234";

        Pair<Boolean, List<Pair<String, Boolean>>> validationResults =
                passwordValidator.isPasswordValidAndWhy(password);

        String actualXformedValidationResults =
                ValidatorResultsXformer.xformValidatorResultsToJsonString(validationResults);

        String expectedXformedValidationResults =
            "{\"IS PASSWORD VALID\":\"false\"," +
                "\"Must not contain any sequence of characters immediately followed by the same sequence.\":\"false\"," +
                "\"Must not be empty.\":\"true\",\"Must be between 5 and 12 characters in length.\":\"true\"," +
                "\"Must consist of a mixture of lowercase letters and numerical digits only, " +
                    "with at least one of each.\":\"true\"}";

        Assert.assertEquals("expected xformed results don't match what actual!",
                            expectedXformedValidationResults,
                            actualXformedValidationResults);


    }
}
