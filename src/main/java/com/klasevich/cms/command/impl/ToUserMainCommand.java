package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.PagePath.USER_MAIN;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.MESSAGE_WARNING;
import static com.klasevich.cms.command.command_parameter.RequestParameter.ORDER_ID;

/**
 * @author Tatsiana Klasevich
 * This command allows going to user's main page.
 */
public class ToUserMainCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter(ORDER_ID);
        String messageWarning = request.getParameter(MESSAGE_WARNING);
        request.setAttribute(ORDER_ID, orderId);
        request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty(messageWarning));
        logger.debug("message warning {}", messageWarning);
        CommandResult commandResult = new CommandResult(USER_MAIN, FORWARD);
        return commandResult;
    }
}
