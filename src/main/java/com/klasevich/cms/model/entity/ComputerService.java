package com.klasevich.cms.model.entity;

import java.math.BigDecimal;

/**
 * @author Tatsiana Klasevich
 * The object Computer service.
 */
public class ComputerService {
    private int id;
    private String serviceName;
    private BigDecimal price;
    private Category category;

    /**
     * Instantiates a new Computer service.
     */
    public ComputerService() {
    }

    /**
     * Instantiates a new Computer service.
     *
     * @param serviceName the service name
     * @param price       the price
     * @param category    the category type
     */
    public ComputerService(String serviceName, BigDecimal price, Category category) {
        this.serviceName = serviceName;
        this.price = price;
        this.category = category;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets service name.
     *
     * @return the service name
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets service name.
     *
     * @param serviceName the service name
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComputerService service = (ComputerService) o;
        if (id != service.id) return false;
        if (serviceName != null ? !serviceName.equals(service.serviceName) : service.serviceName != null) return false;
        if (price != null ? !price.equals(service.price) : service.price != null) return false;
        return category == service.category;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Service{");
        sb.append("id=").append(id);
        sb.append(", serviceName='").append(serviceName).append('\'');
        sb.append(", price=").append(price);
        sb.append(", category=").append(category);
        sb.append('}');
        return sb.toString();
    }
}
