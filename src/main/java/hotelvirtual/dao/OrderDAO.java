package hotelvirtual.dao;

import hotelvirtual.model.Order;
import java.util.List;

public interface OrderDAO {
    List<Order> findAll();
    Order findById(int id);
    void add(Order order);
}
