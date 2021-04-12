package com.klasevich.cms.model.factory.impl;

import com.klasevich.cms.model.entity.Category;
import com.klasevich.cms.model.entity.ComputerService;
import com.klasevich.cms.model.factory.AbstractFactory;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Map;

import static com.klasevich.cms.command.command_parameter.RequestParameter.*;

public class ComputerServiceFactory extends AbstractFactory<ComputerService> {
    @Override
    public ComputerService create(Map<String, String> data) {
        String serviceName = data.get(SERVICE_NAME);
        String categoryString = data.get(CATEGORY_NAME);
        Category category = Category.valueOf(categoryString.toUpperCase(Locale.ROOT));
        String priceString = data.get(SERVICE_PRICE);
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceString));
        ComputerService service = new ComputerService(serviceName, price, category);
        return service;
    }
}
