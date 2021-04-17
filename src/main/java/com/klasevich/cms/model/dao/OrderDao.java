package com.klasevich.cms.model.dao;

import com.klasevich.cms.model.entity.Category;
import com.klasevich.cms.model.entity.Order;
import com.klasevich.cms.model.entity.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Tatsiana Klasevich
 * Interface used for interactions with order table.
 */
public interface OrderDao {
    /**
     * Add order to the table.
     *
     * @param order Object Order
     * @return id of adding order
     * @throws DaoException if the database throws SQLException.
     */
    int addOrder(Order order) throws DaoException;

    /**
     * Find all orders by user id
     *
     * @param id the user's id
     * @return all the user's orders in list.
     * @throws DaoException if the database throws SQLException.
     */
    List<Order> findAllOrdersByUserId(int id) throws DaoException;

    /**
     * Find order by id .
     *
     * @param id the order's id
     * @return Optional order or optional.empty if return nothing
     * @throws DaoException if the database throws SQLException.
     */
    Optional<Order> findOrderById(int id) throws DaoException;

    /**
     * Find orders by page number.
     *
     * @param pageNumber the page number
     * @param limit      the limit lines in page
     * @return all the orders in list by page number and limit in page.
     * @throws DaoException if the database throws SQLException.
     */
    List<Order> findOrdersByPageNumber(int pageNumber, int limit) throws DaoException;

    /**
     * Size of all orders.
     *
     * @return the size of all orders
     * @throws DaoException if the database throws SQLException.
     */
    int sizeOrders() throws DaoException;

    /**
     * Delete order by id.
     *
     * @param id the order's id
     * @return true if order deleted
     * @throws DaoException if the database throws SQLException.
     */
    boolean deleteOrderById(int id) throws DaoException;

    /**
     * Update order status.
     *
     * @param status  the type of Status
     * @param orderId the order's id
     * @return true if order updated
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateOrderStatus(Status status, int orderId) throws DaoException;

    /**
     * Find orders by category and page number.
     *
     * @param category   the type of Category
     * @param pageNumber the page number
     * @param limit      the limit lines in page
     *  @return all the orders in list by page number and limit in page.
     * @throws DaoException if the database throws SQLException.
     */
    List<Order> findOrdersByCategoryAndPageNumber(Category category, int pageNumber, int limit)
            throws DaoException;

    /**
     * Size of all orders by category.

     * @param category the type of Category
     * @return the size of all orders by category
     * @throws DaoException if the database throws SQLException.
     */
    int sizeOrdersByCategory(Category category) throws DaoException;

    /**
     * Size of all orders by status.
     *
     * @param status the type of Status
     * @return the size of all orders by status
     * @throws DaoException if the database throws SQLException.
     */
    int sizeOrdersByStatus(Status status) throws DaoException;

    /**
     * Find orders by status and page number.
     *
     * @param status     the type of Status
     * @param pageNumber the page number
     * @param limit       the limit lines in page
     * @return all the orders in list by status and page number.
     * @throws DaoException if the database throws SQLException.
     */
    List<Order> findOrdersByStatusAndPageNumber(Status status, int pageNumber, int limit)
            throws DaoException;

    /**
     * Find orders after appliance date on page number.
     *
     * @param applianceDate the appliance date of order
     * @param pageNumber    the page number
     * @param limit          the limit lines in page
     * @return all the orders in list after specified appliance date by page number.
     * @throws DaoException if the database throws SQLException.
     */
    List<Order> findOrdersAfterDateOnPageNumber(LocalDate applianceDate, int pageNumber, int limit)
            throws DaoException;

    /**
     * Size orders after date.
     *
     * @param applianceDate the appliance date
     * @return the size of all orders after specified appliance date
     * @throws DaoException if the database throws SQLException.
     */
    int sizeOrdersAfterDate(LocalDate applianceDate) throws DaoException;

    /**
     * Update order status and issue date.
     *
     * @param status    the type of Status
     * @param issueDate the issue date
     * @param orderId   the order's id
     * @return true if order updated
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateOrderStatusAndIssueDate(Status status, LocalDate issueDate, int orderId) throws DaoException;

    /**
     * Delete service by order id.
     *
     * @param serviceId the service id
     * @param orderId   the order id
     * @return true if order deleted
     * @throws DaoException if the database throws SQLException.
     */
    boolean deleteServiceByOrderId(int serviceId, int orderId) throws DaoException;

}
