package com.klasevich.cms.model.entity;

import java.math.BigDecimal;

public class ComputerService {
    private int id;
    private String serviceName;
    private BigDecimal price;
    private Category category;

    public ComputerService() {
    }
    public ComputerService(String serviceName, BigDecimal price) {
        this.serviceName = serviceName;
        this.price = price;
    }

    public ComputerService(String serviceName, BigDecimal price, Category category) {
        this.serviceName = serviceName;
        this.price = price;
        this.category = category;
    }

    public ComputerService(int id, String serviceName, BigDecimal price) {
        this.id = id;
        this.serviceName = serviceName;
        this.price = price;
    }

    public ComputerService(int id, String serviceName, BigDecimal price, Category category) {
        this.id = id;
        this.serviceName = serviceName;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

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
