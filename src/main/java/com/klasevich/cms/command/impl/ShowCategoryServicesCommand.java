package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.entity.ComputerService;
import com.klasevich.cms.model.service.impl.CommandServiceImpl;
import com.klasevich.cms.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.PagePath.SERVICE_PRICE;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.*;
import static com.klasevich.cms.command.command_parameter.RequestParameter.*;

/**
 * @author Tatsiana Klasevich
 * This command allows guests, users, admins all the services by a category.
 */
public class ShowCategoryServicesCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private CommandServiceImpl service;

    /**
     * Instantiates a new Show category services command.
     *
     * @param service the command service
     */
    public ShowCategoryServicesCommand(CommandServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(SERVICE_PRICE, FORWARD);
        String category = request.getParameter(CATEGORY_NAME);
        try {
            List<ComputerService> services = service.findAllServicesByCategory(category);
            logger.debug("services in command {}", services);
            String categoryMessage;
            if (services.size() > 0) {
                switch (category) {
                    case LAPTOP:
                        categoryMessage = "message.repair.laptop";
                        break;
                    case PRINTER:
                        categoryMessage = "message.repair.printer";
                        break;
                    case UPS:
                        categoryMessage = "message.repair.ups";
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + category);
                }
                request.setAttribute(CATEGORY_NAME, MessageManager.getProperty(categoryMessage));
                request.setAttribute(COMPUTER_SERVICES, services);
            } else {
                request.setAttribute(CATEGORY_NAME, MessageManager.getProperty("message.category.not.found"));
            }
        } catch (ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
