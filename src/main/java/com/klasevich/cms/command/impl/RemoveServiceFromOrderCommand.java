package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.command_parameter.UrlPattern;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.OrderServiceImpl;
import com.klasevich.cms.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.CommandResult.Type.REDIRECT;
import static com.klasevich.cms.command.command_parameter.PagePath.ADMIN_MANAGE_SERVICE;
import static com.klasevich.cms.command.command_parameter.PagePath.ERROR_500;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.MESSAGE_WARNING;
import static com.klasevich.cms.command.command_parameter.RequestParameter.ORDER_ID;
import static com.klasevich.cms.command.command_parameter.RequestParameter.SERVICE_ID;

public class RemoveServiceFromOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private OrderServiceImpl service;

    public RemoveServiceFromOrderCommand(OrderServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(ADMIN_MANAGE_SERVICE, FORWARD);
        int serviceId = Integer.parseInt(request.getParameter(SERVICE_ID));
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
        try {
            if (service.removeServiceFromOrder(serviceId, orderId)) {
                commandResult = new CommandResult(UrlPattern.TO_FIND_ORDER_BY_ID_COMMAND + orderId, REDIRECT);
            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.service.remove.error"));
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
