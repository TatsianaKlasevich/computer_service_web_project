package com.klasevich.cms.command.impl;

import com.klasevich.cms.command.Command;
import com.klasevich.cms.command.CommandResult;
import com.klasevich.cms.command.SessionAttribute;
import com.klasevich.cms.model.entity.User;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.service.impl.OrderServiceImpl;
import com.klasevich.cms.util.MessageManager;
import com.klasevich.cms.util.XssProtector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

import static com.klasevich.cms.command.CommandResult.Type.FORWARD;
import static com.klasevich.cms.command.CommandResult.Type.REDIRECT;
import static com.klasevich.cms.command.PagePath.*;
import static com.klasevich.cms.command.RequestAttribute.*;
import static com.klasevich.cms.command.RequestParameter.*;

public class MakeOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger();
    private OrderServiceImpl service;

    public MakeOrderCommand(OrderServiceImpl service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        CommandResult commandResult = new CommandResult(MAKE_ORDER, FORWARD);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        String date = request.getParameter(DATE);
        String address = request.getParameter(ADDRESS);
        String category = request.getParameter(CATEGORY_NAME);
        String problem = request.getParameter(PROBLEM);
        boolean hasDiscount = (request.getParameter(HAS_DISCOUNT) != null);
        address = XssProtector.protect(address);
        problem = XssProtector.protect(problem);

        Map<String, String> data = new HashMap<>();
        data.put(DATE, date);
        data.put(ADDRESS, address);
        data.put(CATEGORY_NAME, category);
        data.put(PROBLEM, problem);
        try {
            int id = service.addOrder(data, hasDiscount, user);
            if (id > 0) {
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.add.order.successfully"));
                request.setAttribute(ORDER_ID, id);
                commandResult = new CommandResult(USER_MAIN, FORWARD);
            } else {
                request.setAttribute(DATE, data.get(DATE));
                request.setAttribute(ADDRESS, data.get(ADDRESS));
                request.setAttribute(PROBLEM, data.get(PROBLEM));
                request.setAttribute(CATEGORY_NAME, data.get(CATEGORY_NAME));
                request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("message.registration.error"));
            }
        } catch (NumberFormatException e) {
            logger.error(e);
            request.setAttribute(MESSAGE_WARNING, MessageManager.getProperty("edit.correct.data"));
        } catch (ServiceException e) {
            logger.error(e);
            commandResult=new CommandResult(ERROR_500, FORWARD);
        }
        return commandResult;
    }
}
