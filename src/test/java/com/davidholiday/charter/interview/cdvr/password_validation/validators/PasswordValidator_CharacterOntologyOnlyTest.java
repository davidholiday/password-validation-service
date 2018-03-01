package com.davidholiday.charter.interview.cdvr.password_validation.validators;

import com.davidholiday.charter.interview.cdvr.password_validation.PasswordValidator;
import com.davidholiday.charter.interview.cdvr.password_validation.bundles.PasswordValidatorBundle_CharacterOntologyOnly;
import org.junit.Assert;
import org.junit.Test;


/**
 * sanity checks the performance of the character ontology only validation bundle
 */
public class PasswordValidator_CharacterOntologyOnlyTest {

    private final PasswordValidatorBundle_CharacterOntologyOnly characterOntologyOnlyValidatorBundle =
            new PasswordValidatorBundle_CharacterOntologyOnly();

    private final PasswordValidator passwordValidator = new PasswordValidator(characterOntologyOnlyValidatorBundle);


    @Test
    public void testPasswordHasSpecialCharacters() {
        String passwordHasSpecialCharacters = "foo!";

        boolean isWithSpecialCharactersValid =
                passwordValidator.isPasswordValidAndWhy(passwordHasSpecialCharacters).getLeft();

        Assert.assertEquals("a password that has special characters shouldn't register as valid!",
                            false,
                            isWithSpecialCharactersValid);

    }


    @Test
    public void testPasswordHasUpperCaseLetters() {
        String passwordHasUpperCaseLetters = "Foo";

        boolean isWithUpperCaseLettersValid =
                passwordValidator.isPasswordValidAndWhy(passwordHasUpperCaseLetters).getLeft();

        Assert.assertEquals("a password that has upper case characters shouldn't register as valid!",
                            false,
                            isWithUpperCaseLettersValid);
    }


    @Test
    public void testPasswordHasOnlyLowerCaseLetters() {
        String passwordHasOnlyLowerCaseLetters = "foo";

        boolean isWithOnlyLowerCaseLettersValid =
                passwordValidator.isPasswordValidAndWhy(passwordHasOnlyLowerCaseLetters).getLeft();

        Assert.assertEquals("a password that has only lower case characters shouldn't register as valid!",
                            false,
                            isWithOnlyLowerCaseLettersValid);

    }


    @Test
    public void testPasswordHasOnlyNumbers() {
        String passwordHasOnlyNumbers = "1234";

        boolean isWithOnlyNumbersValid =
                passwordValidator.isPasswordValidAndWhy(passwordHasOnlyNumbers).getLeft();

        Assert.assertEquals("a password that has only numbers shouldn't register as valid!",
                            false,
                            isWithOnlyNumbersValid);
    }


    @Test
    public void testPasswordHasLowerCaseAndNumbers() {
        String passwordHasOnlyLowerCaseLettersAndNumbers = "foo1234";

        boolean isWithLowerCaseAndNumbersValid =
                passwordValidator.isPasswordValidAndWhy(passwordHasOnlyLowerCaseLettersAndNumbers).getLeft();

        Assert.assertEquals("a password that has only lower case characters and numbers register as valid!",
                            true,
                            isWithLowerCaseAndNumbersValid);

    }


}




