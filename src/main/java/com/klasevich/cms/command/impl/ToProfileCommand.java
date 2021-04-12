package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.command_parameter.SessionAttribute;
import com.klasevich.cms.model.entity.User;
import com.klasevich.cms.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.MESSAGE_WARNING;

public class ToProfileCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String messageWarning = request.getParameter(MESSAGE_WARNING);
        if (messageWarning != null) {
            request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty(messageWarning));
        }
        CommandResult commandResult = new CommandResult(PROFILE, FORWARD);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        if (user == null) {
            commandResult = new CommandResult(LOGIN, FORWARD);
        }
        return commandResult;
    }
}
