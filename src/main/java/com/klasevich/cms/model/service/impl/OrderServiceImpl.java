package com.klasevich.cms.model.service.impl;

import com.klasevich.cms.model.dao.DaoException;
import com.klasevich.cms.model.dao.OrderDao;
import com.klasevich.cms.model.dao.impl.OrderDaoImpl;
import com.klasevich.cms.model.entity.*;
import com.klasevich.cms.model.factory.AbstractFactory;
import com.klasevich.cms.model.factory.impl.OrderFactory;
import com.klasevich.cms.model.service.OrderService;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.util.validator.DateValidator;
import com.klasevich.cms.util.validator.GeneralValidator;
import com.klasevich.cms.util.validator.OrderValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Tatsiana Klasevich
 * The Order service implementation
 */
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LogManager.getLogger();
    private AbstractFactory<Order> factory = new OrderFactory();
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public int addOrder(Map<String, String> data, boolean hasDiscount, User user) throws ServiceException {
        int id = 0;
        if (OrderValidator.isValidOrder(data)) {
            Order order = factory.create(data);
            order.setHasDiscount(hasDiscount);
            order.setUser(user);
            logger.debug("order {}", order);
            try {
                id = orderDao.addOrder(order);

            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return id;
    }

    @Override
    public Optional<Order> findOrderById(int id) throws ServiceException {
        try {
            Optional<Order> order = orderDao.findOrderById(id);
            logger.debug("order in service {}", order);
            return order;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findAllOrdersByUserId(int id) throws ServiceException {
        List<Order> orders;
        try {
            orders = orderDao.findAllOrdersByUserId(id);
            logger.debug("orders in service {}", orders);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return orders;
    }

    @Override
    public boolean removeOrder(int id) throws ServiceException {
        try {
            boolean result = orderDao.deleteOrderById(id);
            logger.debug("order removed - {}", result);
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean removeServiceFromOrder(int serviceId, int orderId) throws ServiceException {
        try {
            return orderDao.deleteServiceByOrderId(serviceId, orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeOrderStatus(Status status, int orderId) throws ServiceException {
        try {
            return orderDao.updateOrderStatus(status, orderId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeOrderStatusAndIssueDate(Status status, String date, int orderId) throws ServiceException {
        boolean result = false;
        try {
            if (GeneralValidator.isValidDate(date)) {
                date = DateValidator.changeDate(date);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.d");
                LocalDate issueDate = LocalDate.parse(date, formatter);
                logger.debug("issue date in service {}", issueDate);
                result = orderDao.updateOrderStatusAndIssueDate(status, issueDate, orderId);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public int sizeOrders() throws ServiceException {
        try {
            return orderDao.sizeOrders();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int sizeOrdersByCategory(Category category) throws ServiceException {
        try {
            return orderDao.sizeOrdersByCategory(category);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int sizeOrdersByStatus(Status status) throws ServiceException {
        try {
            return orderDao.sizeOrdersByStatus(status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int sizeOrdersAfterDate(String date) throws ServiceException {
        int result = 0;
        try {
            if (GeneralValidator.isValidDate(date)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.d");
                LocalDate applianceDate = LocalDate.parse(date, formatter);
                result = orderDao.sizeOrdersAfterDate(applianceDate);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    @Override
    public List<Order> findOrdersByPageNumber(int pageNumber, int limit) throws ServiceException {
        try {
            return orderDao.findOrdersByPageNumber(pageNumber, limit);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findOrdersByCategoryAndPageNumber(Category category, int pageNumber, int limit)
            throws ServiceException {
        try {
            return orderDao.findOrdersByCategoryAndPageNumber(category, pageNumber, limit);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findOrdersByStatusAndPageNumber(Status status, int pageNumber, int limit)
            throws ServiceException {
        try {
            return orderDao.findOrdersByStatusAndPageNumber(status, pageNumber, limit);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> findOrdersAfterDateOnPageNumber(String date, int pageNumber, int limit)
            throws ServiceException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.d");
            LocalDate applianceDate = LocalDate.parse(date, formatter);
            return orderDao.findOrdersAfterDateOnPageNumber(applianceDate, pageNumber, limit);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
