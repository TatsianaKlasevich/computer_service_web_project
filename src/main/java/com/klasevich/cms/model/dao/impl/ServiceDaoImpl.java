package com.klasevich.cms.model.dao.impl;

import com.klasevich.cms.model.dao.DaoException;
import com.klasevich.cms.model.dao.ServiceDao;
import com.klasevich.cms.model.entity.Category;
import com.klasevich.cms.model.entity.ComputerService;
import com.klasevich.cms.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.klasevich.cms.model.dao.impl.SqlQuery.*;

/**
 * @author Tatsiana Klasevich
 * OrderDao implementation.
 */
public class ServiceDaoImpl implements ServiceDao {
    private static final Logger logger = LogManager.getLogger();
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    @Override
    public boolean addService(ComputerService service) throws DaoException {
        String serviceName = service.getServiceName();
        BigDecimal price = service.getPrice();
        int categoryId = service.getCategory().ordinal() + 1;
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_SERVICE)) {
            statement.setString(1, serviceName);
            statement.setBigDecimal(2, price);
            statement.setInt(3, categoryId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void addServiceToOrder(int orderId, int serviceId) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_ADD_SERVICE_TO_ORDER)) {
            statement.setInt(1, orderId);
            statement.setInt(2, serviceId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ComputerService> findAllServicesByOrderId(int orderId) throws DaoException {
        logger.debug("orderId in dao {}", orderId);
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_SERVICES_BY_ORDER_ID)) {
            statement.setInt(1, orderId);
            List<ComputerService> services = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                services.add(convertInService(resultSet));
                logger.debug("services in dao {}", services);
            }
            return services;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean deleteServiceById(int id) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_SERVICE_BY_ID)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ComputerService> findAllServicesByCategory(Category category) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_SERVICES_BY_CATEGORY)) {
            ComputerService service;
            List<ComputerService> services = new ArrayList<>();
            int categoryId = category.ordinal() + 1;
            logger.debug("categoryId {}", categoryId);
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                service = convertInService(resultSet);
                logger.debug("service {}", service);
                services.add(service);
                logger.debug("services in dao {}", services);
            }
            return services;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ComputerService> findAllServices() throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_SERVICES)) {
            ComputerService service;
            List<ComputerService> services = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                service = convertInService(resultSet);
                logger.debug("service {}", service);
                services.add(service);
                logger.debug("services in dao {}", services);
            }
            return services;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<ComputerService> findServiceById(int id) throws DaoException {
        Optional<ComputerService> optionalService = Optional.empty();
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_SERVICE_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                optionalService = Optional.of(convertInService(resultSet));
                logger.debug("optional service {}", optionalService);
            }
            return optionalService;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<ComputerService> findServicesByPageNumber(int pageNumber, int limit) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_FIND_SERVICES_FROM_TO)) {
            int offset = pageNumber * limit - limit;
            List<ComputerService> services = new ArrayList<>();
            statement.setInt(1, limit);
            statement.setInt(2, offset);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                services.add(convertInService(resultSet));
                logger.debug("services {}", services);
            }
            return services;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public int sizeServices() throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SIZE_SERVICES)) {
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

    private ComputerService convertInService(ResultSet resultSet) throws DaoException {
        ComputerService service = new ComputerService();
        try {
            service.setId(resultSet.getInt(1));
            logger.debug("serviceId {}", resultSet.getInt(1));
            logger.debug("category result set {}", resultSet.getString(2));
            service.setCategory(Category.valueOf(resultSet.getString(2).toUpperCase(Locale.ROOT)));
            service.setServiceName(resultSet.getString(3));
            service.setPrice(resultSet.getBigDecimal(4));
        } catch (SQLException e) {
            logger.error(e);
            throw new DaoException(e);
        }
        return service;
    }
}
