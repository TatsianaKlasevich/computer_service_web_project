package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.PagePath;
import com.klasevich.cms.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.PagePath.CHANGE_USER;

import static com.klasevich.cms.command.SessionAttribute.USER;

public class ToChangeUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {//todo проверит работает ли в все юзеры
        CommandResult commandResult = new CommandResult(CHANGE_USER, FORWARD);
        String page = PagePath.CHANGE_USER;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        request.setAttribute(USER,user);
//                request.setAttribute(PARAM_NAME, user.getName());
//                request.setAttribute(PARAM_SURNAME, user.getSurname());
//                request.setAttribute(PARAM_PHONE, user.getPhone());
//                request.setAttribute(PARAM_MAIL, user.getMail());
        return commandResult;
    }
}
