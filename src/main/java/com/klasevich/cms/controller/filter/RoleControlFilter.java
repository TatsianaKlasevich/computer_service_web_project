package com.klasevich.cms.controller.filter;

import com.klasevich.cms.command.CommandType;
import com.klasevich.cms.model.entity.Role;
import com.klasevich.cms.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import static com.klasevich.cms.command.PagePath.ERROR_404;
import static com.klasevich.cms.command.RequestParameter.COMMAND;
import static com.klasevich.cms.command.SessionAttribute.USER;
import static com.klasevich.cms.command.UrlPattern.CONTROLLER;

@WebFilter(urlPatterns = {CONTROLLER})
public class RoleControlFilter implements Filter {
    private static final Logger logger = LogManager.getLogger();
    private static final CommandRoleMap MAP = CommandRoleMap.getInstance();

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String command = request.getParameter(COMMAND);
        if (command != null) {
            CommandType type = CommandType.valueOf(command.toUpperCase());
            Role role = defineRole(request);
            logger.debug("Role defined in filter as {}", role);
            if (!MAP.containsRole(type, role)) {
                logger.warn("Role control filter: '{}' attempted to execute '{}' command ", role, command);
                response.sendError(HttpServletResponse.SC_FORBIDDEN);//todo know out
                return;
            }
        } else {
            logger.warn("Role control filter: no command to execute!");
            RequestDispatcher dispatcher = request.getRequestDispatcher(ERROR_404);//todo
            dispatcher.forward(request, response);
        }
        chain.doFilter(request, response);
    }

    private Role defineRole(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER);
        return (user == null)
                ? Role.GUEST
                : user.getRole();
    }
}
