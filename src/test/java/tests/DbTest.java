package tests;

import org.testng.annotations.Test;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbTest {

    @Test
    public void testQuery() throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM information_schema.sql_parts");

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        }
    }
}
