package com.klasevich.cms.util.validator;

import com.klasevich.cms.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static com.klasevich.cms.command.RequestParameter.*;
import static com.klasevich.cms.util.validator.GeneralValidator.*;

public class ServiceValidator {

    public static boolean isValidService(Map<String,String> data){
        boolean result= true;
        String category = data.get(CATEGORY_NAME);
        if(!isValidCategoryName(category)){
            data.put(CATEGORY_NAME, MessageManager.getProperty("message.registration.error"));
            result=false;
        }
        String service = data.get(SERVICE_NAME);
        if(!isValidServiceName(service)){
            data.put(SERVICE_NAME, MessageManager.getProperty("message.registration.error"));
            result=false;
        }
        String price = data.get(SERVICE_PRICE);
        if(!isValidPrice(price)){
            data.put(SERVICE_PRICE, MessageManager.getProperty("message.registration.error"));
            result=false;
        }
        return result;
    }


}
