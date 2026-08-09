package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = PropertyReader.getProperty("saucedemo.datasource.url");
    private static final String USERNAME = PropertyReader.getProperty("saucedemo.datasource.username");
    private static final String PASSWORD = PropertyReader.getProperty("saucedemo.datasource.password");

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
