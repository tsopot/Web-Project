package hotelvirtual.dao;

import hotelvirtual.model.Customer;
import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();
    Customer findById(int id);
    void add(Customer customer);
}
