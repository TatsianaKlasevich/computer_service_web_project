package com.klasevich.cms.command;

import com.klasevich.cms.command.impl.*;
import com.klasevich.cms.model.service.impl.*;

public enum CommandType {

    LOGIN {
        {
            this.command = new LoginCommand(new UserServiceImpl());
        }
    },
    ADD_SERVICE {
        {
            this.command = new AddServiceCommand(new CommandServiceImpl());
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    TAKE_AVATAR {
        {
            this.command = new TakeAvatarCommand(new CommandServiceImpl());
        }
    },
    SHOW_USER_ORDER {
        {
            this.command = new ShowUserOrderCommand(new OrderServiceImpl(), new CommandServiceImpl());
        }
    },
    CHANGE_ORDER_STATUS {
        {
            this.command = new ChangeOrderStatusCommand(new OrderServiceImpl());
        }
    },
    CHANGE_ADMIN_ORDER {
        {
            this.command = new ChangeAdminOrderCommand(new OrderServiceImpl());
        }
    },
    USER_ORDER {
        {
            this.command = new UserOrderCommand(new OrderServiceImpl());
        }
    },
    FIND_ORDER_BY_ID {
        {
            this.command = new FindOrderByIdCommand(new OrderServiceImpl(), new CommandServiceImpl());
        }
    },
    FIND_ORDERS_BY_CATEGORY {
        {
            this.command = new FindOrdersByCategoryCommand(new OrderServiceImpl());
        }
    },
    FIND_ORDERS_BY_STATUS {
        {
            this.command = new FindOrdersByStatusCommand(new OrderServiceImpl());
        }
    },
    FIND_ORDERS_AFTER_DATE {
        {
            this.command = new FindOrdersAfterDateCommand(new OrderServiceImpl());
        }
    },
    FIND_USER_BY_PARAMETER{
        {
            this.command = new FindUserByParameterCommand(new UserServiceImpl());
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand(new UserServiceImpl());
        }
    },
    CHANGE_USER {
        {
            this.command = new ChangeUserCommand(new UserServiceImpl());
        }
    },
    MAKE_ORDER {
        {
            this.command = new MakeOrderCommand(new OrderServiceImpl());
        }
    },
    SHOW_SERVICES {
        {
            this.command = new ShowServicesCommand(new CommandServiceImpl());
        }
    },
    SHOW_ORDERS {
        {
            this.command = new ShowOrdersCommand(new OrderServiceImpl());
        }
    },
    SHOW_ORDERS_TO_ADD_SERVICES {
        {
            this.command = new ShowOrdersToAddServicesCommand(new OrderServiceImpl());
        }
    },
    SHOW_USERS {
        {
            this.command = new ShowUsersCommand(new UserServiceImpl());
        }
    },
    CHANGE_SERVICE {
        {
            this.command = new ChangeServiceCommand(new CommandServiceImpl());
        }
    },
    REMOVE_SERVICE {
        {
            this.command = new RemoveServiceCommand(new CommandServiceImpl());
        }
    },
    REMOVE_SERVICE_FROM_ORDER {
        {
            this.command = new RemoveServiceFromOrderCommand(new OrderServiceImpl());
        }
    },
    REMOVE_USER {
        {
            this.command = new RemoveUserCommand(new UserServiceImpl());
        }
    },
    REMOVE_ORDER {
        {
            this.command = new RemoveOrderCommand(new OrderServiceImpl());
        }
    },
    TO_REGISTRATION {
        {
            this.command = new ToRegistrationCommand();
        }
    },
    TO_ADMIN_PROFILE {
        {
            this.command = new ToAdminProfileCommand();
        }
    },
    TO_ADD_ADMIN {
        {
            this.command = new ToAddAdminCommand();
        }
    },
    TO_ADMIN_MAIN {
        {
            this.command = new ToAdminMainCommand();
        }
    },
    TO_CHANGE_PASSWORD {
        {
            this.command = new ToChangePasswordCommand();
        }
    },
    CHANGE_PASSWORD {
        {
            this.command = new ChangePasswordCommand(new UserServiceImpl());
        }
    },
    TO_ADMIN_MANAGE_SERVICE {
        {
            this.command = new ToAdminManageServiceCommand();
        }
    },
    TO_ADMIN_MANAGE_USER {
        {
            this.command = new ToAdminManageUserCommand();
        }
    },
    TO_ADMIN_MANAGE_ORDER{
        {
            this.command = new ToAdminManageOrderCommand();
        }
    },
    ADMIN_ADD_SERVICE_TO_ORDER{
        {
            this.command = new AdminAddServiceToOrderCommand(new OrderServiceImpl(), new CommandServiceImpl());
        }
    },
    TO_USER_ORDER {
        {
            this.command = new ToUserOrderCommand();
        }
    },
    TO_CHANGE_SERVICE {
        {
            this.command = new ToChangeServiceCommand(new CommandServiceImpl());
        }
    },
    TO_MAKING_ORDER {
        {
            this.command = new ToMakingOrderCommand();
        }
    },
    TO_PROFILE {
        {
            this.command = new ToProfileCommand();
        }
    },
    TO_LOGIN {
        {

            this.command = new ToLoginCommand();
        }
    },
    TO_CHANGE_USER {
        {

            this.command = new ToChangeUserCommand();
        }
    },
    TO_DELIVERY {
        {

            this.command = new ToDeliveryCommand();
        }
    },
    TO_SERVICE {
        {
            this.command = new ToServiceCommand();
        }
    },
    SHOW_CATEGORY_SERVICES {
        {
            this.command = new ShowCategoryServicesCommand(new CommandServiceImpl());
        }
    },
    TO_CONTACTS {
        {
            this.command = new ToContactsCommand();
        }
    },
    HOME {
        {
            this.command = new HomeCommand();
        }
    },
    MAIL {
        {
            this.command = new MailCommand(new CommandServiceImpl());
        }
    },
    LOCALIZATION {
        {
            this.command = new LocalizationCommand();
        }
    };

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}
