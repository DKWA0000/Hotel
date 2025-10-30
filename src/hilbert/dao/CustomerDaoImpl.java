package hilbert.dao;

import hilbert.connector.MysqlConnector;
import hilbert.model.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    public List<Customer> getAllCustomers() {
        List<Customer> list = new ArrayList<Customer>();
        String sql = "SELECT * FROM customer";
        try (Connection conn = MysqlConnector.getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)
        ) {
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("city"));
                list.add(customer);
            }

        } catch (SQLException e) {
            System.out.println("Failed to fetch customer - " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void addCustomer(Customer c) {
        String sql = "INSERT INTO customer(name, email, city) VALUES ("
                + "'" + c.getName() + "', "
                + "'" + c.getEmail() + "', "
                + "'" + c.getCity()
                + "');";

        try (Connection conn = MysqlConnector.getConnection();
             Statement statement = conn.createStatement();
        ) {
            int rs = statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Failed to insert customer " + c.getName() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateCity(int id, String newCity) {
        String sql = "UPDATE customer " +
                "SET city = '" + newCity + "' " +
                "WHERE id = " + id + ";";
        try (Connection conn = MysqlConnector.getConnection();
             Statement statement = conn.createStatement()) {
            int rs = statement.executeUpdate(sql);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Failed to update customers city (customer id: " + id + ") - " + e.getMessage());
        }
    }


    public List<Customer> findCustomerByEmail(String email) {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customer WHERE email = '" + email + "';";
        try (Connection conn = MysqlConnector.getConnection();
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("city"));
                list.add(customer);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void deleteCustomer(int id){
        String sql = "DELETE FROM customer WHERE id = "+id+";";
        try(Connection conn = MysqlConnector.getConnection();
            Statement statement = conn.createStatement() ){
            int rs = statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Could not delete customer with id " + id + " - " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}