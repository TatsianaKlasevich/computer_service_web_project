package com.klasevich.cms.model.entity;

import java.time.LocalDate;

public class Order {
    private static final long REPAIR_PERIOD = 5L;
    private int id;
    private LocalDate applianceDate;
    private LocalDate issueDate;
    private String address;
    private Category category;
    private String problem;
    private Status status;
    boolean hasDiscount;
    private User user;

    public Order() {
    }

    public Order(LocalDate applianceDate, String address, Category category, String problem, Status status) {
        this.applianceDate = applianceDate;
        this.issueDate = applianceDate.plusDays(REPAIR_PERIOD);
        this.address = address;
        this.category = category;
        this.problem = problem;
        this.status = status;
    }

    public Order(LocalDate applianceDate, String address, Category category, String problem, Status status, boolean hasDiscount, User user) {
        this.applianceDate = applianceDate;
        this.issueDate = applianceDate.plusDays(REPAIR_PERIOD);
        this.address = address;
        this.category = category;
        this.problem = problem;
        this.status = status;
        this.hasDiscount=hasDiscount;
        this.user = user;
    }

    public Order(int id, LocalDate applianceDate, LocalDate issueDate, String address, Category category,
                 String problem, Status status, boolean hasDiscount, User user) {
        this.id = id;
        this.applianceDate = applianceDate;
        this.issueDate = issueDate;
        this.address = address;
        this.category = category;
        this.problem = problem;
        this.status = status;
        this.hasDiscount = hasDiscount;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getApplianceDate() {
        return applianceDate;
    }

    public void setApplianceDate(LocalDate applianceDate) {
        this.applianceDate = applianceDate;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (hasDiscount != order.hasDiscount) return false;
        if (applianceDate != null ? !applianceDate.equals(order.applianceDate) : order.applianceDate != null)
            return false;
        if (issueDate != null ? !issueDate.equals(order.issueDate) : order.issueDate != null) return false;
        if (address != null ? !address.equals(order.address) : order.address != null) return false;
        if (category != order.category) return false;
        if (problem != null ? !problem.equals(order.problem) : order.problem != null) return false;
        if (status != order.status) return false;
        return user != null ? user.equals(order.user) : order.user == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (applianceDate != null ? applianceDate.hashCode() : 0);
        result = 31 * result + (issueDate != null ? issueDate.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (problem != null ? problem.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (hasDiscount ? 1 : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", applianceDate=").append(applianceDate);
        sb.append(", issueDate=").append(issueDate);
        sb.append(", address='").append(address).append('\'');
        sb.append(", category=").append(category);
        sb.append(", problem='").append(problem).append('\'');
        sb.append(", status=").append(status);
        sb.append(", hasDiscount=").append(hasDiscount);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}
