package hilbert.dao;

import hilbert.model.Customer;
import java.util.List;

public interface CustomerDao {
    List<Customer> getCustomersWithoutBookings();
}