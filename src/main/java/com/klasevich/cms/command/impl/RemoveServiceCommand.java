package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.CommandServiceImpl;
import com.klasevich.cms.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.CommandResult.Type.REDIRECT;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.MESSAGE_WARNING;
import static com.klasevich.cms.command.command_parameter.RequestParameter.PAGE_NUMBER;
import static com.klasevich.cms.command.command_parameter.RequestParameter.SERVICE_ID;
import static com.klasevich.cms.command.command_parameter.UrlPattern.TO_SHOW_SERVICES_COMMAND;

/**
 * @author Tatsiana Klasevich
 * This command allows admin to remove a service.
 */
public class RemoveServiceCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private CommandServiceImpl service;

    /**
     * Instantiates a new Remove service command.
     *
     * @param service the command service
     */
    public RemoveServiceCommand(CommandServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(ADMIN_MANAGE_SERVICE, FORWARD);
        int pageNumber = Integer.parseInt(request.getParameter(PAGE_NUMBER));
        int serviceId = Integer.parseInt(request.getParameter(SERVICE_ID));
        try {
            if (service.removeService(serviceId)) {
                commandResult = new CommandResult(TO_SHOW_SERVICES_COMMAND + pageNumber, REDIRECT);
            } else {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.service.remove.error"));
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
