package com.klasevich.cms.util.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;

import static com.klasevich.cms.util.validator.ValidationRegexPattern.*;

public class GeneralValidator {
    private static final Logger logger = LogManager.getLogger();

    public static boolean isValidCategoryName(String category) {
        if (category== null||category.isBlank()){
            return false;
        }
        Matcher matcher = CATEGORY_NAME_REGEX.matcher(category);
        logger.info("category valid {}", matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidServiceName(String service) {
        if (service== null||service.isBlank()){
            return false;
        }
        Matcher matcher = SERVICE_NAME_REGEX.matcher(service);
        logger.info("service name valid {}", matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidId(String idString) {
        if (idString== null||idString.isBlank()){
            return false;
        }
        Matcher matcher = ID_REGEX.matcher(idString);
        int id = Integer.parseInt(idString);
        logger.info("id valid {}", matcher.matches() && id > 0);
        return matcher.matches() && id > 0;
    }

    public static boolean isValidPrice(String price) {
        if (price== null||price.isBlank()||price.equals("0")){
            return false;
        }
        Matcher matcher = PRICE_REGEX.matcher(price);
        logger.info("price valid {}", matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidDate(String date) {
        if (date== null||date.isBlank()){
            return false;
        }
        Matcher matcher = DATE_REGEX.matcher(date);
        logger.info("data valid {}", matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidAddress(String address) {
        if (address== null||address.isBlank()){
            return false;
        }
        Matcher matcher = ADDRESS_REGEX.matcher(address);
        logger.info("address valid {}", matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidProblem(String problem) {
        if (problem== null||problem.isBlank()){
            return false;
        }
        Matcher matcher = PROBLEM_REGEX.matcher(problem);
        logger.info("problem valid {}", matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidName(String name) {
        if (name== null||name.isBlank()){
            return false;
        }
        Matcher matcher = NAME_REGEX.matcher(name);
        logger.info("name valid {}", matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidSurname(String surname) {
        if (surname== null||surname.isBlank()){
            return false;
        }
        Matcher matcher = SURNAME_REGEX.matcher(surname);
        logger.info("surname valid {}", matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidMail(String mail) {
        if (mail== null||mail.isBlank()){
            return false;
        }
        Matcher matcher = MAIL_REGEX.matcher(mail);
        logger.info("mail valid {}", matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidPhone(String phone) {
        if (phone== null||phone.isBlank()){
            return false;
        }
        Matcher matcher = PHONE_REGEX.matcher(phone);
        logger.info("phone valid {}", matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidPass(String password) {
        if (password== null||password.isBlank()){
            return false;
        }
        Matcher matcher = PASS_REGEX.matcher(password);
        logger.info("password valid {}", matcher.matches());
        return matcher.matches();
    }

    public static boolean isValidRepeatedPass(String password, String repeatedPassword) {
        if (password== null||repeatedPassword==null||password.isBlank()||repeatedPassword.isBlank()){
            return false;
        }
        Matcher matcher = PASS_REGEX.matcher(password);
        boolean result = password.equals(repeatedPassword) && matcher.matches();
        logger.info("repassword valid {}", password.equals(repeatedPassword) && matcher.matches());
        return result;
    }

}
