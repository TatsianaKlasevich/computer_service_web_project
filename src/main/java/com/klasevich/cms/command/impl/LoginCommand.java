package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.command_parameter.SessionAttribute;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.entity.Role;
import com.klasevich.cms.model.entity.User;
import com.klasevich.cms.util.MessageManager;
import com.klasevich.cms.model.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Optional;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.MESSAGE_WARNING;
import static com.klasevich.cms.command.command_parameter.RequestParameter.PARAM_MAIL;
import static com.klasevich.cms.command.command_parameter.RequestParameter.PARAM_PASSWORD;
import static com.klasevich.cms.command.command_parameter.SessionAttribute.CURRENT_ROLE;

/**
 * @author Tatsiana Klasevich
 * This command if for autentification.
 */
public class LoginCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private UserServiceImpl service;

    /**
     * Instantiates a new Login command.
     *
     * @param service the user service
     */
    public LoginCommand(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(LOGIN, FORWARD);
        String mail = request.getParameter(PARAM_MAIL);
        logger.debug("mail {}", mail);
        String pass = request.getParameter(PARAM_PASSWORD);
        logger.debug("pass {}", pass);
        HttpSession session = request.getSession();
        try {
            Optional<User> optionalUser = service.findUserByMailPassword(mail, pass);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                session.setAttribute(SessionAttribute.USER, user);
                session.setAttribute(CURRENT_ROLE, user.getRole());
                logger.debug("user.getRole {}", user.getRole());
                if (user.getRole() == Role.ADMIN) {
                    commandResult = new CommandResult(ADMIN_MAIN, FORWARD);
                } else {
                    commandResult = new CommandResult(PROFILE, FORWARD);
                }
            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.login.error"));
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
