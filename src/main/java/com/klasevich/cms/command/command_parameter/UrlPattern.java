package com.klasevich.cms.command.command_parameter;

/**
 *  @author  Tatsiana Klasevich
 * The class represents url patterns.
 */
public class UrlPattern {
    private UrlPattern() {
    }

    /**
     * The constant CONTROLLER.
     */
    public static final String CONTROLLER = "/controller";
    /**
     * The constant DO.
     */
    public static final String DO = "*.do";

    /**
     * The constant CONTROLLER_BEFORE.
     */
    public static final String CONTROLLER_BEFORE = "/controller?";
    /**
     * The constant UPLOADING_CONTROLLER.
     */
    public static final String UPLOADING_CONTROLLER = "/upload/*";
    /**
     * The constant TO_SHOW_ORDERS_COMMAND.
     */
    public static final String TO_SHOW_ORDERS_COMMAND = "/controller?command=show_orders&pageNumber=";
    /**
     * The constant TO_FIND_ORDER_BY_ID_COMMAND.
     */
    public static final String TO_FIND_ORDER_BY_ID_COMMAND = "/controller?command=find_order_by_id&order_id=";
    /**
     * The constant TO_SHOW_USERS_COMMAND.
     */
    public static final String TO_SHOW_USERS_COMMAND = "/controller?command=show_users&pageNumber=";
    /**
     * The constant TO_SHOW_SERVICES_COMMAND.
     */
    public static final String TO_SHOW_SERVICES_COMMAND = "/controller?command=show_services&pageNumber=";
    /**
     * The constant TO_LOGIN_COMMAND.
     */
    public static final String TO_LOGIN_COMMAND = "/controller?command=to_login&name=";
    /**
     * The constant TO_USER_MAIN_COMMAND.
     */
    public static final String TO_USER_MAIN_COMMAND = "/controller?command=to_user_main&order_id=";
    /**
     * The constant TO_SHOW_USER_ORDER_COMMAND.
     */
    public static final String TO_SHOW_USER_ORDER_COMMAND = "/controller?command=show_user_order&order_id=";
    /**
     * @author Tatsiana Klasevich
     * The constant PARAMETER_SURNAME.
     */
    public static final String PARAMETER_SURNAME = "&surname=";
    /**
     * The constant TO_ADMIN_MANAGE_SERVICE_COMMAND.
     */
    public static final String TO_ADMIN_MANAGE_SERVICE_COMMAND = "/controller?command=to_admin_manage_service&messageWarning=";
    /**
     * The constant TO_PROFILE_COMMAND.
     */
    public static final String TO_PROFILE_COMMAND = "/controller?command=to_profile&messageWarning=";
    /**
     * The constant PARAMETER_WELCOME_PAGE.
     */
    public static final String PARAMETER_WELCOME_PAGE = "&welcomePage=";
    /**
     * The constant PARAMETER_MESSAGE_WARNING.
     */
    public static final String PARAMETER_MESSAGE_WARNING = "&messageWarning=";
    /**
     * The constant TO_SHOW_ADMIN_ORDER_COMMAND.
     */
    public static final String TO_SHOW_ADMIN_ORDER_COMMAND = "/controller?command=find_order_by_id&order_id=";
}
