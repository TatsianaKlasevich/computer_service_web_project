package com.klasevich.cms.controller.listner;

import com.klasevich.cms.model.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import static com.klasevich.cms.command.command_parameter.RequestParameter.RU;
import static com.klasevich.cms.command.command_parameter.SessionAttribute.CURRENT_ROLE;
import static com.klasevich.cms.command.command_parameter.SessionAttribute.LOCALE;

/**
 * @author Tatsiana Klasevich
 * The session listener to observe by session attributes and when session destroyed.
 */
@WebListener
public class SessionListener implements HttpSessionListener {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        session.setAttribute(LOCALE, RU);
        logger.info("session locale {}", session.getAttribute(LOCALE));
        session.setAttribute(CURRENT_ROLE, Role.GUEST);
        logger.info("session current role {}", session.getAttribute(CURRENT_ROLE));
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        event.getSession().invalidate();
        logger.info("Session destroyed");
    }
}
