package hotelvirtual.dao;

import hotelvirtual.model.Request;

import java.util.List;

public interface RequestDAO {
    List<Request> findAll();
    Request findById(int id);
    void add(Request request);
    void deleteById(int id);
}
