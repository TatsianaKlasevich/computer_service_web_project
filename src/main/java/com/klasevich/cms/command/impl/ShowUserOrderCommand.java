package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.RequestParameter;
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
import static com.klasevich.cms.command.PagePath.*;
import static com.klasevich.cms.command.RequestAttribute.*;
import static java.math.RoundingMode.HALF_UP;

public class ShowUserOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String PERCENT_WITHOUT_DISCOUNT="0%";
    private static final String PERCENT_WITH_DISCOUNT="15%";
    private static final Double BIG_DECIMAL_PERCENT=0.85;
    private static final Long BIG_DECIMAL_MIN_AMOUNT_FOR_CONFIRMING_ORDER=30L;

    private OrderServiceImpl orderService;
    private CommandServiceImpl commandService;

    public ShowUserOrderCommand(OrderServiceImpl orderService, CommandServiceImpl commandService) {
        this.orderService = orderService;
        this.commandService=commandService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(SHOW_ORDER, FORWARD);
        int id = Integer.parseInt(request.getParameter(RequestParameter.ORDER_ID));
        try {
            Optional<Order> optionalOrder= orderService.findOrderById(id);
            if (optionalOrder.isPresent()) {
                Order order = optionalOrder.get();
                String discount=order.isHasDiscount()?PERCENT_WITH_DISCOUNT:PERCENT_WITHOUT_DISCOUNT;
                logger.debug("discount {}", discount);
                List<ComputerService> services=commandService.findAllServicesByOrderId(order.getId());
                logger.debug("services {}", services);
                BigDecimal amount = BigDecimal.ZERO;
                logger.debug("amount {}", amount);
                BigDecimal percent= BigDecimal.valueOf(BIG_DECIMAL_PERCENT);
                logger.debug("percent {}", percent);
                BigDecimal min=BigDecimal.valueOf(BIG_DECIMAL_MIN_AMOUNT_FOR_CONFIRMING_ORDER);
                for (ComputerService element:services) {
                    amount=amount.add(element.getPrice());
                }
                if (order.isHasDiscount()){
                    amount=amount.multiply(percent).setScale(2,HALF_UP);
                }
                if (services.size()>0){
                    request.setAttribute(SERVICES,services);
                }
                request.setAttribute(ORDER, order);
                request.setAttribute(DISCOUNT, discount);
                request.setAttribute(AMOUNT, amount);

            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.login.error"));
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult=new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
