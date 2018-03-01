package com.davidholiday.charter.interview.cdvr.password_validation;


import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;
import com.davidholiday.charter.interview.cdvr.password_validation.util.ValidatorResultsXformer;
import com.davidholiday.charter.interview.cdvr.password_validation.validators.PasswordValidator;
import com.davidholiday.charter.interview.cdvr.password_validation.validators.bundles.PasswordValidatorBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ValidatorServiceController {

    @Autowired
    @Qualifier("passwordValidatorBundle_Full")
    private PasswordValidatorBundle passwordValidatorBundle;


    @RequestMapping("/validator")
    public String validator(@RequestParam(value="password") String password) {
        PasswordValidator passwordValidator = new PasswordValidator(passwordValidatorBundle);

        Pair<Boolean, List<Pair<String, Boolean>>> isPasswordValidAndWhy =
                passwordValidator.isPasswordValidAndWhy(password);

        String isPasswordValidAndWhyAsJsonString =
                ValidatorResultsXformer.xformValidatorResultsToJsonString(isPasswordValidAndWhy);

        return isPasswordValidAndWhyAsJsonString;
    }

}
