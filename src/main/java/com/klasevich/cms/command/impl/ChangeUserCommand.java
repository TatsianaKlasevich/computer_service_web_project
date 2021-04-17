package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.command_parameter.SessionAttribute;
import com.klasevich.cms.model.entity.User;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.UserServiceImpl;
import com.klasevich.cms.util.XssProtector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.CommandResult.Type.REDIRECT;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.RequestParameter.*;

/**
 * @author Tatsiana Klasevich
 *This command allows admin and user change their profile's data.
 */
public class ChangeUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserServiceImpl service;

    /**
     * Instantiates a new Change user command.
     *
     * @param service the user service
     */
    public ChangeUserCommand(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(CHANGE_USER, FORWARD);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        logger.debug("user in ChangeUserCommand {}", user);
        String name = request.getParameter(PARAM_NAME);
        logger.debug("name in command {}", name);
        String surname = request.getParameter(PARAM_SURNAME);
        String phone = request.getParameter(PARAM_PHONE);
        name = XssProtector.protect(name);
        surname = XssProtector.protect(surname);
        Map<String, String> data = new HashMap<>();
        data.put(PARAM_NAME, name);
        data.put(PARAM_SURNAME, surname);
        data.put(PARAM_PHONE, phone);
        data.put(PARAM_MAIL, user.getMail());
        try {
            if (service.changeUser(data)) {
                user.setName(data.get(PARAM_NAME));
                user.setSurname(data.get(PARAM_SURNAME));
                user.setPhone(data.get(PARAM_PHONE));
                commandResult = new CommandResult(PROFILE, REDIRECT);
            } else {
                request.setAttribute(PARAM_NAME, data.get(PARAM_NAME));
                logger.debug("return name {}", data.get(PARAM_NAME));
                request.setAttribute(PARAM_MAIL, data.get(PARAM_MAIL));
                logger.debug("return name {}", data.get(PARAM_NAME));
                request.setAttribute(PARAM_SURNAME, data.get(PARAM_SURNAME));
                logger.debug("return surname {}", data.get(PARAM_SURNAME));
                request.setAttribute(PARAM_PHONE, data.get(PARAM_PHONE));
                logger.debug("return phone {}", data.get(PARAM_PHONE));
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
