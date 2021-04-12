package com.klasevich.cms.util.validator;

import com.klasevich.cms.util.MessageManager;

import java.util.Map;

import static com.klasevich.cms.command.command_parameter.RequestParameter.*;
import static com.klasevich.cms.util.validator.GeneralValidator.*;

public class UserValidator {

    public static boolean isValidRegistration(Map<String, String> data) {
        boolean result = true;
        String name = data.get(PARAM_NAME);
        if (!isValidName(name)) {
            data.put(PARAM_NAME, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        String surname = data.get(PARAM_SURNAME);
        if (!isValidSurname(surname)) {
            data.put(PARAM_SURNAME, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        String mail = data.get(PARAM_MAIL);
        if (!isValidMail(mail)) {
            data.put(PARAM_MAIL, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        String phone = data.get(PARAM_PHONE);
        if (!isValidPhone(phone)) {
            data.put(PARAM_PHONE, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        String password = data.get(PARAM_PASSWORD);
        if (!isValidPass(password)) {
            data.put(PARAM_PASSWORD, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        String repeatedPassword = data.get(PARAM_RE_PASSWORD);
        if (!isValidRepeatedPass(password, repeatedPassword)) {
            data.put(PARAM_RE_PASSWORD, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        return result;
    }

    public static boolean isValidUser(Map<String, String> data) {
        boolean result = true;
        String name = data.get(PARAM_NAME);
        if (!isValidName(name)) {
            data.put(PARAM_NAME, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        String surname = data.get(PARAM_SURNAME);
        if (!isValidSurname(surname)) {
            data.put(PARAM_SURNAME, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        String mail = data.get(PARAM_MAIL);
        if (!isValidMail(mail)) {
            data.put(PARAM_MAIL, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        String phone = data.get(PARAM_PHONE);
        if (!isValidPhone(phone)) {
            data.put(PARAM_PHONE, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        return result;
    }
}
