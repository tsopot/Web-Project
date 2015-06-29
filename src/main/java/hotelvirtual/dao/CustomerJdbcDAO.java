package hotelvirtual.dao;

import hotelvirtual.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerJdbcDAO implements CustomerDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    private final static String FIND = "SELECT * FROM customers WHERE id = ?;";
    private final static String FIND_ALL = "SELECT * FROM customers;";
    private final static String INSERT = "INSERT INTO customers(name, surname, gender, passport_id, email) VALUES (?, ?, ?, ?, ?);";

    private final static String ID = "id";
    private final static String NAME = "name";
    private final static String SURNAME = "surname";
    private final static String GENDER = "gender";
    private final static String PASSPORT = "passport_id";
    private final static String EMAIL = "email";

    private static DataSource ds;

    public CustomerJdbcDAO(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> result = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL)) {

            try (ResultSet rs = ps.executeQuery()){
                Customer customer;

                while (rs.next()) {
                    customer = new Customer();

                    customer.setId(rs.getObject(ID, Integer.class));
                    customer.setName(rs.getObject(NAME, String.class));
                    customer.setSurname(rs.getObject(SURNAME, String.class));
                    customer.setGender(Customer.Gender.valueOf(rs.getObject(GENDER, String.class)));
                    customer.setPassportId(rs.getObject(PASSPORT, String.class));
                    customer.setEmail(rs.getObject(EMAIL, String.class));

                    result.add(customer);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return result;
    }

    @Override
    public Customer findById(int id) {
        Customer result = new Customer();

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND)) {

            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result.setId(rs.getObject(ID, Integer.class));
                    result.setName(rs.getObject(NAME, String.class));
                    result.setSurname(rs.getObject(SURNAME, String.class));
                    result.setGender(Customer.Gender.valueOf(rs.getObject(GENDER, String.class)));
                    result.setPassportId(rs.getObject(PASSPORT, String.class));
                    result.setEmail(rs.getObject(EMAIL, String.class));
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return result;
    }

    @Override
    public void add(Customer customer) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {

            ps.setObject(1, customer.getName());
            ps.setObject(2, customer.getSurname());
            ps.setObject(3, customer.getGender().toString());
            ps.setObject(4, customer.getPassportId());
            ps.setObject(5, customer.getEmail());

            ps.execute();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    customer.setId(id);
                } else {
                    LOGGER.warn("Problem with a customer insertion");
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

}
