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
import static com.klasevich.cms.command.command_parameter.RequestParameter.DATE;
import static com.klasevich.cms.command.command_parameter.RequestParameter.ORDER_ID;
import static com.klasevich.cms.command.command_parameter.UrlPattern.TO_FIND_ORDER_BY_ID_COMMAND;

/**
 * @author Tatsiana Klasevich
 * This command allows admin to change an order.
 */
public class ChangeAdminOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private OrderServiceImpl service;

    /**
     * Instantiates a new Change admin order command.
     *
     * @param service the order service
     */
    public ChangeAdminOrderCommand(OrderServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(ADMIN_MANAGE_ORDER, FORWARD);
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
        logger.debug("orderId {}", orderId);
        String statusString = request.getParameter(STATUS);
        Status status = Status.valueOf(statusString.toUpperCase(Locale.ROOT));
        String date = request.getParameter(DATE);
        logger.debug("date in command {}", date);

        try {
            if (service.changeOrderStatusAndIssueDate(status, date, orderId)) {
                commandResult = new CommandResult(TO_FIND_ORDER_BY_ID_COMMAND + orderId, REDIRECT);
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
