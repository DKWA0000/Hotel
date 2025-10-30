package hilbert;

import hilbert.connector.MysqlConnector;

import java.sql.*;

public class HotelDBInitializer {
    private static MysqlConnector dbConnector;
    private static volatile HotelDBInitializer instance;
    private static int count;

    private HotelDBInitializer() {
        dbConnector = new MysqlConnector();
        run();
    }

    public static HotelDBInitializer getInstance() {
        if (instance == null) {
            synchronized (HotelDBInitializer.class) {
                if (instance == null) {
                    instance = new HotelDBInitializer();
                    count = 0;
                }
            }
        }
        return instance;
    }

    public void run(){
        try {
            createDatabaseIfNotExists();
            createTablesIfNotExist();
            if(count == 0){
                insertSampleData();
            }
            selectAll();
            System.out.println("HotelDB setup completed successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void selectAll() throws SQLException {
        try (Connection conn = MysqlConnector.getConnection()) {

            String createCustomer = """
                SELECT * FROM customer;
                """;

            String createRoom = """
                SELECT * FROM room;
                """;

            String createBooking = """
                SELECT * FROM booking;
                """;

            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(createCustomer)) {
                //Customer c = null
                while (rs.next()) {
//                    c = new Customer(
//                            rs.getInt("id"),
//                            rs.getString("name"),
//                            rs.getString("email"),
//                            rs.getString("city")
//                    );
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String email= rs.getString("email");
                    String city = rs.getString("city");
                    System.out.println(name + email + city);
//                    System.out.println(c.toString());
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createDatabaseIfNotExists() throws SQLException {
        try (Connection conn = dbConnector.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS HotelDB");
            System.out.println("Database checked/created: HotelDB");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void createTablesIfNotExist() throws SQLException, ClassNotFoundException {
        try (Connection conn = MysqlConnector.getConnection()) {

            String createCustomer = """
                CREATE TABLE IF NOT EXISTS customer (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    email VARCHAR(100) UNIQUE NOT NULL,
                    city VARCHAR(100)
                )
                """;

            String createRoom = """
                CREATE TABLE IF NOT EXISTS room (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    roomnumber VARCHAR(10) NOT NULL,
                    type VARCHAR(10),
                    price DECIMAL(10,2)
                )
                """;

            String createBooking = """
                CREATE TABLE IF NOT EXISTS booking (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    customer_id INT,
                    room_id INT,
                    checkin_date DATE,
                    checkout_date DATE,
                    status VARCHAR(10),
                    FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE,
                    FOREIGN KEY (room_id) REFERENCES room(id) ON DELETE CASCADE
                )
                """;

            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate(createCustomer);
                stmt.executeUpdate(createRoom);
                stmt.executeUpdate(createBooking);
            }
        }
    }

    private static void insertSampleData() throws SQLException, ClassNotFoundException {

        try (Connection conn = MysqlConnector.getConnection()) {

            // Insert default customers
            String insertCustomer = "INSERT INTO customer (name, email, city) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertCustomer)) {

                ps.setString(1, "Anna Karlsson");
                ps.setString(2, "anna@example.com");
                ps.setString(3, "Stockholm");
                ps.executeUpdate();

                ps.setString(1, "Erik Svensson");
                ps.setString(2, "erik@example.com");
                ps.setString(3, "Göteborg");
                ps.executeUpdate();

                System.out.println("Sample customers inserted.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Insert sample rooms
            String insertRoom = "INSERT INTO room (roomnumber, type, price) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertRoom)) {

                ps.setString(1, "101A");
                ps.setString(2, "Single");
                ps.setDouble(3, 950.00);
                ps.executeUpdate();

                ps.setString(1, "202B");
                ps.setString(2, "Double");
                ps.setDouble(3, 1350.00);
                ps.executeUpdate();

                System.out.println("Sample rooms inserted.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Insert sample bookings
            String insertBooking = "INSERT INTO booking (customer_id, room_id, checkin_date, checkout_date, status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertBooking)) {

                ps.setInt(1, 1);
                ps.setInt(2, 1);
                ps.setDate(3, Date.valueOf("2025-10-23"));
                ps.setDate(4, Date.valueOf("2025-10-25"));
                ps.setString(5, "active");
                ps.executeUpdate();

                ps.setInt(1, 2);
                ps.setInt(2, 2);
                ps.setDate(3, Date.valueOf("2025-10-24"));
                ps.setDate(4, Date.valueOf("2025-10-26"));
                ps.setString(5, "active");
                ps.executeUpdate();

                System.out.println("Sample bookings inserted.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
