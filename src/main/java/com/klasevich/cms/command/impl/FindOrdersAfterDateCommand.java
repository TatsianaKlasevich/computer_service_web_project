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

import static com.klasevich.cms.command.command_parameter.OtherParameter.LIMIT;
import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.*;
import static com.klasevich.cms.command.command_parameter.RequestParameter.DATE;
import static com.klasevich.cms.command.command_parameter.RequestParameter.PAGE_NUMBER;

/**
 * @author Tatsiana Klasevich
 * This command allows admin to find orders after some issue date.
 */
public class FindOrdersAfterDateCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private OrderServiceImpl service;

    /**
     * Instantiates a new Find orders after date command.
     *
     * @param service the order service
     */
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
                pageNumber = pageNumber - 1;
                if (pageNumber >= 1) {
                    List<Order> ordersNew = service.findOrdersAfterDateOnPageNumber(date, pageNumber, LIMIT);
                    int sizeOfAllOrdersAfterDateNew = service.sizeOrdersAfterDate(date);
                    int lastPageNew = (int) Math.ceil((double) sizeOfAllOrdersAfterDateNew / LIMIT);
                    request.setAttribute(PAGE_NUMBER, pageNumber);
                    request.setAttribute(IS_LAST_PAGE, lastPageNew == pageNumber);
                    request.setAttribute(ORDERS, ordersNew);
                    request.setAttribute(DATE, date);
                } else {
                    request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.find.order.error"));
                    commandResult = new CommandResult(ADMIN_MANAGE_ORDER, FORWARD);
                }
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
