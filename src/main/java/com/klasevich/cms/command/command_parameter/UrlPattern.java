package com.klasevich.cms.command.command_parameter;

public class UrlPattern {
    private UrlPattern() {
    }

    public static final String CONTROLLER = "/controller";
    public static final String CONTROLLER_BEFORE = "/controller?";
    public static final String UPLOADING_CONTROLLER = "/upload/*";
    public static final String TO_SHOW_ORDERS_COMMAND = "/controller?command=show_orders&pageNumber=";
    public static final String TO_FIND_ORDER_BY_ID_COMMAND = "/controller?command=find_order_by_id&order_id=";
    public static final String TO_SHOW_USERS_COMMAND = "/controller?command=show_users&pageNumber=";
    public static final String TO_SHOW_SERVICES_COMMAND = "/controller?command=show_services&pageNumber=";
    public static final String TO_LOGIN_COMMAND = "/controller?command=to_login&name=";
    public static final String TO_USER_MAIN_COMMAND = "/controller?command=to_user_main&order_id=";
    public static final String TO_SHOW_USER_ORDER_COMMAND = "/controller?command=show_user_order&order_id=";
    public static final String PARAMETER_SURNAME = "&surname=";
    public static final String TO_ADMIN_MANAGE_SERVICE_COMMAND = "/controller?command=to_admin_manage_service&messageWarning=";
    public static final String TO_PROFILE_COMMAND = "/controller?command=to_profile&messageWarning=";
    public static final String PARAMETER_WELCOME_PAGE = "&welcomePage=";
    public static final String PARAMETER_MESSAGE_WARNING = "&messageWarning=";
}
