package com.klasevich.cms.model.dao;

import com.klasevich.cms.model.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * @author Tatsiana Klasevich
 * Interface used for interactions with user table.
 */
public interface UserDao {
    /**
     * Find user by mail.
     *
     * @param mail the user's email
     * @return true if user found
     * @throws DaoException if the database throws SQLException.
     */
    boolean findUserByMail(String mail) throws DaoException;

    /**
     * Find optional user by mail and password.
     *
     * @param mail             the user's email
     * @param encodingPassword the encoding password
     * @return the optional
     * @throws DaoException if the database throws SQLException.
     */
    Optional<User> findUserByMailPassword(String mail, String encodingPassword) throws DaoException;

    /**
     * Find optional user by id.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException if the database throws SQLException.
     */
    Optional<User> findUserById(int id) throws DaoException;

    /**
     * Add avatar.
     *
     * @param mail the user's email
     * @param path the path
     * @return true if avatar added to the profile page
     * @throws DaoException if the database throws SQLException.
     */
    boolean addAvatar(String mail, String path) throws DaoException;

    /**
     * Update user.
     *
     * @param user object User
     * @return true if user updated
     * @throws DaoException if the database throws SQLException.
     */
    boolean updateUser(User user) throws DaoException;

    /**
     * Find users by page number.
     *
     * @param pageNumber the page number
     * @param limit      the limit lines in page
     * @return all the users by page number
     * @throws DaoException if the database throws SQLException.
     */
    List<User> findUsersByPageNumber(int pageNumber, int limit) throws DaoException;

    /**
     * Size users.
     *
     * @return the users' size
     * @throws DaoException if the database throws SQLException.
     */
    int sizeUsers() throws DaoException;

    /**
     * Delete user by id.
     *
     * @param id user's id
     * @return true if user deleted
     * @throws DaoException if the database throws SQLException.
     */
    boolean deleteUserById(int id) throws DaoException;

    /**
     * Register user.
     *
     * @param user             object User
     * @param encodingPassword the encoding password
     * @return true if user registered
     * @throws DaoException if the database throws SQLException.
     */
    boolean register(User user, String encodingPassword) throws DaoException;

    /**
     * Update password.
     *
     * @param mail                the user's email
     * @param encodingPasswordNew the new encoding password
     * @return true if password updated
     * @throws DaoException if the database throws SQLException.
     */
    boolean updatePassword(String mail, String encodingPasswordNew) throws DaoException;

    /**
     * Find users by parameter.
     *
     * @param parameter  some parameter like name, surname, phone number, email
     * @param pageNumber the page number
     * @param limit      the limit lines in page
     * @return all the users by parameter
     * @throws DaoException if the database throws SQLException.
     */
    List<User> findUsersByParameter(String parameter,int pageNumber,int limit) throws DaoException;

    /**
     * Size users by parameter.
     *
     * @param parameter the parameter
     * @return the users' size by parameter
     * @throws DaoException if the database throws SQLException.
     */
    int sizeUsersByParameter(String parameter) throws DaoException;

}
