package com.klasevich.cms.model.dao;

import com.klasevich.cms.model.entity.Category;
import com.klasevich.cms.model.entity.ComputerService;

import java.util.List;
import java.util.Optional;

public interface ServiceDao {
    boolean addService(ComputerService service) throws DaoException;

    boolean deleteServiceById(int id) throws DaoException;

    List<ComputerService> findAllServicesByCategory(Category category) throws DaoException;

    List<ComputerService> findAllServices() throws DaoException;

    Optional<ComputerService> findServiceById(int id) throws DaoException;

    boolean updateService(ComputerService computerService) throws DaoException;

    List<ComputerService> findServicesByPageNumber(int pageNumber, int limit) throws DaoException;

    int sizeServices() throws DaoException;

    void addServiceToOrder(int orderId, int serviceId) throws DaoException;

    List<ComputerService> findAllServicesByOrderId(int orderId) throws DaoException;
}
