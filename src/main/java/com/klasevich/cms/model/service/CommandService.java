package com.klasevich.cms.model.service;

import com.klasevich.cms.model.entity.ComputerService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Tatsiana Klasevich
 * The interface provides actions on Computer service, mail and uploading file.
 */
public interface CommandService {
    /**
     * Send mail to client when order is ready.
     *
     * @param mailTo  the mail to some email
     * @param subject the subject of mail
     * @param body    the body of mail
     * @return true if mail has been sent
     */
    boolean sendMail(String mailTo, String subject, String body);

    /**
     * Read file's byte [ ] to add it to profile page further.
     *
     * @param fileName the avatar's file name
     * @return the byte [ ] of image
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    byte[] readFile(String fileName) throws ServiceException;

    /**
     * Add service.
     *
     * @param data Map object with applicant request's fields with RequestParameter's constants as keys inside.
     * @return true if service has been added
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    boolean addService(Map<String, String> data) throws ServiceException;

    /**
     * Find all services by category.
     *
     * @param category the type of category
     * @return all the services by category in the list
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    List<ComputerService> findAllServicesByCategory(String category) throws ServiceException;

    /**
     * Remove service.
     *
     * @param id the service's id
     * @return true if service has been removed
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    boolean removeService(int id) throws ServiceException;

    /**
     * Find optional service by id.
     *
     * @param id the service's id
     * @return the optional object ComputerService or optional.empty() if it doesn't exist
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    Optional<ComputerService> findServiceById(int id) throws ServiceException;

    /**
     * Find services by page number.
     *
     * @param pageNumber the page number
     * @param limit      the limit lines in page
     * @return all the services by page number the list
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    List<ComputerService> findServicesByPageNumber(int pageNumber, int limit) throws ServiceException;

    /**
     * Size services.
     *
     * @return the all services' size
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    int sizeServices() throws ServiceException;

    /**
     * Add service to order.
     *
     * @param orderId  the order id
     * @param services the services
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    void addServiceToOrder(int orderId, List<String> services) throws ServiceException;

    /**
     * Find all services by order id.
     *
     * @param orderId the order id
     * @return all the services by order's id in the list
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    List<ComputerService> findAllServicesByOrderId(int orderId) throws ServiceException;

    /**
     * Find all services.
     *
     * @return all the services in the list
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    List<ComputerService> findAllServices() throws ServiceException;
}
