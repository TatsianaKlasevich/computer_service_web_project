package com.klasevich.cms.model.service;

import com.klasevich.cms.model.entity.Role;
import com.klasevich.cms.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserByMailPassword(String mail, String password) throws ServiceException;

    boolean register(Map<String, String> data, Role role) throws ServiceException;

    boolean userExists(String mail) throws ServiceException;

    boolean addAvatar(String mail, String path) throws ServiceException;

    boolean changeUser(Map<String, String> data) throws ServiceException;

    List<User> findUsersByPageNumber(int pageNumber, int limit) throws ServiceException;

    int sizeUsers() throws ServiceException;

    boolean removeUser(int id) throws ServiceException;

    boolean changePassword(String mail, String password, String passwordNew, String repeatedPassword)
            throws ServiceException;

    Optional<User> findUserByParameter(String parameter) throws ServiceException;
}
