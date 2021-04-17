package com.klasevich.cms.command;

import com.klasevich.cms.command.impl.*;
import com.klasevich.cms.model.service.impl.*;

/**
 * @author Tatsiana Klasevich
 * The class contains types of commands.
 */
public enum CommandType {

    /**
     * The Login.
     */
    LOGIN {
        {
            this.command = new LoginCommand(new UserServiceImpl());
        }
    },
    /**
     * The Add service.
     */
    ADD_SERVICE {
        {
            this.command = new AddServiceCommand(new CommandServiceImpl());
        }
    },
    /**
     * The Logout.
     */
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    /**
     * The Take avatar.
     */
    TAKE_AVATAR {
        {
            this.command = new TakeAvatarCommand(new CommandServiceImpl());
        }
    },
    /**
     * The Show user order.
     */
    SHOW_USER_ORDER {
        {
            this.command = new ShowUserOrderCommand(new OrderServiceImpl(), new CommandServiceImpl());
        }
    },
    /**
     * The Change order status.
     */
    CHANGE_ORDER_STATUS {
        {
            this.command = new ChangeOrderStatusCommand(new OrderServiceImpl());
        }
    },
    /**
     * The Change admin order.
     */
    CHANGE_ADMIN_ORDER {
        {
            this.command = new ChangeAdminOrderCommand(new OrderServiceImpl());
        }
    },
    /**
     * The User order.
     */
    USER_ORDER {
        {
            this.command = new UserOrderCommand(new OrderServiceImpl());
        }
    },
    /**
     * The Find order by id.
     */
    FIND_ORDER_BY_ID {
        {
            this.command = new FindOrderByIdCommand(new OrderServiceImpl(), new CommandServiceImpl());
        }
    },
    /**
     * The Find orders by category.
     */
    FIND_ORDERS_BY_CATEGORY {
        {
            this.command = new FindOrdersByCategoryCommand(new OrderServiceImpl());
        }
    },
    /**
     * The Find orders by status.
     */
    FIND_ORDERS_BY_STATUS {
        {
            this.command = new FindOrdersByStatusCommand(new OrderServiceImpl());
        }
    },
    /**
     * The Find orders after date.
     */
    FIND_ORDERS_AFTER_DATE {
        {
            this.command = new FindOrdersAfterDateCommand(new OrderServiceImpl());
        }
    },
    /**
     * The Find users by parameter.
     */
    FIND_USERS_BY_PARAMETER {
        {
            this.command = new FindUsersByParameterCommand(new UserServiceImpl());
        }
    },
    /**
     * The Register.
     */
    REGISTER {
        {
            this.command = new RegisterCommand(new UserServiceImpl());
        }
    },
    /**
     * The Change user.
     */
    CHANGE_USER {
        {
            this.command = new ChangeUserCommand(new UserServiceImpl());
        }
    },
    /**
     * The Make order.
     */
    MAKE_ORDER {
        {
            this.command = new MakeOrderCommand(new OrderServiceImpl());
        }
    },
    /**
     * The Show services.
     */
    SHOW_SERVICES {
        {
            this.command = new ShowServicesCommand(new CommandServiceImpl());
        }
    },
    /**
     * The Show orders.
     */
    SHOW_ORDERS {
        {
            this.command = new ShowOrdersCommand(new OrderServiceImpl());
        }
    },
    /**
     * The Show orders to add services.
     */
    SHOW_ORDERS_TO_ADD_SERVICES {
        {
            this.command = new ShowOrdersToAddServicesCommand(new OrderServiceImpl());
        }
    },
    /**
     * The Show users.
     */
    SHOW_USERS {
        {
            this.command = new ShowUsersCommand(new UserServiceImpl());
        }
    },
    /**
     * The Remove service.
     */
    REMOVE_SERVICE {
        {
            this.command = new RemoveServiceCommand(new CommandServiceImpl());
        }
    },
    /**
     * The Remove service from order.
     */
    REMOVE_SERVICE_FROM_ORDER {
        {
            this.command = new RemoveServiceFromOrderCommand(new OrderServiceImpl());
        }
    },
    /**
     * The Remove user.
     */
    REMOVE_USER {
        {
            this.command = new RemoveUserCommand(new UserServiceImpl());
        }
    },
    /**
     * The Remove order.
     */
    REMOVE_ORDER {
        {
            this.command = new RemoveOrderCommand(new OrderServiceImpl());
        }
    },
    /**
     * The To registration.
     */
    TO_REGISTRATION {
        {
            this.command = new ToRegistrationCommand();
        }
    },
    /**
     * The To admin profile.
     */
    TO_ADMIN_PROFILE {
        {
            this.command = new ToAdminProfileCommand();
        }
    },
    /**
     * The To add admin.
     */
    TO_ADD_ADMIN {
        {
            this.command = new ToAddAdminCommand();
        }
    },
    /**
     * The To admin main.
     */
    TO_ADMIN_MAIN {
        {
            this.command = new ToAdminMainCommand();
        }
    },
    /**
     * The To change password.
     */
    TO_CHANGE_PASSWORD {
        {
            this.command = new ToChangePasswordCommand();
        }
    },
    /**
     * The Change password.
     */
    CHANGE_PASSWORD {
        {
            this.command = new ChangePasswordCommand(new UserServiceImpl());
        }
    },
    /**
     * The To admin manage service.
     */
    TO_ADMIN_MANAGE_SERVICE {
        {
            this.command = new ToAdminManageServiceCommand();
        }
    },
    /**
     * The To admin manage user.
     */
    TO_ADMIN_MANAGE_USER {
        {
            this.command = new ToAdminManageUserCommand();
        }
    },
    /**
     * The To admin manage order.
     */
    TO_ADMIN_MANAGE_ORDER {
        {
            this.command = new ToAdminManageOrderCommand();
        }
    },
    /**
     * The Admin add service to order.
     */
    ADMIN_ADD_SERVICE_TO_ORDER {
        {
            this.command = new AdminAddServiceToOrderCommand(new OrderServiceImpl(), new CommandServiceImpl());
        }
    },
    /**
     * The To user order.
     */
    TO_USER_ORDER {
        {
            this.command = new ToUserOrderCommand();
        }
    },
    /**
     * The To making order.
     */
    TO_MAKING_ORDER {
        {
            this.command = new ToMakingOrderCommand();
        }
    },
    /**
     * The To profile.
     */
    TO_PROFILE {
        {
            this.command = new ToProfileCommand();
        }
    },
    /**
     * The To login.
     */
    TO_LOGIN {
        {

            this.command = new ToLoginCommand();
        }
    },
    /**
     * The To change user.
     */
    TO_CHANGE_USER {
        {

            this.command = new ToChangeUserCommand();
        }
    },
    /**
     * The To delivery.
     */
    TO_DELIVERY {
        {

            this.command = new ToDeliveryCommand();
        }
    },
    /**
     * The To service.
     */
    TO_SERVICE {
        {
            this.command = new ToServiceCommand();
        }
    },
    /**
     * The Show category services.
     */
    SHOW_CATEGORY_SERVICES {
        {
            this.command = new ShowCategoryServicesCommand(new CommandServiceImpl());
        }
    },
    /**
     * The To user main.
     */
    TO_USER_MAIN {
        {
            this.command = new ToUserMainCommand();
        }
    },
    /**
     * The To contacts.
     */
    TO_CONTACTS {
        {
            this.command = new ToContactsCommand();
        }
    },
    /**
     * The Home.
     */
    HOME {
        {
            this.command = new HomeCommand();
        }
    },
    /**
     * The Mail.
     */
    MAIL {
        {
            this.command = new MailCommand(new CommandServiceImpl());
        }
    },
    /**
     * The Localization.
     */
    LOCALIZATION {
        {
            this.command = new LocalizationCommand();
        }
    };

    /**
     * The Command.
     */
    Command command;

    /**
     * Gets current command.
     *
     * @return the current command
     */
    public Command getCurrentCommand() {
        return command;
    }
}
