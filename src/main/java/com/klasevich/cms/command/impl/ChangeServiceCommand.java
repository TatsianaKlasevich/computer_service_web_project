package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.entity.Category;
import com.klasevich.cms.model.entity.ComputerService;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.CommandServiceImpl;
import com.klasevich.cms.util.MessageManager;
import com.klasevich.cms.util.XssProtector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.util.Locale;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.CommandResult.Type.REDIRECT;
import static com.klasevich.cms.command.PagePath.*;
import static com.klasevich.cms.command.RequestAttribute.MESSAGE_WARNING;
import static com.klasevich.cms.command.RequestParameter.*;
import static com.klasevich.cms.command.RequestParameter.SERVICE_NAME;
import static com.klasevich.cms.command.RequestParameter.SERVICE_PRICE;


public class ChangeServiceCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private CommandServiceImpl service;

    public ChangeServiceCommand(CommandServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(ADMIN_MANAGE_SERVICE, FORWARD);
        String serviceName = request.getParameter(SERVICE_NAME);
        serviceName= XssProtector.protect(serviceName);
        String categoryString = request.getParameter(CATEGORY_NAME);
        String priceString = request.getParameter(SERVICE_PRICE);
        int id = Integer.parseInt(request.getParameter(SERVICE_ID));
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceString));
        Category category = Category.valueOf(categoryString.toUpperCase(Locale.ROOT));
        ComputerService computerService = new ComputerService(id, serviceName, price, category);
        logger.debug("computerService in command {}", computerService);
        try {
            if (service.changeService(computerService)) {
                request.setAttribute(MESSAGE_WARNING,
                        MessageManager.getProperty("message.service.change.successfully"));
                commandResult = new CommandResult(ADMIN_MANAGE_SERVICE, REDIRECT);
            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.service.change.error"));
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult=new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
