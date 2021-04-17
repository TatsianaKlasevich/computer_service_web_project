package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.command_parameter.RequestParameter;
import com.klasevich.cms.util.validator.PageValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.command_parameter.RequestParameter.*;
import static com.klasevich.cms.command.command_parameter.SessionAttribute.LOCALE;
import static com.klasevich.cms.command.command_parameter.UrlPattern.CONTROLLER_BEFORE;

/**
 * @author Tatsiana Klasevich
 * This command allows changing a language.
 */
public class LocalizationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String currentLocale;
        HttpSession session = request.getSession();
        String language = request.getParameter(RequestParameter.LANGUAGE);
        currentLocale = (language.equals(LANG_RU) || language.equals(LANG_RUS)) ? RU : EN;
        logger.debug("current locale {}", currentLocale);
        String page;
        String currentPage = request.getParameter(RequestParameter.CURRENT_PAGE);
        logger.debug("current page before choose {}", currentPage);
        if (currentPage != null && !currentPage.isBlank()) {
            page = CONTROLLER_BEFORE + currentPage;
            logger.debug("current page in if {}", page);
        } else {
            String pagePath = request.getParameter(PAGE);
            page = PageValidator.findPage(pagePath);
            logger.debug("current page in else{}", page);
        }
        session.setAttribute(LOCALE, currentLocale);
        CommandResult commandResult = new CommandResult(page, FORWARD);
        return commandResult;
    }
}
