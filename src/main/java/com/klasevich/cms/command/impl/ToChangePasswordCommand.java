package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.PagePath.CHANGE_PASSWORD;

/**
 * @author Tatsiana Klasevich
 * This command allows going to admin's profile page.
 */
public class ToChangePasswordCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(CHANGE_PASSWORD, FORWARD);
        return commandResult;
    }
}
