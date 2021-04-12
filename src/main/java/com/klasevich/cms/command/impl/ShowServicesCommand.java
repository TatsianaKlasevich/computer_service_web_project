package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.entity.ComputerService;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.CommandServiceImpl;
import com.klasevich.cms.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.OtherParameter.LIMIT;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.*;
import static com.klasevich.cms.command.command_parameter.RequestParameter.*;

public class ShowServicesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private CommandServiceImpl service;

    public ShowServicesCommand(CommandServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(SHOW_SERVICES, FORWARD);
        int pageNumber = request.getParameter(PAGE_NUMBER) != null ?
                Integer.parseInt(request.getParameter(PAGE_NUMBER)) : 1;
        logger.debug("page from request is {}", pageNumber);
        try {
            List<ComputerService> services = service.findServicesByPageNumber(pageNumber, LIMIT);
            int sizeOfAllServices = service.sizeServices();
            int lastPage = (int) Math.ceil((double) sizeOfAllServices / LIMIT);
            logger.debug("size {}", sizeOfAllServices);
            logger.debug("last page {}", lastPage);
            if (services.size() > 0) {
                request.setAttribute(PAGE_NUMBER, pageNumber);
                logger.debug("is last page {}", lastPage == pageNumber);
                request.setAttribute(IS_LAST_PAGE, lastPage == pageNumber);
                request.setAttribute(COMPUTER_SERVICES, services);
            } else {
                pageNumber = pageNumber - 1;
                if (pageNumber >= 1) {
                    List<ComputerService> servicesNew = service.findServicesByPageNumber(pageNumber, LIMIT);
                    int sizeOfAllServicesNew = service.sizeServices();
                    int lastPageNew = (int) Math.ceil((double) sizeOfAllServicesNew / LIMIT);
                    request.setAttribute(PAGE_NUMBER, pageNumber);
                    request.setAttribute(IS_LAST_PAGE, lastPageNew == pageNumber);
                    request.setAttribute(COMPUTER_SERVICES, servicesNew);
                } else {
                    request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.find.service.error"));
                    commandResult = new CommandResult(ADMIN_MANAGE_SERVICE, FORWARD);
                }
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
