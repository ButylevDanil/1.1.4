package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("francisk", "klio", (byte) 33);
        userService.saveUser("gradno", "where", (byte) 24);
        userService.saveUser("silva", "ntoh", (byte) 89);
        userService.saveUser("revron", "chopor", (byte) 56);
        userService.removeUserById(2);
        User user = userService.getAllUsers().get(0);
        System.out.println(user);
        User user1 = userService.getAllUsers().get(1);
        System.out.println(user1);
        User user2 = userService.getAllUsers().get(2);
        System.out.println(user2);

    }
}
