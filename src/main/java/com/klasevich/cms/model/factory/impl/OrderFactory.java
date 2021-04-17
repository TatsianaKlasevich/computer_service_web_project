package com.klasevich.cms.model.factory.impl;

import com.klasevich.cms.model.dao.UserDao;
import com.klasevich.cms.model.dao.impl.UserDaoImpl;
import com.klasevich.cms.model.entity.*;
import com.klasevich.cms.model.factory.AbstractFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

import static com.klasevich.cms.command.command_parameter.RequestParameter.*;

/**
 * @author Tatsiana Klasevich
 * The type Order factory.
 */
public class OrderFactory extends AbstractFactory<Order> {
    private static final String DATE_FORMAT = "yyyy.MM.d";
    private UserDao userDao = new UserDaoImpl();

    public Order create(Map<String, String> data) {
        Status status = Status.CHECKING;
        String address = data.get(ADDRESS);
        String problem = data.get(PROBLEM);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate applianceDate = LocalDate.parse(data.get(DATE), formatter);
        String categoryString = data.get(CATEGORY_NAME);
        Category category = Category.valueOf(categoryString.toUpperCase(Locale.ROOT));
        Order order = new Order(applianceDate, address, category, problem, status);
        return order;
    }
}
