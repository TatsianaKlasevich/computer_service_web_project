package com.klasevich.cms.model.dao.impl;

import com.klasevich.cms.model.dao.DaoException;
import com.klasevich.cms.model.dao.OrderDao;
import com.klasevich.cms.model.entity.*;
import com.klasevich.cms.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.klasevich.cms.model.dao.impl.SqlQuery.*;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final UserDaoImpl dao = new UserDaoImpl();

    @Override
    public int addOrder(Order order) throws DaoException {
        int id = 0;
        LocalDate applianceDate = order.getApplianceDate();
        LocalDate issueDate = order.getIssueDate();
        logger.debug("issueDate {}", issueDate);
        String address = order.getAddress();
        int userId = order.getUser().getId();
        int statusId = order.getStatus().ordinal() + 1;
        logger.debug("statusId {}", statusId);
        int categoryId = order.getCategory().ordinal() + 1;
        String problem = order.getProblem();
        byte hasDiscount = 0;
        if (order.isHasDiscount()) {
            hasDiscount = 1;
        }
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_ORDER, Statement.RETURN_GENERATED_KEYS)) {
            logger.debug("date in dao {}", Date.valueOf(applianceDate));
            statement.setDate(1, Date.valueOf(applianceDate));
            statement.setDate(2, Date.valueOf(issueDate));
            statement.setString(3, address);
            statement.setInt(4, userId);
            statement.setInt(5, statusId);
            statement.setInt(6, categoryId);
            statement.setString(7, problem);
            statement.setByte(8, hasDiscount);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
                logger.debug("order has been added successfully, id {}", id);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return id;
    }

    public Optional<Order> findOrderById(int id) throws DaoException {
        Optional<Order> optionalOrder = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                optionalOrder = Optional.of(convertInOrder(resultSet));
                logger.debug("optional order {}", optionalOrder);
            }
            return optionalOrder;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteOrderById(int id) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER_BY_ID)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteServiceByOrderId(int serviceId, int orderId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SERVICE_BY_ORDER_ID)) {
            statement.setInt(1, orderId);
            statement.setInt(2, serviceId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateOrderStatus(Status status, int orderId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER_STATUS)) {
            int statusId = status.ordinal() + 1;
            statement.setInt(1, statusId);
            statement.setInt(2, orderId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateOrderStatusAndIssueDate(Status status, LocalDate issueDate, int orderId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER_STATUS_AND_ISSUE_DATE)) {
            int statusId = status.ordinal() + 1;
            statement.setInt(1, statusId);
            statement.setDate(2, Date.valueOf(issueDate));
            statement.setInt(3, orderId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findOrdersByPageNumber(int pageNumber, int limit) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_FROM_TO)) {
            int offset = pageNumber * limit - limit;
            List<Order> orders = new ArrayList<>();
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(convertInOrder(resultSet));
                logger.debug("orders {}", orders);
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findOrdersByCategoryAndPageNumber(Category category, int pageNumber, int limit)
            throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_CATEGORY_FROM_TO)) {
            int offset = pageNumber * limit - limit;
            List<Order> orders = new ArrayList<>();
            int categoryId = category.ordinal() + 1;
            statement.setInt(1, categoryId);
            statement.setInt(2, limit);
            statement.setInt(3, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(convertInOrder(resultSet));
                logger.debug("orders {}", orders);
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findOrdersByStatusAndPageNumber(Status status, int pageNumber, int limit)
            throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_BY_STATUS_FROM_TO)) {
            int offset = pageNumber * limit - limit;
            List<Order> orders = new ArrayList<>();
            String statusString = status.toString().toLowerCase(Locale.ROOT);
            statement.setString(1, statusString);
            statement.setInt(2, limit);
            statement.setInt(3, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(convertInOrder(resultSet));
                logger.debug("orders {}", orders);
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findOrdersAfterDateOnPageNumber(LocalDate applianceDate, int pageNumber, int limit)
            throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ORDERS_AFTER_DATE_FROM_TO)) {
            int offset = pageNumber * limit - limit;
            List<Order> orders = new ArrayList<>();
            logger.debug("date in dao {}", Date.valueOf(applianceDate));
            statement.setDate(1, Date.valueOf(applianceDate));
            statement.setInt(2, limit);
            statement.setInt(3, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(convertInOrder(resultSet));
                logger.debug("orders {}", orders);
            }
            return orders;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int sizeOrders() throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SIZE_ORDERS)) {
            ResultSet resultSet = statement.executeQuery();
            int size = 0;
            if (resultSet.next()) {
                size = resultSet.getInt(1);
            }
            return size;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int sizeOrdersByCategory(Category category) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SIZE_ORDERS_BY_CATEGORY)) {
            int categoryId = category.ordinal() + 1;
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            int size = 0;
            if (resultSet.next()) {
                size = resultSet.getInt(1);
            }
            return size;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int sizeOrdersAfterDate(LocalDate applianceDate) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SIZE_ORDERS_AFTER_DATE)) {
            statement.setDate(1, Date.valueOf(applianceDate));
            ResultSet resultSet = statement.executeQuery();
            int size = 0;
            if (resultSet.next()) {
                size = resultSet.getInt(1);
            }
            return size;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int sizeOrdersByStatus(Status status) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SIZE_ORDERS_BY_STATUS)) {
            String statusString = status.toString().toLowerCase(Locale.ROOT);
            statement.setString(1, statusString);
            ResultSet resultSet = statement.executeQuery();
            int size = 0;
            if (resultSet.next()) {
                size = resultSet.getInt(1);
            }
            return size;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Order> findAllOrdersByUserId(int id) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ORDERS_BY_USER_ID)) {
            Order order;
            List<Order> orders = new ArrayList<>();
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                order = convertInOrder(resultSet);
                orders.add(order);
            }
            logger.debug("orders in dao {}", orders);
            return orders;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private Order convertInOrder(ResultSet resultSet) throws DaoException {
        Order order = new Order();
        try {
            order.setId(resultSet.getInt(1));
            logger.debug("date sql {}", resultSet.getDate(2));
            LocalDate applianceDate = resultSet.getDate(2).toLocalDate();
            logger.debug("appliance date {}", applianceDate);
            order.setApplianceDate(applianceDate);
            LocalDate issueDate = resultSet.getDate(3).toLocalDate();
            order.setIssueDate(issueDate);
            order.setProblem(resultSet.getString(4));
            logger.debug("category {}", resultSet.getString(5).toUpperCase(Locale.ROOT));
            order.setCategory(Category.valueOf(resultSet.getString(5).toUpperCase(Locale.ROOT)));
            Optional<User> optionalUser = dao.findUserById(resultSet.getInt(6));
            logger.debug("optional user {}", optionalUser);
            order.setAddress(resultSet.getString(7));
            order.setStatus(Status.valueOf(resultSet.getString(8).toUpperCase(Locale.ROOT)));
            boolean hasDiscount = resultSet.getByte(9) != 0;
            order.setHasDiscount(hasDiscount);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                order.setUser(user);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return order;
    }
}
