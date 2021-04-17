package com.klasevich.cms.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Tatsiana Klasevich
 *  Interface that represents "Command" pattern. Used by a controller.
 */
public interface Command {
    /**
     * Processes a request from controller and returns the page to be redirected or forwarded.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @return CommandResult object
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response);
}
