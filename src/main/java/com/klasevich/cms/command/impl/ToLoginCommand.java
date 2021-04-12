package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.PagePath.LOGIN;
import static com.klasevich.cms.command.command_parameter.RequestAttribute.WELCOME_PAGE;
import static com.klasevich.cms.command.command_parameter.RequestParameter.PARAM_NAME;
import static com.klasevich.cms.command.command_parameter.RequestParameter.PARAM_SURNAME;

public class ToLoginCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(PARAM_NAME, request.getParameter(PARAM_NAME));
        request.setAttribute(PARAM_SURNAME, request.getParameter(PARAM_SURNAME));
        request.setAttribute(WELCOME_PAGE, request.getParameter(WELCOME_PAGE));
        CommandResult commandResult = new CommandResult(LOGIN, FORWARD);
        return commandResult;
    }
}

