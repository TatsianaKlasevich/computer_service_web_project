package com.klasevich.cms.util.validator;

import static com.klasevich.cms.util.validator.ValidationRegexPattern.FIRST_SEPARATOR;
import static com.klasevich.cms.util.validator.ValidationRegexPattern.SECOND_SEPARATOR;

public class DateValidator {
    public static String changeDate(String date) {
        String newDate = date.replaceAll(FIRST_SEPARATOR, SECOND_SEPARATOR);
        return newDate;
    }
}
