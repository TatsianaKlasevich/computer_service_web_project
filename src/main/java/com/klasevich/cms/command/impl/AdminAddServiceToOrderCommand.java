package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.CommandServiceImpl;
import com.klasevich.cms.model.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.CommandResult.Type.REDIRECT;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.RequestParameter.*;
import static com.klasevich.cms.command.command_parameter.UrlPattern.TO_FIND_ORDER_BY_ID_COMMAND;

/**
 * @author Tatsiana Klasevich
 * This command allows admin to add a service to order command .
 */
public class AdminAddServiceToOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private OrderServiceImpl orderService;
    private CommandServiceImpl commandService;

    /**
     * Instantiates a new Admin add service to order command.
     *
     * @param orderService   the order service
     * @param commandService the command service
     */
    public AdminAddServiceToOrderCommand(OrderServiceImpl orderService, CommandServiceImpl commandService) {
        this.orderService = orderService;
        this.commandService = commandService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
        CommandResult commandResult = new CommandResult(TO_FIND_ORDER_BY_ID_COMMAND + orderId, REDIRECT);
        String serviceId1 = request.getParameter(SERVICE_ID1);
        String serviceId2 = request.getParameter(SERVICE_ID2);
        String serviceId3 = request.getParameter(SERVICE_ID3);
        List<String> services = new ArrayList<>();
        if (serviceId1 != null && !serviceId1.isBlank()) {
            services.add(serviceId1);
        }
        if (serviceId2 != null && !serviceId2.isBlank()) {
            services.add(serviceId2);
        }
        if (serviceId3 != null && !serviceId3.isBlank()) {
            services.add(serviceId3);
        }
        logger.debug("services in command {}", services);
        try {
            commandService.addServiceToOrder(orderId, services);
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
