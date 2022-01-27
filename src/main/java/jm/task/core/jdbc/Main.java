package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Анна", "Иванова", (byte) 34);
        userService.saveUser("Виктория", "Грибова", (byte) 21);
        userService.saveUser("Антон", "Владимиров", (byte) 45);
        userService.saveUser("Александр", "Сидоров", (byte) 22);

        List<User> listOfUsers = userService.getAllUsers();
        for (User us : listOfUsers) {
           System.out.println(us);
        }

        userService.removeUserById(2);

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
