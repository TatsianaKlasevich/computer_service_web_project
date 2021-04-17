package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.command_parameter.RequestParameter;
import com.klasevich.cms.model.entity.*;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.CommandServiceImpl;
import com.klasevich.cms.model.service.impl.OrderServiceImpl;
import com.klasevich.cms.util.MessageManager;
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
import static java.math.RoundingMode.HALF_UP;

/**
 * @author Tatsiana Klasevich
 * This command allows user to see his order.
 */
public class ShowUserOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private OrderServiceImpl orderService;
    private CommandServiceImpl commandService;

    /**
     * Instantiates a new Show user order command.
     *
     * @param orderService   the order service
     * @param commandService the command service
     */
    public ShowUserOrderCommand(OrderServiceImpl orderService, CommandServiceImpl commandService) {
        this.orderService = orderService;
        this.commandService = commandService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(SHOW_ORDER, FORWARD);
        int id = Integer.parseInt(request.getParameter(RequestParameter.ORDER_ID));
        try {
            Optional<Order> optionalOrder = orderService.findOrderById(id);
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                String discount = order.isHasDiscount() ? PERCENT_WITH_DISCOUNT : PERCENT_WITHOUT_DISCOUNT;
                logger.debug("discount {}", discount);
                List<ComputerService> services = commandService.findAllServicesByOrderId(order.getId());
                logger.debug("services {}", services);
                BigDecimal amount = BigDecimal.ZERO;
                logger.debug("amount {}", amount);
                BigDecimal percent = BigDecimal.valueOf(BIG_DECIMAL_PERCENT);
                logger.debug("percent {}", percent);
                BigDecimal min = BigDecimal.valueOf(BIG_DECIMAL_MIN_AMOUNT_FOR_CONFIRMING_ORDER);
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
                request.setAttribute(AMOUNT, amount);

            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.login.error"));
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
