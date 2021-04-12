package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.CommandServiceImpl;
import com.klasevich.cms.util.MessageManager;
import com.klasevich.cms.util.XssProtector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.CommandResult.Type.REDIRECT;
import static com.klasevich.cms.command.command_parameter.PagePath.ADMIN_MANAGE_SERVICE;
import static com.klasevich.cms.command.command_parameter.PagePath.ERROR_500;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.*;
import static com.klasevich.cms.command.command_parameter.RequestParameter.*;
import static com.klasevich.cms.command.command_parameter.UrlPattern.TO_ADMIN_MANAGE_SERVICE_COMMAND;


public class AddServiceCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private CommandServiceImpl service;

    public AddServiceCommand(CommandServiceImpl adminService) {
        this.service = adminService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(ADMIN_MANAGE_SERVICE, FORWARD);
        String category = request.getParameter(CATEGORY_NAME);
        String serviceName = request.getParameter(SERVICE_NAME);
        String priceString = request.getParameter(SERVICE_PRICE);
        serviceName = XssProtector.protect(serviceName);

        Map<String, String> data = new HashMap<>();
        data.put(CATEGORY_NAME, category);
        data.put(SERVICE_NAME, serviceName);
        data.put(SERVICE_PRICE, priceString);
        try {
            if (service.addService(data)) {
                String messageWarning = MESSAGE_SERVICE_ADD;
                commandResult = new CommandResult(TO_ADMIN_MANAGE_SERVICE_COMMAND + messageWarning, REDIRECT);
            } else {
                logger.debug("return  category name {}", data.get(CATEGORY_NAME));
                request.setAttribute(CATEGORY_NAME, data.get(CATEGORY_NAME));
                logger.debug("return service name {}", data.get(SERVICE_NAME));
                request.setAttribute(SERVICE_NAME, data.get(SERVICE_NAME));
                logger.debug("return price {}", data.get(SERVICE_PRICE));
                request.setAttribute(SERVICE_PRICE, data.get(SERVICE_PRICE));
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.service.add.error"));
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}

