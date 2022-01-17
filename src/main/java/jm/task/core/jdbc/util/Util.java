package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase1";
    private static final String USERNAME = "root17";
    private static final String PASSWORD = "root17";
    private static Connection connection;

    public static Connection dbOpenConnection () {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Не удалось загрузить драйвер");
        }
        return connection;
    }

    public static void dbCloseConnection () {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Не удалось закрыть соединение");
        }
    }
}
