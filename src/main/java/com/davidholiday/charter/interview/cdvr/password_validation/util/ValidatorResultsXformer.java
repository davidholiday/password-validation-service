package com.davidholiday.charter.interview.cdvr.password_validation.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidatorResultsXformer {

    public static String xformValidatorResultsToJsonString(
            Pair<Boolean, List<Pair<String, Boolean>>> validatorResults) {

        Map<String, String> validatorResultsAsMap = new HashMap<>();
        validatorResultsAsMap.put("IS_PASSWORD_VALID", validatorResults.getLeft().toString());

        List<Pair<String, Boolean>> andWhyList = validatorResults.getRight();
        for (Pair<String, Boolean> componentResult : andWhyList) {
            validatorResultsAsMap.put(componentResult.getLeft(), componentResult.getRight().toString());
        }

        String validatorResultsAsJsonString = validatorResults.getLeft().toString();
        try {
            validatorResultsAsJsonString = new ObjectMapper().writeValueAsString(validatorResultsAsMap);
        } catch (JsonProcessingException e) {
            System.err.println("processing error xforming result list -- returning global is valid boolean instead");
        }

        return validatorResultsAsJsonString;
    }

}
