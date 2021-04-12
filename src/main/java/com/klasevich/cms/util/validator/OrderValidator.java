package com.klasevich.cms.util.validator;

import com.klasevich.cms.util.MessageManager;

import java.util.Map;

import static com.klasevich.cms.command.command_parameter.RequestParameter.*;
import static com.klasevich.cms.util.validator.GeneralValidator.*;

public class OrderValidator {
    public static boolean isValidOrder(Map<String, String> data) {
        boolean result = true;
        String date = data.get(DATE);
        if (!isValidDate(date)) {
            data.put(DATE, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        String address = data.get(ADDRESS);
        if (!isValidAddress(address)) {
            data.put(ADDRESS, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        String category = data.get(CATEGORY_NAME);
        if (!isValidCategoryName(category)) {
            data.put(CATEGORY_NAME, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        String problem = data.get(PROBLEM);
        if (!isValidProblem(problem)) {
            data.put(PROBLEM, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        return result;
    }
}



