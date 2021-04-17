package com.klasevich.cms.model.service;

import com.klasevich.cms.model.entity.Category;
import com.klasevich.cms.model.entity.Order;
import com.klasevich.cms.model.entity.Status;
import com.klasevich.cms.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Tatsiana Klasevich
 * The interface provides actions on Order.
 */
public interface OrderService {

    /**
     * Add order.
     *
     * @param data        Map object with applicant request's fields with RequestParameter's constants as keys inside.
     * @param hasDiscount true if order has discount
     * @param user        object User
     * @return the adding order's id
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    int addOrder(Map<String, String> data, boolean hasDiscount, User user) throws ServiceException;

    /**
     * Find all orders by user id.
     *
     * @param id the user's id
     * @return all the orders by user's id in the list
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    List<Order> findAllOrdersByUserId(int id) throws ServiceException;

    /**
     * Find optional order by id .
     *
     * @param id the order's id
     * @return the optional object Order or optional.empty() if it doesn't exist
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    Optional<Order> findOrderById(int id) throws ServiceException;

    /**
     * Find orders by page number.
     *
     * @param pageNumber the page number
     * @param limit       the limit lines in page
     * @return all the orders by page number in the list
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    List<Order> findOrdersByPageNumber(int pageNumber, int limit) throws ServiceException;

    /**
     * Size orders.
     *
     * @return the all orders' size
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    int sizeOrders() throws ServiceException;

    /**
     * Remove order.
     *
     * @param id the order's id
     * @return true if order has been removed
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    boolean removeOrder(int id) throws ServiceException;

    /**
     * Change order status.
     *
     * @param status  the type of status
     * @param orderId the order id
     * @return true if order has been changed
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    boolean changeOrderStatus(Status status, int orderId) throws ServiceException;

    /**
     * Find orders by category and page number.
     *
     * @param category   the type of category
     * @param pageNumber the page number
     * @param limit      the limit lines in page
     * @return all the orders by category and page number in the list
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    List<Order> findOrdersByCategoryAndPageNumber(Category category, int pageNumber, int limit)
            throws ServiceException;

    /**
     * Size orders by category.
     *
     * @param category the type of category
     * @return the all orders' size by category
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    int sizeOrdersByCategory(Category category) throws ServiceException;

    /**
     * Size orders by status.
     *
     * @param status the type of status
     * @return the all orders' size by status
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    int sizeOrdersByStatus(Status status) throws ServiceException;

    /**
     * Find orders by status and page number.
     *
     * @param status     the type of status
     * @param pageNumber the page number
     * @param limit       the limit lines in page
     * @return all the orders by status and page number in the list
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    List<Order> findOrdersByStatusAndPageNumber(Status status, int pageNumber, int limit)
            throws ServiceException;

    /**
     * Size orders after date.
     *
     * @param date the appliance date
     * @return the all orders' size after determined appliance date
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    int sizeOrdersAfterDate(String date) throws ServiceException;

    /**
     * Find orders after date on page number.
     *
     * @param date       the appliance date
     * @param pageNumber the page number
     * @param limit      the limit lines in page
     * @return all the orders after determined appliance date and page number in the list
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    List<Order> findOrdersAfterDateOnPageNumber(String date, int pageNumber, int limit)
            throws ServiceException;

    /**
     * Change order status and issue date.
     *
     * @param status  the type of status
     * @param date    the appliance date
     * @param orderId the order id
     * @return true if order has been changed
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    boolean changeOrderStatusAndIssueDate(Status status, String date, int orderId) throws ServiceException;

    /**
     * Remove service from order.
     *
     * @param serviceId the service id
     * @param orderId   the order id
     * @return true if order has been removed
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    boolean removeServiceFromOrder(int serviceId, int orderId) throws ServiceException;
}
