package com.davidholiday.charter.interview.cdvr.password_validation.bundles;


import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;
import com.davidholiday.charter.interview.cdvr.password_validation.components.BasicPasswordValidatorComponent;
import com.davidholiday.charter.interview.cdvr.password_validation.components.PasswordValidatorComponent;
import com.davidholiday.charter.interview.cdvr.password_validation.components.PasswordValidatorComponentDecorator;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * base for all PasswordValidatorBundles. this is here to save the engineer the need to manually compose
 * decorated PasswordValidatorComponents. A 'bundle' class is really just a list containing the desired
 * PasswordValidatorComponent classes that uses this class's constructor to create a PasswordValidatorComponent
 * object decorated with whatever the engineer wants.
 */
public abstract class PasswordValidatorBundle {

    private final PasswordValidatorComponent passwordValidatorComponent;
    private final List<Class<? extends PasswordValidatorComponentDecorator>> passwordValidatorDecoratorList;


    /**
     * common constructor for all PasswordValidatorBundles. Accepts a list of PasswordValidatorDecorator classes and
     * takes care of the chain of instantiation required to make the decorator pattern work.
     *
     * @param passwordValidatorDecoratorList the list of validator decorator classes representing the list of
     *                                       desired validations the resultant object will apply to a given password.
     */
    PasswordValidatorBundle(List<Class<? extends PasswordValidatorComponentDecorator>> passwordValidatorDecoratorList) {

            PasswordValidatorComponent passwordValidatorComponent = new BasicPasswordValidatorComponent();
            this.passwordValidatorDecoratorList = passwordValidatorDecoratorList;

            for (Class<? extends PasswordValidatorComponentDecorator> passwordValidatorDecoratorClazz :
                    this.passwordValidatorDecoratorList) {

                try {

                    passwordValidatorComponent =
                            passwordValidatorDecoratorClazz.getConstructor(PasswordValidatorComponent.class)
                                                           .newInstance(passwordValidatorComponent);

                } catch (NoSuchMethodException noSuchMethodException) {
                    System.err.println(noSuchMethodException);
                } catch (InstantiationException instantiationException) {
                    System.err.println(instantiationException);
                } catch (IllegalAccessException illegalAccessException) {
                    System.err.println(illegalAccessException);
                } catch (InvocationTargetException invocationTargetException) {
                    System.err.println(invocationTargetException);
                }
            }

            this.passwordValidatorComponent = passwordValidatorComponent;

        }


    /**
     * means of accessing the constituent decorated PasswordValidatorComponent's validation method
     *
     * @param password the password to be validated
     *
     * @return the list of results from each of the validations applied to the provided password
     */
    public List<Pair<String, Boolean>> isPasswordValid(String password) {
        return this.passwordValidatorComponent.isPasswordValid(password);
    }

}
