package com.klasevich.cms.model.entity;

import java.time.LocalDate;

/**
 * @author Tatsiana Klasevich
 * The object Order.
 */
public class Order {
    private static final long REPAIR_PERIOD = 5L;
    private int id;
    private LocalDate applianceDate;
    private LocalDate issueDate;
    private String address;
    private Category category;
    private String problem;
    private Status status;
    /**
     * The Has discount.
     */
    boolean hasDiscount;
    private User user;

    /**
     * Instantiates a new Order.
     */
    public Order() {
    }

    /**
     * Instantiates a new Order.
     *
     * @param applianceDate the appliance date
     * @param address       the address
     * @param category      the category
     * @param problem       the problem
     * @param status        the status
     */
    public Order(LocalDate applianceDate, String address, Category category, String problem, Status status) {
        this.applianceDate = applianceDate;
        this.issueDate = applianceDate.plusDays(REPAIR_PERIOD);
        this.address = address;
        this.category = category;
        this.problem = problem;
        this.status = status;
    }

    /**
     * Instantiates a new Order.
     *
     * @param applianceDate the appliance date
     * @param address       the address
     * @param category      the category
     * @param problem       the problem
     * @param status        the status
     * @param hasDiscount   the has discount
     * @param user          the user
     */
    public Order(LocalDate applianceDate, String address, Category category, String problem, Status status, boolean hasDiscount, User user) {
        this.applianceDate = applianceDate;
        this.issueDate = applianceDate.plusDays(REPAIR_PERIOD);
        this.address = address;
        this.category = category;
        this.problem = problem;
        this.status = status;
        this.hasDiscount = hasDiscount;
        this.user = user;
    }

    /**
     * Instantiates a new Order.
     *
     * @param id            the id
     * @param applianceDate the appliance date
     * @param issueDate     the issue date
     * @param address       the address
     * @param category      the category
     * @param problem       the problem
     * @param status        the status
     * @param hasDiscount   the has discount
     * @param user          the user
     */
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
     * Gets appliance date.
     *
     * @return the appliance date
     */
    public LocalDate getApplianceDate() {
        return applianceDate;
    }

    /**
     * Sets appliance date.
     *
     * @param applianceDate the appliance date
     */
    public void setApplianceDate(LocalDate applianceDate) {
        this.applianceDate = applianceDate;
    }

    /**
     * Gets issue date.
     *
     * @return the issue date
     */
    public LocalDate getIssueDate() {
        return issueDate;
    }

    /**
     * Sets issue date.
     *
     * @param issueDate the issue date
     */
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
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

    /**
     * Gets problem.
     *
     * @return the problem
     */
    public String getProblem() {
        return problem;
    }

    /**
     * Sets problem.
     *
     * @param problem the problem
     */
    public void setProblem(String problem) {
        this.problem = problem;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Is has discount boolean.
     *
     * @return the boolean
     */
    public boolean isHasDiscount() {
        return hasDiscount;
    }

    /**
     * Sets has discount.
     *
     * @param hasDiscount the has discount
     */
    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
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
