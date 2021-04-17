package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.command_parameter.SessionAttribute;
import com.klasevich.cms.model.entity.Role;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.UserServiceImpl;
import com.klasevich.cms.util.MessageManager;
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
import static com.klasevich.cms.command.command_parameter.RequestAttribute.*;
import static com.klasevich.cms.command.command_parameter.RequestParameter.*;
import static com.klasevich.cms.command.command_parameter.UrlPattern.*;

/**
 * @author Tatsiana Klasevich
 * This command allows guest to register himself.
 */
public class RegisterCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private UserServiceImpl service;

    /**
     * Instantiates a new Register command.
     *
     * @param service the user service
     */
    public RegisterCommand(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(REGISTRATION, FORWARD);
        HttpSession session = request.getSession();
        Role currentRole = (Role) session.getAttribute(SessionAttribute.CURRENT_ROLE);
        logger.debug("current role {}", currentRole);
        String name = request.getParameter(PARAM_NAME);
        String surname = request.getParameter(PARAM_SURNAME);
        String mail = request.getParameter(PARAM_MAIL);
        String phone = request.getParameter(PARAM_PHONE);
        String password = request.getParameter(PARAM_PASSWORD);
        String repeatedPassword = request.getParameter(PARAM_RE_PASSWORD);
        logger.debug("parameters in register command {} {} {} {} {}", name, surname, mail, phone, password);

        name = XssProtector.protect(name);
        surname = XssProtector.protect(surname);
        mail = XssProtector.protect(mail);
        phone = XssProtector.protect(phone);
        password = XssProtector.protect(password);
        repeatedPassword = XssProtector.protect(repeatedPassword);

        Map<String, String> data = new HashMap<>();
        data.put(PARAM_NAME, name);
        data.put(PARAM_SURNAME, surname);
        data.put(PARAM_MAIL, mail);
        data.put(PARAM_PHONE, phone);
        data.put(PARAM_PASSWORD, password);
        data.put(PARAM_RE_PASSWORD, repeatedPassword);
        try {
            if (service.userExists(mail)) {
                logger.info("user with mail {} has existed yet", mail);
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.login.exist"));
            } else {
                if (service.register(data, currentRole)) {
                    if (currentRole == Role.GUEST) {
                        String welcomePage = MessageManager.getProperty("message.welcome.page");
                        logger.debug("adding in bd {}, {}, {}, {}", name, surname, mail, phone);
                        commandResult = new CommandResult(TO_LOGIN_COMMAND + name + PARAMETER_SURNAME +
                                surname + PARAMETER_WELCOME_PAGE + welcomePage, REDIRECT);
                    } else {
                        request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("admin.register.successfully"));
                        commandResult = new CommandResult(ADMIN_MAIN, FORWARD);
                    }
                } else {
                    request.setAttribute(PARAM_NAME, data.get(PARAM_NAME));
                    logger.debug("return name {}", data.get(PARAM_NAME));
                    request.setAttribute(PARAM_SURNAME, data.get(PARAM_SURNAME));
                    logger.debug("return surname {}", data.get(PARAM_SURNAME));
                    request.setAttribute(PARAM_MAIL, data.get(PARAM_MAIL));
                    logger.debug("return mail {}", data.get(PARAM_MAIL));
                    request.setAttribute(PARAM_PHONE, data.get(PARAM_PHONE));
                    logger.debug("return phone {}", data.get(PARAM_PHONE));
                    request.setAttribute(PARAM_PASSWORD, data.get(PARAM_PASSWORD));
                    logger.debug("return password {}", data.get(PARAM_PASSWORD));
                    request.setAttribute(PARAM_RE_PASSWORD, data.get(PARAM_RE_PASSWORD));
                    logger.debug("return Repassword {}", data.get(PARAM_RE_PASSWORD));
                    request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.registration.error"));
                }
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
