package com.klasevich.cms.model.service.impl;

import com.klasevich.cms.model.dao.DaoException;
import com.klasevich.cms.model.dao.ServiceDao;
import com.klasevich.cms.model.dao.impl.ServiceDaoImpl;
import com.klasevich.cms.model.entity.Category;
import com.klasevich.cms.model.entity.ComputerService;
import com.klasevich.cms.model.factory.AbstractFactory;
import com.klasevich.cms.model.factory.impl.ComputerServiceFactory;
import com.klasevich.cms.model.service.CommandService;
import com.klasevich.cms.model.service.ServiceException;
import com.klasevich.cms.util.mail.MailSender;
import com.klasevich.cms.util.validator.GeneralValidator;
import com.klasevich.cms.util.validator.ServiceValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CommandServiceImpl implements CommandService {
    private static final Logger logger = LogManager.getLogger();
    private static final ServiceDao serviceDao = new ServiceDaoImpl();
    private static Properties properties;
    private AbstractFactory<ComputerService> factory = new ComputerServiceFactory();


    static {
        properties = new Properties();
        try {
            properties.load(CommandServiceImpl.class.getResourceAsStream("/config/mail.properties"));
        } catch (IOException e) {
            logger.error("properties have not found", e);
        }
    }

    @Override
    public boolean sendMail(String mailTo, String subject, String body) {
        boolean result = false;
        if (mailTo != null && subject != null && body != null) {
            MailSender sender = new MailSender(mailTo, subject, body, properties);
            sender.send();
            result = true;
        }
        return result;
    }

    @Override
    public byte[] readFile(String fileName) throws ServiceException {
        byte[] result;
        Path filePath = Path.of(fileName);
        if (Files.exists(filePath)) {
            try {
                result = Files.readAllBytes(filePath);
            } catch (IOException exception) {
                throw new ServiceException("Cant read file", exception);
            }
        } else {
            throw new ServiceException("File not exists");
        }
        return result;
    }

    @Override
    public boolean addService(Map<String, String> data) throws ServiceException {
        boolean result = false;
        if (ServiceValidator.isValidService(data)) {
            ComputerService service = factory.create(data);
            try {
                result = serviceDao.addService(service);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public boolean changeService(ComputerService computerService) throws ServiceException {//todo validation
        boolean result = false;
        if (GeneralValidator.isValidServiceName(computerService.getServiceName())
                && GeneralValidator.isValidPrice(String.valueOf(computerService.getPrice()))) {
            try {
                result = serviceDao.updateService(computerService);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    @Override
    public void addServiceToOrder(int orderId, List<String> services) throws ServiceException {
        try {
            for (String stringId : services) {
                int serviceId = Integer.parseInt(stringId);
                if (serviceId > 0) {
                    serviceDao.addServiceToOrder(orderId, serviceId);
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean removeService(int id) throws ServiceException {
        try {
            boolean result = serviceDao.deleteServiceById(id);
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<ComputerService> findServiceById(int id) throws ServiceException {
        try {
            Optional<ComputerService> computerService = serviceDao.findServiceById(id);
            logger.debug("computerService in service {}", computerService);
            return computerService;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ComputerService> findAllServicesByCategory(String category) throws ServiceException {
        Category categoryResult = Category.valueOf(category.toUpperCase(Locale.ROOT));
        logger.debug("categoryResulst {}", categoryResult);
        List<ComputerService> services;
        try {
            services = serviceDao.findAllServicesByCategory(categoryResult);
            logger.debug("services in service {}", services);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return services;
    }

    @Override
    public List<ComputerService> findAllServicesByOrderId(int orderId) throws ServiceException {
        List<ComputerService> services;
        try {
            services = serviceDao.findAllServicesByOrderId(orderId);
            logger.debug("services in service {}", services);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return services;
    }

    @Override
    public List<ComputerService> findAllServices() throws ServiceException {
        List<ComputerService> services;
        try {
            services = serviceDao.findAllServices();
            logger.debug("services in service {}", services);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return services;
    }

    @Override
    public int sizeServices() throws ServiceException {
        try {
            return serviceDao.sizeServices();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ComputerService> findServicesByPageNumber(int pageNumber, int limit) throws ServiceException {
        try {
            return serviceDao.findServicesByPageNumber(pageNumber, limit);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
