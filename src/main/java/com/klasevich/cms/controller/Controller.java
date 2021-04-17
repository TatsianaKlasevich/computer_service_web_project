package com.klasevich.cms.controller;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandProvider;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.pool.ConnectionPool;
import com.klasevich.cms.model.pool.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.klasevich.cms.command.command_parameter.PagePath.INDEX;
import static com.klasevich.cms.command.command_parameter.RequestParameter.COMMAND;
import static com.klasevich.cms.command.command_parameter.UrlPattern.CONTROLLER;

/**
 * @author Tatsiana Klasevich
 * The class of main controller
 */
@WebServlet(urlPatterns = {CONTROLLER})
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        Optional<Command> commandOptional = CommandProvider.defineCommand(request.getParameter(COMMAND));
        CommandResult commandResult;
        if (commandOptional.isPresent()) {
            Command command = commandOptional.get();
            commandResult = command.execute(request, response);
        } else {
            logger.error("page hasn't defined ");
            commandResult = new CommandResult(INDEX);
        }
        String urlPath = commandResult.providePath();
        switch (commandResult.getType()) {
            case FORWARD: {
                request.getRequestDispatcher(urlPath).forward(request, response);
                break;
            }
            case REDIRECT: {
                logger.debug("redirect path {}",request.getContextPath() + urlPath );
                response.sendRedirect(request.getContextPath() + urlPath);
                break;
            }
        }
    }

    public void destroy() {
        try {
            ConnectionPool.getInstance().destroyPool();
        } catch (ConnectionPoolException e) {
            logger.error("Exception during closing pool", e);
        }
    }
}
