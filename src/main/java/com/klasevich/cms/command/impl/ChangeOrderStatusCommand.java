package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.entity.Status;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.OrderServiceImpl;
import com.klasevich.cms.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.CommandResult.Type.REDIRECT;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.*;
import static com.klasevich.cms.command.command_parameter.RequestParameter.ORDER_ID;
import static com.klasevich.cms.command.command_parameter.UrlPattern.TO_SHOW_USER_ORDER_COMMAND;

public class ChangeOrderStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private OrderServiceImpl service;

    public ChangeOrderStatusCommand(OrderServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(USER_ORDER, FORWARD);
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
        logger.debug("orderId {}", orderId);
        logger.debug("string status {}", request.getParameter(STATUS));
        String statusString = request.getParameter(STATUS);
        logger.debug("status string in command {}", statusString);
        Status status = Status.valueOf(statusString.toUpperCase(Locale.ROOT));
        if (status == Status.CHECKING || status == Status.WORKING) {
            status = Status.WORKING;
        }
        try {
            if (service.changeOrderStatus(status, orderId)) {
                commandResult = new CommandResult(TO_SHOW_USER_ORDER_COMMAND + orderId, REDIRECT);
            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.status.not.changed"));
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
