package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl uslmpl = new UserServiceImpl();
        uslmpl.createUsersTable();

        uslmpl.saveUser("Юлия", "Белова", (byte) 25);
        uslmpl.saveUser("Виталий", "Воронин", (byte) 50);
        uslmpl.saveUser("Виктория", "Попова", (byte) 37);
        uslmpl.saveUser("Андрей", "Кузичев",(byte) 48);

        uslmpl.getAllUsers();
        uslmpl.cleanUsersTable();
        uslmpl.dropUsersTable();













    }
}
