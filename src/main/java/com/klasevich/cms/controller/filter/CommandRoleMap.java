package com.klasevich.cms.controller.filter;

import com.klasevich.cms.command.CommandType;
import com.klasevich.cms.model.entity.Role;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class CommandRoleMap {
    private static final CommandRoleMap INSTANCE = new CommandRoleMap();
    private static final EnumMap<CommandType, List<Role>> ROLE_MAP;

    static {
        ROLE_MAP = new EnumMap<>(CommandType.class);
        ROLE_MAP.put(CommandType.HOME, Arrays.asList(Role.USER, Role.GUEST, Role.ADMIN));
        ROLE_MAP.put(CommandType.TO_CONTACTS, Arrays.asList(Role.USER, Role.GUEST, Role.ADMIN));
        ROLE_MAP.put(CommandType.SHOW_CATEGORY_SERVICES, Arrays.asList(Role.USER, Role.GUEST, Role.ADMIN));
        ROLE_MAP.put(CommandType.LOCALIZATION, Arrays.asList(Role.USER, Role.GUEST, Role.ADMIN));
        ROLE_MAP.put(CommandType.TO_SERVICE, Arrays.asList(Role.USER, Role.GUEST, Role.ADMIN));
        ROLE_MAP.put(CommandType.TO_DELIVERY, Arrays.asList(Role.USER, Role.GUEST, Role.ADMIN));
        ROLE_MAP.put(CommandType.MAIL, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.LOGOUT, Arrays.asList(Role.USER, Role.ADMIN, Role.GUEST));
        ROLE_MAP.put(CommandType.TO_LOGIN, Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
        ROLE_MAP.put(CommandType.LOGIN, Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
        ROLE_MAP.put(CommandType.REGISTER, Arrays.asList(Role.GUEST, Role.ADMIN));
        ROLE_MAP.put(CommandType.TO_REGISTRATION, Arrays.asList(Role.GUEST));
        ROLE_MAP.put(CommandType.ADD_SERVICE, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.SHOW_ORDERS, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.FIND_USERS_BY_PARAMETER, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.CHANGE_ADMIN_ORDER, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.ADMIN_ADD_SERVICE_TO_ORDER, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.REMOVE_SERVICE_FROM_ORDER, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.FIND_ORDERS_BY_STATUS, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.FIND_ORDERS_BY_CATEGORY, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.FIND_ORDERS_AFTER_DATE, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.FIND_ORDER_BY_ID, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.REMOVE_ORDER, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.REMOVE_SERVICE, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.SHOW_ORDERS_TO_ADD_SERVICES, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.REMOVE_USER, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.TO_ADMIN_MAIN, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.SHOW_USERS, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.TO_ADD_ADMIN, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.TO_ADMIN_MANAGE_ORDER, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.SHOW_SERVICES, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.TO_ADMIN_MANAGE_SERVICE, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.TO_ADMIN_MANAGE_USER, Arrays.asList(Role.ADMIN));
        ROLE_MAP.put(CommandType.TO_PROFILE, Arrays.asList(Role.USER, Role.ADMIN));
        ROLE_MAP.put(CommandType.TO_CHANGE_USER, Arrays.asList(Role.USER, Role.ADMIN));
        ROLE_MAP.put(CommandType.SHOW_USER_ORDER, Arrays.asList(Role.USER));
        ROLE_MAP.put(CommandType.MAKE_ORDER, Arrays.asList(Role.USER));
        ROLE_MAP.put(CommandType.TO_USER_MAIN, Arrays.asList(Role.USER));
        ROLE_MAP.put(CommandType.CHANGE_USER, Arrays.asList(Role.USER, Role.ADMIN));
        ROLE_MAP.put(CommandType.TO_CHANGE_PASSWORD, Arrays.asList(Role.USER, Role.ADMIN));
        ROLE_MAP.put(CommandType.CHANGE_PASSWORD, Arrays.asList(Role.USER, Role.ADMIN));
        ROLE_MAP.put(CommandType.CHANGE_ORDER_STATUS, Arrays.asList(Role.USER));
        ROLE_MAP.put(CommandType.TAKE_AVATAR, Arrays.asList(Role.USER, Role.ADMIN));
        ROLE_MAP.put(CommandType.USER_ORDER, Arrays.asList(Role.USER));
        ROLE_MAP.put(CommandType.TO_MAKING_ORDER, Arrays.asList(Role.USER));
        ROLE_MAP.put(CommandType.TO_USER_ORDER, Arrays.asList(Role.USER));
    }

    private CommandRoleMap() {
    }

    static CommandRoleMap getInstance() {
        return INSTANCE;
    }

    public boolean containsRole(CommandType type, Role role) {
        return ROLE_MAP.get(type).contains(role);
    }
}
