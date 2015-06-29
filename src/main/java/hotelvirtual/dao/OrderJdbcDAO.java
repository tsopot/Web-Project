package hotelvirtual.dao;

import hotelvirtual.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderJdbcDAO implements OrderDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    private final static String FIND = "SELECT * FROM orders WHERE id = ?;";
    private final static String FIND_ALL = "SELECT * FROM orders;";
    private final static String INSERT = "INSERT INTO orders(customer_id, apartment_id, check_in_date, check_out_date, price) VALUES(?, ?, ?, ?, ?);";

    private final static String ID = "id";
    private final static String CUSTOMER_ID = "customer_id";
    private final static String APARTMENT_ID = "apartment_id";
    private final static String CHECK_IN = "check_in_date";
    private final static String CHECK_OUT = "check_out_date";
    private final static String PRICE = "price";

    private static DataSource ds;

    public OrderJdbcDAO(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<Order> findAll() {
        List<Order> result = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL)) {

            try (ResultSet rs = ps.executeQuery()) {
                Order order;

                while (rs.next()) {
                    order = new Order();

                    order.setId(rs.getObject(ID, Integer.class));
                    CustomerDAO dao = DAOFactory.getCustomerDAO();
                    order.setCustomer(dao.findById(rs.getObject((CUSTOMER_ID), Integer.class)));
                    order.setApartmentId(rs.getObject(APARTMENT_ID, Integer.class));
                    order.setCheckInDate(rs.getDate(CHECK_IN));
                    order.setCheckOutDate(rs.getDate(CHECK_OUT));
                    order.setPrice(rs.getObject(PRICE, Integer.class));

                    result.add(order);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return result;
    }

    @Override
    public Order findById(int id) {
        Order result = new Order();

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND)) {

            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                result.setId(rs.getObject(ID, Integer.class));
                CustomerDAO dao = DAOFactory.getCustomerDAO();
                result.setCustomer(dao.findById(rs.getObject((CUSTOMER_ID), Integer.class)));
                result.setApartmentId(rs.getObject(APARTMENT_ID, Integer.class));
                result.setCheckInDate(rs.getDate(CHECK_IN));
                result.setCheckOutDate(rs.getDate(CHECK_OUT));
                result.setPrice(rs.getObject(PRICE, Integer.class));
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return result;
    }

    @Override
    public void add(Order order) {

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {

            ps.setObject(1, order.getCustomer().getId());
            ps.setObject(2, order.getApartmentId());
            ps.setObject(3, order.getCheckInDate());
            ps.setObject(4, order.getCheckOutDate());
            ps.setObject(5, order.getPrice());

            ps.execute();

        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }
}
