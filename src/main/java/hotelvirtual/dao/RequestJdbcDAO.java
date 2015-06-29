package hotelvirtual.dao;

import hotelvirtual.model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestJdbcDAO implements RequestDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    private final static String FIND = "SELECT * FROM requests WHERE id = ?;";
    private final static String FIND_ALL = "SELECT * FROM requests;";
    private final static String INSERT = "INSERT INTO requests(customer_id, guests, check_in_date, check_out_date) VALUES(?, ?, ?, ?);";
    private final static String DELETE = "DELETE FROM requests WHERE id = ?;";

    private final static String ID = "id";
    private final static String CUSTOMER_ID = "customer_id";
    private final static String GUESTS = "guests";
    private final static String CHECK_IN = "check_in_date";
    private final static String CHECK_OUT = "check_out_date";

    private static DataSource ds;

    public RequestJdbcDAO(DataSource ds) { this.ds = ds; }

    @Override
    public List<Request> findAll() {
        List<Request> result = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL)) {

            try (ResultSet rs = ps.executeQuery()){
                Request request;

                while (rs.next()) {
                    request = new Request();

                    request.setId(rs.getObject(ID, Integer.class));
                    CustomerDAO dao = DAOFactory.getCustomerDAO();
                    request.setCustomer(dao.findById(rs.getObject(CUSTOMER_ID, Integer.class)));
                    request.setGuests(rs.getObject(GUESTS, Integer.class));
                    request.setCheckInDate(rs.getDate(CHECK_IN));
                    request.setCheckOutDate(rs.getDate(CHECK_OUT));

                    result.add(request);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return result;
    }

    @Override
    public Request findById(int id) {
        Request result = new Request();

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND)) {

            ps.setObject(1, id);


            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result.setId(rs.getObject(ID, Integer.class));
                    CustomerDAO dao = DAOFactory.getCustomerDAO();
                    result.setCustomer(dao.findById(rs.getObject(CUSTOMER_ID, Integer.class)));
                    result.setGuests(rs.getObject(GUESTS, Integer.class));
                    result.setCheckInDate(rs.getDate(CHECK_IN));
                    result.setCheckOutDate(rs.getDate(CHECK_OUT));
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return result;
    }

    @Override
    public void add(Request request) {

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {

            ps.setObject(1, request.getCustomer().getId());
            ps.setObject(2, request.getGuests());
            ps.setObject(3, request.getCheckInDate());
            ps.setObject(4, request.getCheckOutDate());

            ps.execute();

        } catch (SQLException e) {
            LOGGER.error(e);
        }

    }

    @Override
    public void deleteById(int id) {

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE)) {

            ps.setObject(1, id);

            ps.execute();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
