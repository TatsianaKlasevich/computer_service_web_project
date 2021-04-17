package com.klasevich.cms.util.validator;

import static com.klasevich.cms.util.validator.ValidationRegexPattern.FIRST_SEPARATOR;
import static com.klasevich.cms.util.validator.ValidationRegexPattern.SECOND_SEPARATOR;

/**
 * The Class to validate date.
 */
public class DateValidator {
    /**
     * Change date.
     *
     * @param date the date
     * @return changed date
     */
    public static String changeDate(String date) {
        String newDate = date.replaceAll(FIRST_SEPARATOR, SECOND_SEPARATOR);
        return newDate;
    }
}
