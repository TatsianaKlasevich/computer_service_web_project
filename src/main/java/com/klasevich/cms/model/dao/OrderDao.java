package com.klasevich.cms.model.dao;

import com.klasevich.cms.model.entity.Category;
import com.klasevich.cms.model.entity.Order;
import com.klasevich.cms.model.entity.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderDao {
    int addOrder(Order order) throws DaoException;

    List<Order> findAllOrdersByUserId(int id) throws DaoException;

    Optional<Order> findOrderById(int id) throws DaoException;

    List<Order> findOrdersByPageNumber(int pageNumber, int limit) throws DaoException;

    int sizeOrders() throws DaoException;

    boolean deleteOrderById(int id) throws DaoException;

    boolean updateOrderStatus(Status status, int orderId) throws DaoException;

    List<Order> findOrdersByCategoryAndPageNumber(Category category, int pageNumber, int limit)
            throws DaoException;

    int sizeOrdersByCategory(Category category) throws DaoException;

    int sizeOrdersByStatus(Status status) throws DaoException;

    List<Order> findOrdersByStatusAndPageNumber(Status status, int pageNumber, int limit)
            throws DaoException;

    List<Order> findOrdersAfterDateOnPageNumber(LocalDate applianceDate, int pageNumber, int limit)
            throws DaoException;

    int sizeOrdersAfterDate(LocalDate applianceDate) throws DaoException;

    boolean updateOrderStatusAndIssueDate(Status status, LocalDate issueDate, int orderId) throws DaoException;

    boolean deleteServiceByOrderId(int serviceId, int orderId) throws DaoException;

}
