package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.entity.ComputerService;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.CommandServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Optional;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.PagePath.*;
import static com.klasevich.cms.command.RequestParameter.*;
import static com.klasevich.cms.command.RequestParameter.SERVICE_PRICE;

public class ToChangeServiceCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private CommandServiceImpl service;

    public ToChangeServiceCommand(CommandServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult( CHANGE_SERVICE, FORWARD);
        int serviceId = Integer.parseInt(request.getParameter(SERVICE_ID));
        try {
            Optional<ComputerService> optionalService = service.findServiceById(serviceId);
            if (optionalService.isPresent()) {
                ComputerService computerService = optionalService.get();
                request.setAttribute(SERVICE_NAME, computerService.getServiceName());
                request.setAttribute(CATEGORY_NAME, computerService.getCategory());
                request.setAttribute(SERVICE_PRICE, computerService.getPrice());
                request.setAttribute(SERVICE_ID, serviceId);
            }
            else{
                commandResult=new CommandResult(ERROR_500, FORWARD);
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult=new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
