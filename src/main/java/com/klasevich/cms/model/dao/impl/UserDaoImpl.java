package com.klasevich.cms.model.dao.impl;

import com.klasevich.cms.model.dao.UserDao;
import com.klasevich.cms.model.entity.Role;
import com.klasevich.cms.model.entity.User;
import com.klasevich.cms.model.dao.DaoException;
import com.klasevich.cms.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.klasevich.cms.model.dao.impl.SqlQuery.*;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public boolean register(User user, String encodingPassword) throws DaoException {
        logger.debug("user in dao {}", user);
        String name = user.getName();
        String surname = user.getSurname();
        String mail = user.getMail();
        String phone = user.getPhone();
        int roleId=2;
        if (user.getRole()==Role.GUEST){
            roleId=1;
        }
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_REGISTER_USER)) {
            statement.setString(1, name);
            logger.debug("setname {}", name);
            statement.setString(2, surname);
            logger.debug("setsurname {}", surname);
            statement.setString(3, mail);
            logger.debug("setMail {}", mail);
            statement.setString(4, phone);
            logger.debug("setPhone {}", phone);
            statement.setString(5, encodingPassword);
            logger.debug("setPassword {}", encodingPassword);
            statement.setInt(6, roleId);
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateUser(User user) throws DaoException{
        String name = user.getName();
        String surname= user.getSurname();
        String phone=user.getPhone();
        String mail = user.getMail();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, phone);
            statement.setString(4, mail);
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updatePassword(String mail,String encodingPasswordNew) throws DaoException{
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_PASSWORD)) {
            statement.setString(1, encodingPasswordNew);
            statement.setString(2, mail);
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<User> findUsersByPageNumber(int pageNumber, int limit) throws DaoException{
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USERS_FROM_TO)) {
            int offset = pageNumber*limit - limit;
            List<User> users = new ArrayList<>();
            statement.setInt(1, limit);
            statement.setInt(2,offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(convertInUser(resultSet));
                logger.debug("users {}", users);
            }
            return users;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteUserById(int id) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_BY_ID)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int sizeUsers() throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SIZE_USERS)) {
            ResultSet resultSet = statement.executeQuery();
            int size=0;
            if (resultSet.next()) {
                size=resultSet.getInt(1);
            }
            return size;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean findUserByMail(String mail) throws DaoException {
        boolean exists = false;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_MAIL)) {
            statement.setString(1, mail);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
            logger.debug("mail exists? {}", exists);
            return exists;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public Optional<User> findUserById(int id) throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                optionalUser = Optional.of(convertInUser(resultSet));
                logger.debug("optional user {}", optionalUser);
            }
            return optionalUser;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional <User> findUserByMailPassword(String mail, String encodingPassword)throws DaoException {
        logger.debug("mail {}", mail);
        logger.debug("password {}", encodingPassword);
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_MAIL_AND_PASSWORD)) {
            statement.setString(1, mail);
            statement.setString(2,encodingPassword);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
              optionalUser = Optional.of(convertInUser(resultSet));
              logger.debug("optional user in dao {}", optionalUser);
            }
            return optionalUser;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional <User> findUserByParameter(String parameter)throws DaoException {
        Optional<User> optionalUser = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_PARAMETER)) {
            statement.setString(1, parameter);
            statement.setString(2, parameter);
            statement.setString(3, parameter);
            statement.setString(4, parameter);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
              optionalUser = Optional.of(convertInUser(resultSet));
              logger.debug("optional user in dao {}", optionalUser);
            }
            return optionalUser;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean addAvatar (String mail,String path) throws DaoException{
        logger.debug("in dao mail {}, path {}", mail,path);
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_AVATAR)) {
            statement.setString(1, path);
            statement.setString(2, mail);
            return statement.executeUpdate()>0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private  User convertInUser(ResultSet resultSet) throws DaoException {
        User user = new User();
        try {
            user.setId(resultSet.getInt(1));
            user.setName(resultSet.getString(2));
            user.setSurname(resultSet.getString(3));
            user.setMail(resultSet.getString(4));
            user.setPhone(resultSet.getString(5));
            user.setAvatar(resultSet.getString(6));
            String role = resultSet.getString(7).toUpperCase();
            logger.debug("role from bd {}", role);
          Role userRole = Role.valueOf(role);
          logger.debug("userRole {}", userRole);
            user.setRole(Role.valueOf(role));

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return user;
    }

}
