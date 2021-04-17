package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.service.impl.CommandServiceImpl;
import com.klasevich.cms.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.OtherParameter.*;
import static com.klasevich.cms.command.command_parameter.OtherParameter.SUBJECT;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.*;
import static com.klasevich.cms.command.command_parameter.RequestParameter.*;
import static com.klasevich.cms.command.command_parameter.UrlPattern.TO_SHOW_ADMIN_ORDER_COMMAND;

/**
 * @author Tatsiana Klasevich
 * This command allows mailing user a letter about his order has been prepared.
 */
public class MailCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private CommandServiceImpl service;

    /**
     * Instantiates a new Mail command.
     *
     * @param service the command service
     */
    public MailCommand(CommandServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String mailTo = request.getParameter(PARAM_MAIL);
        logger.debug("mailto {}", mailTo);
        String name = request.getParameter(PARAM_NAME);
        String orderId = request.getParameter(ORDER_ID);
        String amount = request.getParameter(AMOUNT);
        String subject = SUBJECT;
        String body = BODY1 + name + BODY2 + orderId + BODY3 + amount;
        if (service.sendMail(mailTo, subject, body)) {
            request.setAttribute(ORDER_ID, orderId);
            request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("mail.sent"));
        } else {
            request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("mail.error"));
        }
        CommandResult commandResult = new CommandResult(TO_SHOW_ADMIN_ORDER_COMMAND + orderId, FORWARD);
        return commandResult;
    }
}
