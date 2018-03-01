package com.davidholiday.charter.interview.cdvr.password_validation.bundles;


import com.davidholiday.charter.interview.cdvr.password_validation.components.PasswordValidatorComponentDecorator;
import com.davidholiday.charter.interview.cdvr.password_validation.components.WithLengthValidationComponent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * password validation bundle that applies only a length validation
 */
@Component
public class PasswordValidatorBundle_LengthOnly extends PasswordValidatorBundle {

    private static final List<Class<? extends PasswordValidatorComponentDecorator>> PASSWORD_VALIDATOR_DECORATOR_LIST =
            Stream.of(
                    WithLengthValidationComponent.class
            ).collect(Collectors.toList());


    public PasswordValidatorBundle_LengthOnly() {
        super(PASSWORD_VALIDATOR_DECORATOR_LIST);
    }

}
