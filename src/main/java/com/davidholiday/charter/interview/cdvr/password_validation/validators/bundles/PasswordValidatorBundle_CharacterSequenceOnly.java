package com.davidholiday.charter.interview.cdvr.password_validation.validators.bundles;

import com.davidholiday.charter.interview.cdvr.password_validation.validators.components.PasswordValidatorComponentDecorator;
import com.davidholiday.charter.interview.cdvr.password_validation.validators.components.WithCharacterSequenceValidationComponent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class PasswordValidatorBundle_CharacterSequenceOnly extends PasswordValidatorBundle {

    private static final List<Class<? extends PasswordValidatorComponentDecorator>> PASSWORD_VALIDATOR_DECORATOR_LIST =
            Stream.of(
                    WithCharacterSequenceValidationComponent.class
            ).collect(Collectors.toList());


    public PasswordValidatorBundle_CharacterSequenceOnly() {
        super(PASSWORD_VALIDATOR_DECORATOR_LIST);
    }

}
