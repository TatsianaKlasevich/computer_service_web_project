package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.command_parameter.SessionAttribute;
import com.klasevich.cms.model.entity.Order;
import com.klasevich.cms.model.entity.User;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.OrderServiceImpl;
import com.klasevich.cms.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.*;

/**
 * @author Tatsiana Klasevich
 * This command allows user to see all his orders.
 */
public class UserOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private OrderServiceImpl service;

    /**
     * Instantiates a new User order command.
     *
     * @param service the order service
     */
    public UserOrderCommand(OrderServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(USER_ORDER, FORWARD);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        int id = user.getId();
        try {
            List<Order> orders = service.findAllOrdersByUserId(id);
            if (orders.size() > 0) {
                request.setAttribute(ORDERS, orders);
            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.order.not.found"));
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
