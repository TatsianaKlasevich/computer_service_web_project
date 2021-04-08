package com.klasevich.cms.model.dao;

import com.klasevich.cms.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    boolean findUserByMail(String mail) throws DaoException;

    Optional<User> findUserByMailPassword(String mail, String encodingPassword) throws DaoException;

    Optional<User> findUserById(int id) throws DaoException;

    boolean addAvatar(String mail, String path) throws DaoException;

    boolean updateUser(User user) throws DaoException;

    List<User> findUsersByPageNumber(int pageNumber, int limit) throws DaoException;

    int sizeUsers() throws DaoException;

    boolean deleteUserById(int id) throws DaoException;

    boolean register(User user, String encodingPassword) throws DaoException;

    boolean updatePassword(String mail, String encodingPasswordNew) throws DaoException;

    Optional<User> findUserByParameter(String parameter) throws DaoException;

}
