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
import java.util.List;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.OtherParameter.LIMIT;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.*;
import static com.klasevich.cms.command.command_parameter.RequestParameter.PAGE_NUMBER;

public class FindUsersByParameterCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private UserServiceImpl service;

    public FindUsersByParameterCommand(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(SHOW_USERS, FORWARD);
        int pageNumber = request.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(request.getParameter(PAGE_NUMBER)) : 1;
        logger.debug("page from request is {}", pageNumber);
        String parameter = request.getParameter(PARAMETER);
        parameter = XssProtector.protect(parameter);
        try {
            List<User> users = service.findUsersByParameter(parameter, pageNumber, LIMIT);
            int sizeOfAllUsersByParameter = service.sizeUsersByParameter(parameter);
            int lastPage = (int) Math.ceil((double) sizeOfAllUsersByParameter / LIMIT);
            logger.debug("size {}", sizeOfAllUsersByParameter);
            logger.debug("last page {}", lastPage);
            if (users.size() > 0) {
                request.setAttribute(PAGE_NUMBER, pageNumber);
                logger.debug("is last page {}", lastPage == pageNumber);
                request.setAttribute(IS_LAST_PAGE, lastPage == pageNumber);
                request.setAttribute(USERS, users);
                request.setAttribute(PARAMETER, parameter);
            } else {
                pageNumber = pageNumber - 1;
                if (pageNumber >= 1) {
                    List<User> usersNew = service.findUsersByParameter(parameter, pageNumber, LIMIT);
                    int sizeOfAllUsersByParameterNew = service.sizeUsersByParameter(parameter);
                    int lastPageNew = (int) Math.ceil((double) sizeOfAllUsersByParameterNew / LIMIT);
                    request.setAttribute(PAGE_NUMBER, pageNumber);
                    request.setAttribute(IS_LAST_PAGE, lastPageNew == pageNumber);
                    request.setAttribute(USERS, usersNew);
                    request.setAttribute(PARAMETER, parameter);
                } else {
                    request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.find.user.error"));
                    commandResult = new CommandResult(ADMIN_MANAGE_USER, FORWARD);
                }
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
