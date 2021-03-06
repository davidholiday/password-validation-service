package com.davidholiday.charter.interview.cdvr.password_validation.bundles;


import com.davidholiday.charter.interview.cdvr.password_validation.components.PasswordValidatorComponentDecorator;
import com.davidholiday.charter.interview.cdvr.password_validation.components.WithCharacterOntologyValidationComponent;
import com.davidholiday.charter.interview.cdvr.password_validation.components.WithCharacterSequenceValidationComponent;
import com.davidholiday.charter.interview.cdvr.password_validation.components.WithLengthValidationComponent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * password validation bundle that applies length, character ontology, and character sequence validations
 */
@Component
public class PasswordValidatorBundle_Full extends PasswordValidatorBundle {

    private static final List<Class<? extends PasswordValidatorComponentDecorator>> PASSWORD_VALIDATOR_DECORATOR_LIST =
                Stream.of(
                        WithLengthValidationComponent.class,
                        WithCharacterSequenceValidationComponent.class,
                        WithCharacterOntologyValidationComponent.class
                ).collect(Collectors.toList());


    public PasswordValidatorBundle_Full() {
        super(PASSWORD_VALIDATOR_DECORATOR_LIST);
    }

}
