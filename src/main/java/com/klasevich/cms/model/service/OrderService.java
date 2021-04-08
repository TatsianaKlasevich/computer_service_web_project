package com.klasevich.cms.model.service;

import com.klasevich.cms.model.entity.Category;
import com.klasevich.cms.model.entity.Order;
import com.klasevich.cms.model.entity.Status;
import com.klasevich.cms.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderService {

    int addOrder(Map<String, String> data, boolean hasDiscount, User user) throws ServiceException;

    List<Order> findAllOrdersByUserId(int id) throws ServiceException;

    Optional<Order> findOrderById(int id) throws ServiceException;

    List<Order> findOrdersByPageNumber(int pageNumber, int limit) throws ServiceException;

    int sizeOrders() throws ServiceException;

    boolean removeOrder(int id) throws ServiceException;

    boolean changeOrderStatus(Status status, int orderId) throws ServiceException;

    List<Order> findOrdersByCategoryAndPageNumber(Category category, int pageNumber, int limit)
            throws ServiceException;

    int sizeOrdersByCategory(Category category) throws ServiceException;

    int sizeOrdersByStatus(Status status) throws ServiceException;

    List<Order> findOrdersByStatusAndPageNumber(Status status, int pageNumber, int limit)
            throws ServiceException;

    int sizeOrdersAfterDate(String date) throws ServiceException;

    List<Order> findOrdersAfterDateOnPageNumber(String date, int pageNumber, int limit)
            throws ServiceException;

    boolean changeOrderStatusAndIssueDate(Status status, String date, int orderId) throws ServiceException;

    boolean removeServiceFromOrder(int serviceId, int orderId) throws ServiceException;
}
