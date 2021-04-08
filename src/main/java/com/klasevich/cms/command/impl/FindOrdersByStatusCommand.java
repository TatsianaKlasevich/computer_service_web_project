package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.entity.Order;
import com.klasevich.cms.model.entity.Status;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.OrderServiceImpl;
import com.klasevich.cms.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Locale;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.PagePath.*;
import static com.klasevich.cms.command.RequestAttribute.*;
import static com.klasevich.cms.command.RequestParameter.PAGE_NUMBER;

public class FindOrdersByStatusCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int LIMIT = 4;

    private OrderServiceImpl service;

    public FindOrdersByStatusCommand(OrderServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(SHOW_ORDERS_BY_STATUS, FORWARD);
        String statusString = request.getParameter(STATUS);
        Status status=Status.valueOf(statusString.toUpperCase(Locale.ROOT));
        int pageNumber = request.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(request.getParameter(PAGE_NUMBER)) : 1;
        logger.debug("page from request is {}", pageNumber);
        try {
            List<Order> orders = service.findOrdersByStatusAndPageNumber(status,pageNumber, LIMIT);
            int sizeOfAllOrdersByStatus = service.sizeOrdersByStatus(status);
            int lastPage = (int) Math.ceil((double) sizeOfAllOrdersByStatus / LIMIT);
            logger.debug("size {}", sizeOfAllOrdersByStatus);
            logger.debug("last page {}", lastPage);
            if (orders.size() > 0) {
                request.setAttribute(PAGE_NUMBER, pageNumber);
                logger.debug("is last page {}", lastPage == pageNumber);
                request.setAttribute(IS_LAST_PAGE, lastPage == pageNumber);
                request.setAttribute(ORDERS, orders);
                request.setAttribute(STATUS,statusString);
            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.find.order.error"));
                commandResult = new CommandResult(ADMIN_MANAGE_ORDER);
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult=new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
