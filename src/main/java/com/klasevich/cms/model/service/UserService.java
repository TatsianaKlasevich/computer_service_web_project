package com.klasevich.cms.model.service;

import com.klasevich.cms.model.entity.Role;
import com.klasevich.cms.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Tatsiana Klasevich
 * The interface provides actions on User
 */
public interface UserService {
    /**
     * Find optional user by mail and password.
     *
     * @param mail     the user's mail
     * @param password the user's password
     * @return the optional object User or optional.empty() if it doesn't exist
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    Optional<User> findUserByMailPassword(String mail, String password) throws ServiceException;

    /**
     * Register user.
     *
     * @param data Map object with applicant request's fields with RequestParameter's constants as keys inside.
     * @param role the user's role
     * @return true if user has been registered
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    boolean register(Map<String, String> data, Role role) throws ServiceException;

    /**
     * Find out if user exists.
     *
     * @param mail the user's email
     * @return true if user exists
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    boolean userExists(String mail) throws ServiceException;

    /**
     * Add avatar.
     *
     * @param mail the user's email
     * @param path the page path
     * @return true if user has been added
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    boolean addAvatar(String mail, String path) throws ServiceException;

    /**
     * Change user.
     *
     * @param data Map object with applicant request's fields with RequestParameter's constants as keys inside.
     * @return true if user has been changed
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    boolean changeUser(Map<String, String> data) throws ServiceException;

    /**
     * Find users by page number.
     *
     * @param pageNumber the page number
     * @param limit      the limit lines in page
     * @return all the orders by page number in the list
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    List<User> findUsersByPageNumber(int pageNumber, int limit) throws ServiceException;

    /**
     * Size users.
     *
     * @return the all users' size
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    int sizeUsers() throws ServiceException;

    /**
     * Remove user.
     *
     * @param id the user's id
     * @return true if user has been removed
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    boolean removeUser(int id) throws ServiceException;

    /**
     * Change password.
     *
     * @param mail             the mail
     * @param password         the password
     * @param passwordNew      the password new
     * @param repeatedPassword the repeated password
     * @return true if user's password has been changed
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    boolean changePassword(String mail, String password, String passwordNew, String repeatedPassword)
            throws ServiceException;

    /**
     * Find users by parameter.
     *
     * @param parameter  the parameter
     * @param pageNumber the page number
     * @param limit       the limit lines in page
     * @return all the orders by user's id in the list
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    List<User> findUsersByParameter(String parameter, int pageNumber, int limit) throws ServiceException;

    /**
     * Size users by parameter.
     *
     * @param parameter the parameter
     * @return the all users' size
     * @throws ServiceException the service exception if an error occurs while processing.
     */
    int sizeUsersByParameter(String parameter) throws ServiceException;
}
