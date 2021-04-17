package com.klasevich.cms.util.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;

import static com.klasevich.cms.util.validator.ValidationRegexPattern.*;

/**
 * The class with General validators for all fields of project objects
 */
public class GeneralValidator {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Check if category name valid.
     *
     * @param category the type of category
     * @return true if category name is valid
     */
    public static boolean isValidCategoryName(String category) {
        if (category == null || category.isBlank()) {
            return false;
        }
        Matcher matcher = CATEGORY_NAME_REGEX.matcher(category);
        logger.info("category valid {}", matcher.matches());
        return matcher.matches();
    }

    /**
     * Check if service name valid.
     *
     * @param service the type of service
     * @return true if service name is valid
     */
    public static boolean isValidServiceName(String service) {
        if (service == null || service.isBlank()) {
            return false;
        }
        Matcher matcher = SERVICE_NAME_REGEX.matcher(service);
        logger.info("service name valid {}", matcher.matches());
        return matcher.matches();
    }

    /**
     * Check if id valid.
     *
     * @param idString id
     * @return true if id is valid
     */
    public static boolean isValidId(String idString) {
        if (idString == null || idString.isBlank()) {
            return false;
        }
        Matcher matcher = ID_REGEX.matcher(idString);
        int id = Integer.parseInt(idString);
        logger.info("id valid {}", matcher.matches() && id > 0);
        return matcher.matches() && id > 0;
    }

    /**
     * Check if price valid.
     *
     * @param price the service price
     * @return true if price is valid
     */
    public static boolean isValidPrice(String price) {
        if (price == null || price.isBlank() || price.equals("0")) {
            return false;
        }
        Matcher matcher = PRICE_REGEX.matcher(price);
        logger.info("price valid {}", matcher.matches());
        return matcher.matches();
    }

    /**
     * Check if date valid.
     *
     * @param date the date
     * @return true if date is valid
     */
    public static boolean isValidDate(String date) {
        if (date == null || date.isBlank()) {
            return false;
        }
        Matcher matcher = DATE_REGEX.matcher(date);
        logger.info("data valid {}", matcher.matches());
        return matcher.matches();
    }

    /**
     * Check if address valid.
     *
     * @param address the user's address
     * @return true if address is valid
     */
    public static boolean isValidAddress(String address) {
        if (address == null || address.isBlank()) {
            return false;
        }
        Matcher matcher = ADDRESS_REGEX.matcher(address);
        logger.info("address valid {}", matcher.matches());
        return matcher.matches();
    }

    /**
     * Check if problem valid.
     *
     * @param problem the problem
     * @return true if problem is valid
     */
    public static boolean isValidProblem(String problem) {
        if (problem == null || problem.isBlank()) {
            return false;
        }
        Matcher matcher = PROBLEM_REGEX.matcher(problem);
        logger.info("problem valid {}", matcher.matches());
        return matcher.matches();
    }

    /**
     * Check if name valid.
     *
     * @param name the name
     * @return true if name is valid
     */
    public static boolean isValidName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        Matcher matcher = NAME_REGEX.matcher(name);
        logger.info("name valid {}", matcher.matches());
        return matcher.matches();
    }

    /**
     * Check if surname valid.
     *
     * @param surname the surname
     * @return true if surname is valid
     */
    public static boolean isValidSurname(String surname) {
        if (surname == null || surname.isBlank()) {
            return false;
        }
        Matcher matcher = SURNAME_REGEX.matcher(surname);
        logger.info("surname valid {}", matcher.matches());
        return matcher.matches();
    }

    /**
     * Check if email valid.
     *
     * @param mail the email
     * @return true if email is valid
     */
    public static boolean isValidMail(String mail) {
        if (mail == null || mail.isBlank()) {
            return false;
        }
        Matcher matcher = MAIL_REGEX.matcher(mail);
        logger.info("mail valid {}", matcher.matches());
        return matcher.matches();
    }

    /**
     * Check if phone valid.
     *
     * @param phone the phone number
     * @return true if phone number is valid
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.isBlank()) {
            return false;
        }
        Matcher matcher = PHONE_REGEX.matcher(phone);
        logger.info("phone valid {}", matcher.matches());
        return matcher.matches();
    }

    /**
     * Check if password valid.
     *
     * @param password the password
     * @return true if password is valid
     */
    public static boolean isValidPass(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }
        Matcher matcher = PASS_REGEX.matcher(password);
        logger.info("password valid {}", matcher.matches());
        return matcher.matches();
    }

    /**
     * Check if repeated password valid.
     *
     * @param password         the password
     * @param repeatedPassword the repeated password
     * @return true if repeated password is valid
     */
    public static boolean isValidRepeatedPass(String password, String repeatedPassword) {
        if (password == null || repeatedPassword == null || password.isBlank() || repeatedPassword.isBlank()) {
            return false;
        }
        Matcher matcher = PASS_REGEX.matcher(password);
        boolean result = password.equals(repeatedPassword) && matcher.matches();
        logger.info("repassword valid {}", password.equals(repeatedPassword) && matcher.matches());
        return result;
    }

}
