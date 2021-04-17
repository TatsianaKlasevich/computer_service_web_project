package com.klasevich.cms.util.validator;

import com.klasevich.cms.util.MessageManager;

import java.util.Map;

import static com.klasevich.cms.command.command_parameter.RequestParameter.*;
import static com.klasevich.cms.util.validator.GeneralValidator.*;

/**
 * Class check if Service is valid.
 */
public class ServiceValidator {

    /**
     * Is valid Service.
     *
     * @param data the data
     * @return true if Service is valid
     */
    public static boolean isValidService(Map<String, String> data) {
        boolean result = true;
        String category = data.get(CATEGORY_NAME);
        if (!isValidCategoryName(category)) {
            data.put(CATEGORY_NAME, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        String service = data.get(SERVICE_NAME);
        if (!isValidServiceName(service)) {
            data.put(SERVICE_NAME, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        String price = data.get(SERVICE_PRICE);
        if (!isValidPrice(price)) {
            data.put(SERVICE_PRICE, MessageManager.getProperty("message.registration.error"));
            result = false;
        }
        return result;
    }
}
