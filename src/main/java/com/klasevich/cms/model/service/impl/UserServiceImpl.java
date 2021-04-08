package com.klasevich.cms.model.service.impl;

import com.klasevich.cms.model.dao.UserDao;
import com.klasevich.cms.model.dao.DaoException;
import com.klasevich.cms.model.entity.Role;
import com.klasevich.cms.model.factory.AbstractFactory;
import com.klasevich.cms.model.factory.impl.UserFactory;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.model.dao.impl.UserDaoImpl;
import com.klasevich.cms.model.entity.User;
import com.klasevich.cms.model.service.UserService;
import com.klasevich.cms.util.EncodingPassword;
import com.klasevich.cms.util.validator.GeneralValidator;
import com.klasevich.cms.util.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.klasevich.cms.command.RequestParameter.PARAM_PASSWORD;

public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger();
    private UserDao userDao = new UserDaoImpl();
    private AbstractFactory<User> factory = new UserFactory();

    @Override
    public Optional<User> findUserByMailPassword(String mail, String password) throws ServiceException {
        Optional<User> user = Optional.empty();
        String encodingPassword = EncodingPassword.createEncodingPassword(password);
        try {
            if (GeneralValidator.isValidMail(mail) && GeneralValidator.isValidPass(password)) {
                user = userDao.findUserByMailPassword(mail, encodingPassword);
                logger.debug("user in service {}", user);
            }
            return user;

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<User> findUserByParameter(String parameter) throws ServiceException {
        try {
            Optional<User> user = userDao.findUserByParameter(parameter);
            logger.debug("user in service {}", user);
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean register(Map<String, String> data, Role role) throws ServiceException {
        boolean result = false;
        if (UserValidator.isValidRegistration(data)) {
            String password = data.get(PARAM_PASSWORD);
            String encodingPassword = EncodingPassword.createEncodingPassword(password);
            User user = factory.create(data);
            user.setRole(role);
            try {
                userDao.register(user, encodingPassword);
                logger.debug("send to dao user {} password {}", user, password);
                result = true;
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean changePassword(String mail, String password, String passwordNew, String repeatedPassword) throws ServiceException {
        boolean result = false;
        if (GeneralValidator.isValidPass(password) && GeneralValidator.isValidPass(passwordNew) && GeneralValidator.isValidRepeatedPass(passwordNew, repeatedPassword)) {
            String encodingPassword = EncodingPassword.createEncodingPassword(password);
            logger.debug("encoding password {}", encodingPassword);
            try {
                Optional<User> user = userDao.findUserByMailPassword(mail, encodingPassword);
                if (user.isPresent()) {
                    String encodingPasswordNew = EncodingPassword.createEncodingPassword(passwordNew);

                    if (userDao.updatePassword(mail, encodingPasswordNew)) {
                        result = true;
                    }
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean removeUser(int id) throws ServiceException {
        try {
            boolean result = userDao.deleteUserById(id);
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeUser(Map<String, String> data) throws ServiceException {
        boolean userHasChanged = false;
        if (UserValidator.isValidUser(data)) {
            User user = factory.create(data);
            try {
                userHasChanged = userDao.updateUser(user);
                logger.debug("user has changed {}", userHasChanged);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return userHasChanged;
    }

    @Override
    public boolean userExists(String mail) throws ServiceException {
        logger.debug("tut exist");
        try {
            return userDao.findUserByMail(mail);

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addAvatar(String mail, String path) throws ServiceException {
        logger.debug("in service mail {}, path {}", mail, path);
        boolean result = false;
        try {
            if (userDao.addAvatar(mail, path)) {
                result = true;
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findUsersByPageNumber(int pageNumber, int limit) throws ServiceException {
        try {
            return userDao.findUsersByPageNumber(pageNumber, limit);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int sizeUsers() throws ServiceException {
        try {
            return userDao.sizeUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
