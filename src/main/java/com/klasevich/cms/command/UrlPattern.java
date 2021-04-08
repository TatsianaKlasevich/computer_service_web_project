package com.klasevich.cms.command;

public class UrlPattern {
    private UrlPattern() {
    }
    public static final String CONTROLLER ="/controller";
    public static final String CONTROLLER_BEFORE ="/controller?";
    public static final String UPLOADING_CONTROLLER ="/upload/*";
    public static final String TO_SHOW_ORDERS_COMMAND="controller?command=show_orders&&tariffNumPage=";
    public static final String TO_FIND_ORDER_BY_ID_COMMAND="controller?command=find_order_by_id&&order_id=";
    public static final String TO_SHOW_USERS_COMMAND="controller?command=show_users&&order_id=";
}
