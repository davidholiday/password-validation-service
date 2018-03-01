package com.davidholiday.charter.interview.cdvr.password_validation.validators;


import com.davidholiday.charter.interview.cdvr.password_validation.PasswordValidator;
import com.davidholiday.charter.interview.cdvr.password_validation.bundles.PasswordValidatorBundle_Full;
import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;
import com.davidholiday.charter.interview.cdvr.password_validation.components.WithCharacterOntologyValidationComponent;
import com.davidholiday.charter.interview.cdvr.password_validation.components.WithCharacterSequenceValidationComponent;
import com.davidholiday.charter.interview.cdvr.password_validation.components.WithLengthValidationComponent;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;


/**
 * sanity checks the performance of the full password validation bundle
 */
public class PasswordValidatorBundleFullTest {

    private final PasswordValidatorBundle_Full fullValidatorBundle =
            new PasswordValidatorBundle_Full();

    private final PasswordValidator passwordValidator = new PasswordValidator(fullValidatorBundle);


    @Test
    public void testInvalidLength() {
        String passwordTooShort = "b0rk";

        Pair<Boolean, List<Pair<String, Boolean>>> validatorResults =
                passwordValidator.isPasswordValidAndWhy(passwordTooShort);

        // check to make sure we've correctly rejected this password
        boolean isTooShortValid = validatorResults.getLeft();
        Assert.assertEquals("a password that is too short shouldn't register as valid!",
                            false,
                            isTooShortValid);

        // now make sure we've rejected this password for the correct reason
        List<Pair<String, Boolean>> andWhyList = validatorResults.getRight();
        for (Pair<String, Boolean> componentResult : andWhyList) {

            if (componentResult.getLeft() == WithLengthValidationComponent.VALIDATOR_CRITERION) {
                Assert.assertEquals("a password that is too short shouldn't pass the length check!",
                                    false,
                                    componentResult.getRight());
            } else {
                String message =
                        "a password that is too short should only fail the length check! " +
                                "\n FAILED CHECK IS: "
                                + componentResult.getLeft();

                Assert.assertEquals(message,
                                    true,
                                    componentResult.getRight());
            }
        }
    }


    @Test
    public void testInvalidCharacterOntology() {
        String passwordInvalidCharacterOntology = "b0rked!";

        Pair<Boolean, List<Pair<String, Boolean>>> validatorResults =
                passwordValidator.isPasswordValidAndWhy(passwordInvalidCharacterOntology);

        // check to make sure we've correctly rejected this password
        boolean isInvalidCharacterOntologyValid = validatorResults.getLeft();
        Assert.assertEquals("a password that has invalid characters shouldn't register as valid!",
                            false,
                            isInvalidCharacterOntologyValid);

        // now make sure we've rejected this password for the correct reason
        List<Pair<String, Boolean>> andWhyList = validatorResults.getRight();
        for (Pair<String, Boolean> componentResult : andWhyList) {

            if (componentResult.getLeft() == WithCharacterOntologyValidationComponent.VALIDATOR_CRITERION) {
                Assert.assertEquals("a password that has invalid characters shouldn't register as valid!",
                        false,
                        componentResult.getRight());
            } else {
                String message =
                        "a password is valid in all respects except character ontology shouldn't fail validation for " +
                                "other reasons! \n FAILED CHECK IS: "
                                + componentResult.getLeft();

                Assert.assertEquals(message,
                        true,
                        componentResult.getRight());
            }
        }
    }


    @Test
    public void testInvalidCharacterSequence() {
        String passwordInvalidCharacterSequence = "b0rkb0rk";

        Pair<Boolean, List<Pair<String, Boolean>>> validatorResults =
                passwordValidator.isPasswordValidAndWhy(passwordInvalidCharacterSequence);

        // check to make sure we've correctly rejected this password
        boolean isInvalidCharacterSequenceValid = validatorResults.getLeft();
        Assert.assertEquals("a password that has invalid sequences shouldn't register as valid!",
                            false,
                            isInvalidCharacterSequenceValid);

        // now make sure we've rejected this password for the correct reason
        List<Pair<String, Boolean>> andWhyList = validatorResults.getRight();
        for (Pair<String, Boolean> componentResult : andWhyList) {

            if (componentResult.getLeft() == WithCharacterSequenceValidationComponent.VALIDATOR_CRITERION) {
                Assert.assertEquals("a password that has invalid sequences shouldn't register as valid!",
                                    false,
                                    componentResult.getRight());
            } else {
                String message =
                        "a password is valid in all respects except character sequence shouldn't fail validation for " +
                                "other reasons! \n FAILED CHECK IS: "
                                + componentResult.getLeft();

                Assert.assertEquals(message,
                                    true,
                                    componentResult.getRight());
            }
        }
    }


}
