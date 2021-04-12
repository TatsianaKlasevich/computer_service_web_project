package com.klasevich.cms.model.service;

import com.klasevich.cms.model.entity.ComputerService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CommandService {
    boolean sendMail(String mailTo, String subject, String body);

    byte[] readFile(String fileName) throws ServiceException;

    boolean addService(Map<String, String> data) throws ServiceException;

    List<ComputerService> findAllServicesByCategory(String category) throws ServiceException;

    boolean removeService(int id) throws ServiceException;

    Optional<ComputerService> findServiceById(int id) throws ServiceException;

    List<ComputerService> findServicesByPageNumber(int pageNumber, int limit) throws ServiceException;

    int sizeServices() throws ServiceException;

    void addServiceToOrder(int orderId, List<String> services) throws ServiceException;

    List<ComputerService> findAllServicesByOrderId(int orderId) throws ServiceException;

    List<ComputerService> findAllServices() throws ServiceException;
}
