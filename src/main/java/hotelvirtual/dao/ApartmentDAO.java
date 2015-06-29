package hotelvirtual.dao;

import hotelvirtual.model.Apartment;
import java.util.List;

public interface ApartmentDAO {
    List<Apartment> findAll();
    Apartment findById(int id);
    void add(Apartment apartment);
}
