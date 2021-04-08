package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.RequestParameter;
import com.klasevich.cms.model.service.impl.CommandServiceImpl;
import com.klasevich.cms.util.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.klasevich.cms.command.CommandResult.Type.REDIRECT;
import static com.klasevich.cms.command.PagePath.MAIL;
import static com.klasevich.cms.command.RequestAttribute.*;

public class MailCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private CommandServiceImpl service;

    public MailCommand(CommandServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String mailTo = request.getParameter(RequestParameter.MAIL_TO);
        logger.debug("mailto {}", mailTo);
        String subject = request.getParameter(RequestParameter.SUBJECT);
        logger.debug("subject {}", subject);
        String body = request.getParameter(RequestParameter.BODY);
        logger.debug("body {}", body);

        if (service.sendMail(mailTo, subject, body)) {
            request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("mail.sent"));
        } else {
            request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("mail.error"));
        }
        CommandResult commandResult = new CommandResult(MAIL, REDIRECT);
        return commandResult;
    }
}
