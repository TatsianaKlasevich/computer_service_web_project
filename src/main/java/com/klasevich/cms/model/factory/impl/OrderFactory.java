package com.klasevich.cms.model.factory.impl;

import com.klasevich.cms.model.dao.UserDao;
import com.klasevich.cms.model.dao.impl.UserDaoImpl;
import com.klasevich.cms.model.entity.*;
import com.klasevich.cms.model.factory.AbstractFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;

import static com.klasevich.cms.command.RequestParameter.*;

public class OrderFactory extends AbstractFactory<Order> {
    private UserDao userDao = new UserDaoImpl();

    public Order create(Map<String, String> data) {
        Status status = Status.CHECKING;
        String address = data.get(ADDRESS);
        String problem = data.get(PROBLEM);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.d");
        LocalDate applianceDate = LocalDate.parse(data.get(DATE), formatter);
        String categoryString = data.get(CATEGORY_NAME);
        Category category = Category.valueOf(categoryString.toUpperCase(Locale.ROOT));
        Order order = new Order(applianceDate, address, category, problem, status);
        return order;
    }
}
