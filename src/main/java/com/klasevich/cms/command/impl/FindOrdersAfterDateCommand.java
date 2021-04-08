package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.entity.Order;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.OrderServiceImpl;
import com.klasevich.cms.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeParseException;
import java.util.List;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.PagePath.*;
import static com.klasevich.cms.command.RequestAttribute.*;
import static com.klasevich.cms.command.RequestParameter.DATE;
import static com.klasevich.cms.command.RequestParameter.PAGE_NUMBER;

public class FindOrdersAfterDateCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int LIMIT = 4;

    private OrderServiceImpl service;

    public FindOrdersAfterDateCommand(OrderServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(SHOW_ORDERS_AFTER_DATE, FORWARD);
        try {
            String date = request.getParameter(DATE);
            int pageNumber = request.getParameter(PAGE_NUMBER) != null ?
                    Integer.parseInt(request.getParameter(PAGE_NUMBER)) : 1;
            logger.debug("page from request is {}", pageNumber);

            List<Order> orders = service.findOrdersAfterDateOnPageNumber(date, pageNumber, LIMIT);
            int sizeOfAllOrdersAfterDate = service.sizeOrdersAfterDate(date);
            int lastPage = (int) Math.ceil((double) sizeOfAllOrdersAfterDate / LIMIT);
            logger.debug("size {}", sizeOfAllOrdersAfterDate);
            logger.debug("last page {}", lastPage);
            if (orders.size() > 0) {
                request.setAttribute(PAGE_NUMBER, pageNumber);
                logger.debug("is last page {}", lastPage == pageNumber);
                request.setAttribute(IS_LAST_PAGE, lastPage == pageNumber);
                request.setAttribute(ORDERS, orders);
                request.setAttribute(DATE, date);
            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.find.order.error"));
                commandResult = new CommandResult(ADMIN_MANAGE_ORDER, FORWARD);
            }
        } catch (DateTimeParseException e) {
            logger.error(e);
            request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("edit.correct.data"));
            commandResult = new CommandResult(ADMIN_MANAGE_ORDER, FORWARD);
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
