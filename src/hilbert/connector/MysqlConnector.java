package hilbert.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/HotelDB";
    private static final String USER = System.getenv("USER");
    private static final String PASSWORD = System.getenv("PASSWORD");

    public static Connection getConnection() throws SQLException, ClassNotFoundException {

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void createDatabaseFresh() throws SQLException {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Drop from root connection (not from inside hotelDB)
            stmt.executeUpdate("DROP DATABASE IF EXISTS hotelDB");
            stmt.executeUpdate("CREATE DATABASE hotelDB");
        }
    }
}
