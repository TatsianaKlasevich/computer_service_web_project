package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.klasevich.cms.command.CommandResult.Type.REDIRECT;
import static com.klasevich.cms.command.command_parameter.PagePath.*;
import static com.klasevich.cms.command.command_parameter.SessionAttribute.CURRENT_ROLE;
import static com.klasevich.cms.command.command_parameter.SessionAttribute.USER;
import static com.klasevich.cms.model.entity.Role.GUEST;

/**
 * @author Tatsiana Klasevich
 * This command allows logging out.
 */
public class LogoutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(INDEX, REDIRECT);
        HttpSession session = request.getSession();
        if (session != null) {
            session.removeAttribute(USER);
            session.removeAttribute(CURRENT_ROLE);
            session.setAttribute(CURRENT_ROLE, GUEST);
            session.invalidate();
        }
        return commandResult;
    }
}

