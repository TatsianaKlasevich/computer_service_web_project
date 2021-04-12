package com.klasevich.cms.model.dao.impl;

import org.intellij.lang.annotations.Language;

public class SqlQuery {

    private SqlQuery() {
    }

    //orderDao
    @Language("MySQL")
    public static final String SQL_ADD_ORDER = "INSERT INTO orders (appliance_date,issue_date,address,user_id_fk," +
            "status, category_id_fk, problem,has_discount) VALUES (?,?,?,?,?,?,?,?)";
    @Language("MySQL")
    public static final String SQL_FIND_ALL_ORDERS_BY_USER_ID = "SELECT order_id, appliance_date, issue_date, problem, category," +
            " user_id_fk,address,status,has_discount FROM orders,categories WHERE user_id_fk = ? AND category_id_fk=categories.category_id";
    @Language("MySQL")
    public static final String SQL_FIND_ORDER_BY_ID = "SELECT order_id, appliance_date, issue_date, problem, category," +
            " user_id_fk,address,status,has_discount FROM orders " +
            "JOIN categories c on c.category_id = orders.category_id_fk WHERE order_id = ?";
    @Language("MySQL")
    public static final String SQL_DELETE_ORDER_BY_ID = "DELETE FROM orders WHERE order_id=?";
    @Language("MySQL")
    public static final String SQL_DELETE_SERVICE_BY_ORDER_ID = "DELETE FROM orders_services WHERE order_id_fk=? AND service_id_fk=?";
    @Language("MySQL")
    public static final String SQL_FIND_ORDERS_FROM_TO = "SELECT order_id, appliance_date, issue_date, problem, category," +
            " user_id_fk,address,status,has_discount,service_name, price FROM orders,categories,services " +
            "WHERE orders.category_id_fk=categories.category_id  LIMIT ? OFFSET ?";
    @Language("MySQL")
    public static final String SQL_FIND_ORDERS_BY_CATEGORY_FROM_TO = "SELECT order_id, appliance_date, issue_date, problem, category," +
            " user_id_fk,address,status,has_discount FROM orders " +
            "JOIN categories c on c.category_id = orders.category_id_fk " +
            "WHERE category_id_fk=?  LIMIT ? OFFSET ?";
    @Language("MySQL")
    public static final String SQL_FIND_ORDERS_BY_STATUS_FROM_TO = "SELECT order_id, appliance_date, issue_date, problem, category," +
            " user_id_fk,address,status,has_discount FROM orders " +
            "JOIN categories c on c.category_id = orders.category_id_fk " +
            "WHERE status=?  LIMIT ? OFFSET ?";
    @Language("MySQL")
    public static final String SQL_FIND_ORDERS_AFTER_DATE_FROM_TO = "SELECT order_id, appliance_date, issue_date, problem, category," +
            " user_id_fk,address,status,has_discount FROM orders " +
            "JOIN categories c on c.category_id = orders.category_id_fk " +
            "WHERE appliance_date>=?  LIMIT ? OFFSET ?";
    @Language("MySQL")
    public static final String SQL_UPDATE_ORDER_STATUS = "UPDATE orders SET status=? WHERE order_id=?";
    @Language("MySQL")
    public static final String SQL_UPDATE_ORDER_STATUS_AND_ISSUE_DATE = "UPDATE orders SET status=?, issue_date=? WHERE order_id=?";
    @Language("MySQL")
    public static final String SQL_SIZE_ORDERS = "SELECT COUNT(order_id) FROM orders";
    @Language("MySQL")
    public static final String SQL_SIZE_ORDERS_BY_CATEGORY = "SELECT COUNT(order_id) FROM orders" +
            " WHERE category_id_fk=?";
    @Language("MySQL")
    public static final String SQL_SIZE_ORDERS_BY_STATUS = "SELECT COUNT(order_id) FROM orders" +
            " WHERE status=?";
    @Language("MySQL")
    public static final String SQL_SIZE_ORDERS_AFTER_DATE = "SELECT COUNT(order_id) FROM orders" +
            " WHERE appliance_date>=?";
    //serviceDao
    @Language("MySQL")
    public static final String SQL_ADD_SERVICE = "INSERT INTO services (service_name, price, category_id_fk) " +
            "VALUES (?,?,?)";
    @Language("MySQL")
    public static final String SQL_DELETE_SERVICE_BY_ID = "DELETE FROM services WHERE service_id=?";
    @Language("MySQL")
    public static final String SQL_FIND_ALL_SERVICES_BY_CATEGORY = "SELECT service_id,category, service_name, price " +
            "FROM services,categories WHERE category_id_fk = ? AND services.category_id_fk=categories.category_id";
    @Language("MySQL")
    public static final String SQL_FIND_ALL_SERVICES = "SELECT service_id, category, service_name, price " +
            "FROM services,categories WHERE services.category_id_fk=categories.category_id";
    @Language("MySQL")
    public static final String SQL_FIND_SERVICE_BY_ID = "SELECT service_id, category, service_name, price " +
            "FROM services,categories WHERE service_iD = ? AND services.category_id_fk=categories.category_id";
    @Language("MySQL")
    public static final String SQL_FIND_ALL_SERVICES_BY_ORDER_ID = "SELECT service_id,category, service_name, price" +
            " FROM services " +
            "JOIN orders_services os on services.service_id = os.service_id_fk " +
            "JOIN categories c on c.category_id = services.category_id_fk " +
            "WHERE order_id_fk=?";
    @Language("MySQL")
    public static final String SQL_FIND_SERVICES_FROM_TO = "SELECT service_id, category, service_name, price " +
            "FROM services,categories WHERE services.category_id_fk=categories.category_id LIMIT ? OFFSET ?";
    @Language("MySQL")
    public static final String SQL_SIZE_SERVICES = "SELECT COUNT(service_id) FROM services";
    @Language("MySQL")
    public static final String SQL_ADD_SERVICE_TO_ORDER = "INSERT INTO orders_services(order_id_fk, service_id_fk)" +
            " VALUES (?,?) ";
    //userDao
    @Language("MySQL")
    public static final String SQL_FIND_USER_BY_MAIL = "SELECT mail FROM users WHERE mail = ?";
    @Language("MySQL")
    public static final String SQL_FIND_USER_BY_ID = "SELECT user_id, user_name, surname, mail, phone, " +
            "avatar, role FROM users WHERE user_id = ?";
    @Language("MySQL")
    public static final String SQL_REGISTER_USER = "INSERT INTO users (user_name, surname, mail,phone, password,role)" +
            " VALUES (?,?,?,?,?,?)";
    @Language("MySQL")
    public static final String SQL_FIND_USER_BY_MAIL_AND_PASSWORD = "SELECT user_id, user_name, surname, mail, phone, " +
            "avatar, role FROM users WHERE mail = ? AND password = ?";
    @Language("MySQL")
    public static final String SQL_FIND_USERS_BY_PARAMETER = "SELECT user_id, user_name, surname, mail, phone," +
            "avatar, role FROM users WHERE user_name = ? OR surname = ? OR phone = ? OR mail =?  LIMIT ? OFFSET ?";
    @Language("MySQL")
    public static final String SQL_ADD_AVATAR = "UPDATE users SET avatar=? WHERE mail=?";
    @Language("MySQL")
    public static final String SQL_UPDATE_USER = "UPDATE users SET user_name=?,surname=?,phone=? WHERE mail=?";
    @Language("MySQL")
    public static final String SQL_UPDATE_PASSWORD = "UPDATE users SET password=? WHERE mail=?";
    @Language("MySQL")
    public static final String SQL_FIND_USERS_FROM_TO = "SELECT user_id, user_name, surname, mail, phone, avatar," +
            " role FROM users LIMIT ? OFFSET ?";
    @Language("MySQL")
    public static final String SQL_SIZE_USERS = "SELECT COUNT(user_id) FROM users";
    @Language("MySQL")
    public static final String SQL_SIZE_USERS_BY_PARAMETER = "SELECT COUNT(user_id) FROM users " +
            "WHERE user_name = ? OR surname = ? OR phone = ? OR mail =?";
    @Language("MySQL")
    public static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE user_id=?";
}
