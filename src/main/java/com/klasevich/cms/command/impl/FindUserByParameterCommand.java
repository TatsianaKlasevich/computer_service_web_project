package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.entity.User;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.UserServiceImpl;
import com.klasevich.cms.util.MessageManager;
import com.klasevich.cms.util.XssProtector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.PagePath.*;
import static com.klasevich.cms.command.RequestAttribute.*;
import static com.klasevich.cms.command.SessionAttribute.USER;

public class FindUserByParameterCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private UserServiceImpl service;

    public FindUserByParameterCommand(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(SHOW_ORDERS_BY_STATUS, FORWARD);
        String parameter = request.getParameter(PARAMETER);
        parameter = XssProtector.protect(parameter);
        try {
            Optional<User>optionalUser=service.findUserByParameter(parameter);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.setAttribute(USER,user);
            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.user.found.error"));
                commandResult = new CommandResult(ADMIN_MANAGE_USER, FORWARD);
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult=new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
