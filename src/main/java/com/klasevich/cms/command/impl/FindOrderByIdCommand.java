package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.entity.ComputerService;
import com.klasevich.cms.model.entity.Order;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.CommandServiceImpl;
import com.klasevich.cms.model.service.impl.OrderServiceImpl;
import com.klasevich.cms.util.MessageManager;
import com.klasevich.cms.util.XssProtector;
import com.klasevich.cms.util.validator.GeneralValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.OtherParameter.*;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.*;
import static com.klasevich.cms.command.command_parameter.RequestParameter.*;
import static java.math.RoundingMode.HALF_UP;

public class FindOrderByIdCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private OrderServiceImpl orderService;
    private CommandServiceImpl commandService;

    public FindOrderByIdCommand(OrderServiceImpl orderService, CommandServiceImpl commandService) {
        this.orderService = orderService;
        this.commandService = commandService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(SHOW_ADMIN_ORDER, FORWARD);
        String orderId;
        try {
            orderId = request.getParameter(ORDER_ID);
            orderId = XssProtector.protect(orderId);
            Optional<Order> optionalOrder = Optional.empty();
            if (GeneralValidator.isValidId(orderId)) {
                int id = Integer.parseInt(orderId);
                optionalOrder = orderService.findOrderById(id);
            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.find.order.error"));
            }
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                String categoryName = order.getCategory().toString();
                logger.debug("categoryName in command {}", categoryName);
                List<ComputerService> servicesByCategory = commandService.findAllServicesByCategory(categoryName);
                List<ComputerService> services = commandService.findAllServicesByOrderId(order.getId());
                String discount = order.isHasDiscount() ? PERCENT_WITH_DISCOUNT : PERCENT_WITHOUT_DISCOUNT;
                BigDecimal amount = BigDecimal.ZERO;
                BigDecimal percent = BigDecimal.valueOf(BIG_DECIMAL_PERCENT);
                for (ComputerService element : services) {
                    amount = amount.add(element.getPrice());
                }
                if (order.isHasDiscount()) {
                    amount = amount.multiply(percent).setScale(2, HALF_UP);
                }
                if (services.size() > 0) {
                    request.setAttribute(SERVICES, services);
                }
                request.setAttribute(ORDER, order);
                request.setAttribute(DISCOUNT, discount);
                request.setAttribute(SERVICES_BY_CATEGORY, servicesByCategory);
                request.setAttribute(AMOUNT, amount);
            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.find.order.error"));
                commandResult = new CommandResult(ADMIN_MANAGE_ORDER, FORWARD);
            }
        } catch (NumberFormatException e) {
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
