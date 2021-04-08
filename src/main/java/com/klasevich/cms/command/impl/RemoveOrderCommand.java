package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
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
import static com.klasevich.cms.command.PagePath.*;
import static com.klasevich.cms.command.RequestAttribute.*;
import static com.klasevich.cms.command.RequestParameter.ORDER_ID;
import static com.klasevich.cms.command.RequestParameter.PAGE_NUMBER;
import static com.klasevich.cms.command.UrlPattern.TO_SHOW_ORDERS_COMMAND;

public class RemoveOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private OrderServiceImpl service;

    public RemoveOrderCommand(OrderServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(ADMIN_MANAGE_ORDER, FORWARD);
        int pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER));
        logger.debug("page number {}", pageNumber);
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
        logger.debug("order id {}", orderId);
        String status = request.getParameter(STATUS).toLowerCase(Locale.ROOT);
        if (!status.equals("checking")) {
            request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.remove.error"));
            commandResult = new CommandResult(TO_SHOW_ORDERS_COMMAND + pageNumber, REDIRECT);
        } else {
            try {
                if (service.removeOrder(orderId)) {
                    commandResult = new CommandResult(TO_SHOW_ORDERS_COMMAND + pageNumber, REDIRECT);
                } else {
                    request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.order.remove.error"));
                }
            } catch (ServiceException e) {
                logger.error(e);
                commandResult=new CommandResult(ERROR_500, FORWARD);
            }
        }
        return commandResult;
    }
}
