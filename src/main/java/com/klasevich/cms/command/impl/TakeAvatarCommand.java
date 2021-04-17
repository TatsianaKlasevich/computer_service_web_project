package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.CommandServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.OtherParameter.EMPTY_PAGE;
import static com.klasevich.cms.command.command_parameter.OtherParameter.FILE_NAME;
import static com.klasevich.cms.command.command_parameter.PagePath.*;

/**
 * @author Tatsiana Klasevich
 * This command allows to show avatar in the page.
 */
public class TakeAvatarCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private CommandServiceImpl service;

    /**
     * Instantiates a new Take avatar command.
     *
     * @param service the command service
     */
    public TakeAvatarCommand(CommandServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(EMPTY_PAGE, FORWARD);
        String fileName = request.getParameter(FILE_NAME);
        logger.debug("filename in takeavatar {}", fileName);
        if (fileName.isEmpty()) {
            return commandResult;
        }
        try (ServletOutputStream outputStream = response.getOutputStream();) {
            outputStream.write(service.readFile(fileName));
        } catch (IOException | ServiceException e) {
            logger.error(e);
            commandResult = new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
