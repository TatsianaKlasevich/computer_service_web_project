package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.command_parameter.UrlPattern;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.UserServiceImpl;
import com.klasevich.cms.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.CommandResult.Type.REDIRECT;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.MESSAGE_WARNING;
import static com.klasevich.cms.command.command_parameter.RequestParameter.PAGE_NUMBER;
import static com.klasevich.cms.command.command_parameter.RequestParameter.USER_ID;

/**
 * @author Tatsiana Klasevich
 * This command allows admin to remove a user.
 */
public class RemoveUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserServiceImpl service;

    /**
     * Instantiates a new Remove user command.
     *
     * @param service the user service
     */
    public RemoveUserCommand(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(ADMIN_MANAGE_USER, FORWARD);
        int userId = Integer.parseInt(request.getParameter(USER_ID));
        logger.debug("userId {}", userId);
        int pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER));
        logger.debug("page number {}", pageNumber);
        try {
            if (service.removeUser(userId)) {
                commandResult = new CommandResult(UrlPattern.TO_SHOW_USERS_COMMAND + pageNumber, REDIRECT);
            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.user.remove.error"));
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
