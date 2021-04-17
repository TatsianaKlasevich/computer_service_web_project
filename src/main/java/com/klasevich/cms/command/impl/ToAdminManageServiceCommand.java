package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.PagePath.ADMIN_MANAGE_SERVICE;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.MESSAGE_WARNING;

/**
 * @author Tatsiana Klasevich
 * This command allows going to admin's manage service page.
 */
public class ToAdminManageServiceCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String messageWarning = request.getParameter(MESSAGE_WARNING);
        logger.debug("message warning {}", messageWarning);
        if (messageWarning != null) {
            request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty(messageWarning));
        }
        CommandResult commandResult = new CommandResult(ADMIN_MANAGE_SERVICE, FORWARD);
        return commandResult;
    }
}

