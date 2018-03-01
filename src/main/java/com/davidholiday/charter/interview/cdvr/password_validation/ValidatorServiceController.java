package com.davidholiday.charter.interview.cdvr.password_validation;


import com.davidholiday.charter.interview.cdvr.password_validation.util.Pair;
import com.davidholiday.charter.interview.cdvr.password_validation.util.ValidatorResultsXformer;
import com.davidholiday.charter.interview.cdvr.password_validation.bundles.PasswordValidatorBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * establishes the /validator endpoint and uses dependency injection to hotwire password validation criteria
 */
@RestController
public class ValidatorServiceController {

    @Autowired
    @Qualifier("passwordValidatorBundle_Full")
    private PasswordValidatorBundle passwordValidatorBundle;

    /**
     * handles requests to the /validator endpoint
     *
     * @param password password to be validated
     * @return json string representing whether or not the password is valid, what checks were run, and what the
     *  results of the checks were
     */
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
