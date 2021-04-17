package com.klasevich.cms.model.dao;

import com.klasevich.cms.model.entity.Category;
import com.klasevich.cms.model.entity.ComputerService;

import java.util.List;
import java.util.Optional;

/**
 * @author Tatsiana Klasevich
 * Interface used for interactions with service table.
 */
public interface ServiceDao {
    /**
     * Add service to the table.
     *
     * @param service the service
     * @return true if service added
     * @throws DaoException if the database throws SQLException.
     */
    boolean addService(ComputerService service) throws DaoException;

    /**
     * Delete service by id.
     *
     * @param id service's id
     * @return true if service deleted
     * @throws DaoException if the database throws SQLException.
     */
    boolean deleteServiceById(int id) throws DaoException;

    /**
     * Find all services by category.
     *
     * @param category the type of Category
     * @return all services by category
     * @throws DaoException if the database throws SQLException.
     */
    List<ComputerService> findAllServicesByCategory(Category category) throws DaoException;

    /**
     * Find all services.
     *
     * @return all services
     * @throws DaoException if the database throws SQLException.
     */
    List<ComputerService> findAllServices() throws DaoException;

    /**
     * Find optional service by id.
     *
     * @param id service's id
     * @return the optional computer service if it exists otherwise optional.empty()
     * @throws DaoException if the database throws SQLException.
     */
    Optional<ComputerService> findServiceById(int id) throws DaoException;

    /**
     * Find services by page number.
     *
     * @param pageNumber the page number
     * @param limit the limit lines in page
     * @return all services by page number
     * @throws DaoException if the database throws SQLException.
     */
    List<ComputerService> findServicesByPageNumber(int pageNumber, int limit) throws DaoException;

    /**
     * Size services.
     *
     * @return the services' size
     * @throws DaoException if the database throws SQLException.
     */
    int sizeServices() throws DaoException;

    /**
     * Add service to order.
     *
     * @param orderId   the order id
     * @param serviceId the service id
     * @throws DaoException if the database throws SQLException.
     */
    void addServiceToOrder(int orderId, int serviceId) throws DaoException;

    /**
     * Find all services by order id.
     *
     * @param orderId the order's id
     * @return all services by order's id
     * @throws DaoException if the database throws SQLException.
     */
    List<ComputerService> findAllServicesByOrderId(int orderId) throws DaoException;
}
