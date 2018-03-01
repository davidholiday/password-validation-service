package com.davidholiday.charter.interview.cdvr.password_validation.validators.bundles;


import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;
import com.davidholiday.charter.interview.cdvr.password_validation.validators.components.BasicPasswordValidatorComponent;
import com.davidholiday.charter.interview.cdvr.password_validation.validators.components.PasswordValidatorComponent;
import com.davidholiday.charter.interview.cdvr.password_validation.validators.components.PasswordValidatorComponentDecorator;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


public abstract class PasswordValidatorBundle {

    private final PasswordValidatorComponent passwordValidatorComponent;
    private final List<Class<? extends PasswordValidatorComponentDecorator>> passwordValidatorDecoratorList;


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


    public List<Pair<String, Boolean>> isPasswordValid(String password) {
        return this.passwordValidatorComponent.isPasswordValid(password);
    }

}
