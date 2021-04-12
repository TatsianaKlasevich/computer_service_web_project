package com.klasevich.cms.model.factory.impl;

import com.klasevich.cms.model.entity.User;
import com.klasevich.cms.model.factory.AbstractFactory;

import java.util.Map;

import static com.klasevich.cms.command.command_parameter.RequestParameter.*;

public class UserFactory extends AbstractFactory<User> {
    @Override
    public User create(Map<String, String> data) {
        String name = data.get(PARAM_NAME);
        String surname = data.get(PARAM_SURNAME);
        String mail = data.get(PARAM_MAIL);
        String phone = data.get(PARAM_PHONE);
        User user = new User(name, surname, mail, phone);
        return user;
    }
}
