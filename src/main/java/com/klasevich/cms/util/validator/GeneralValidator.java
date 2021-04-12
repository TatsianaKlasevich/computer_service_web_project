package com.klasevich.cms.util.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;

import static com.klasevich.cms.util.validator.ValidationRegexPattern.*;

public class GeneralValidator {
    private static final Logger logger = LogManager.getLogger();

    public static boolean isValidCategoryName(String category) {
        Matcher matcher = CATEGORY_NAME_REGEX.matcher(category);
        logger.debug(matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidServiceName(String service) {
        Matcher matcher = SERVICE_NAME_REGEX.matcher(service);
        logger.debug(matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidId(String idString) {
        Matcher matcher = ID_REGEX.matcher(idString);
        int id = Integer.parseInt(idString);
        logger.debug(matcher.matches());
        boolean result = matcher.matches() && id > 0;
        return result;
    }

    public static boolean isValidPrice(String price) {
        Matcher matcher = PRICE_REGEX.matcher(price);
        logger.debug(matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidDate(String date) {
        Matcher matcher = DATE_REGEX.matcher(date);
        logger.debug(matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidAddress(String address) {
        Matcher matcher = ADDRESS_REGEX.matcher(address);
        logger.debug(matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidProblem(String problem) {
        Matcher matcher = PROBLEM_REGEX.matcher(problem);
        logger.debug(matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidName(String name) {
        Matcher matcher = NAME_REGEX.matcher(name);
        logger.debug(matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidSurname(String surname) {
        Matcher matcher = SURNAME_REGEX.matcher(surname);
        logger.debug(matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidMail(String mail) {
        Matcher matcher = MAIL_REGEX.matcher(mail);
        logger.debug(matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidPhone(String phone) {
        Matcher matcher = PHONE_REGEX.matcher(phone);
        logger.debug(matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidPass(String password) {
        Matcher matcher = PASS_REGEX.matcher(password);
        logger.debug("is valid pass {}", matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidRepeatedPass(String password, String repeatedPassword) {
        Matcher matcher = PASS_REGEX.matcher(password);
        boolean result = password.equals(repeatedPassword) && matcher.matches();
        logger.debug("is valid repeated pass {}", result);
        return result;
    }

}
