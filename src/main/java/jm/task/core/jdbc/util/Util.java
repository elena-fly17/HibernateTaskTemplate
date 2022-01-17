package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase1";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root17";
    private static Connection connection;

    public static Connection dbOpenConnectionJDBC () {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Не удалось загрузить драйвер");
        }
        return connection;
    }

    public static void dbCloseConnectionJDBC () {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Не удалось закрыть соединение");
        }
    }
}
