package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.command_parameter.SessionAttribute;
import com.klasevich.cms.model.entity.User;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.UserServiceImpl;
import com.klasevich.cms.util.MessageManager;
import com.klasevich.cms.util.XssProtector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.CommandResult.Type.REDIRECT;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.MESSAGE_WARNING;
import static com.klasevich.cms.command.command_parameter.RequestParameter.*;
import static com.klasevich.cms.command.command_parameter.UrlPattern.TO_PROFILE_COMMAND;

/**
 * @author Tatsiana Klasevich
 * This command allows user and admin to change password in a profile.
 */
public class ChangePasswordCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserServiceImpl service;

    /**
     * Instantiates a new Change password command.
     *
     * @param service the user service
     */
    public ChangePasswordCommand(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(CHANGE_PASSWORD, FORWARD);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        String mail = user.getMail();
        String password = request.getParameter(PARAM_PASSWORD);
        String passwordNew = request.getParameter(PARAM_PASSWORD_NEW);
        String repeatedPassword = request.getParameter(PARAM_RE_PASSWORD);
        password = XssProtector.protect(password);
        passwordNew = XssProtector.protect(passwordNew);
        repeatedPassword = XssProtector.protect(repeatedPassword);
        try {
            if (service.changePassword(mail, password, passwordNew, repeatedPassword)) {
                String messageWarning = MESSAGE_CHANGE_PASSWORD;
                commandResult = new CommandResult(TO_PROFILE_COMMAND + messageWarning, REDIRECT);
            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.change.password.error"));
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
