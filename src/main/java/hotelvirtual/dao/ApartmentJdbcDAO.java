package hotelvirtual.dao;

import hotelvirtual.model.Apartment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartmentJdbcDAO implements ApartmentDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    private final static String FIND = "SELECT * FROM apartments WHERE id = ?;";
    private final static String FIND_ALL = "SELECT * FROM apartments;";
    private final static String INSERT = "INSERT INTO apartments(type, wifi, tv_set, air_condition, view, price_per_night) VALUES(?, ?, ?, ?, ?, ?);";

    private final static String ID = "id";
    private final static String TYPE = "type";
    private final static String WIFI = "wifi";
    private final static String TV = "tv_set";
    private final static String AIR_CONDITION = "air_condition";
    private final static String VIEW = "view";
    private final static String PRICE = "price_per_night";

    private static DataSource ds;

    public ApartmentJdbcDAO(DataSource ds) {
        this.ds = ds;
    }


    @Override
    public List<Apartment> findAll() {
        List<Apartment> result = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND_ALL)) {

            try (ResultSet rs = ps.executeQuery()) {
                Apartment apartment;

                while (rs.next()) {
                    apartment = new Apartment();

                    apartment.setId(rs.getObject(ID, Integer.class));
                    apartment.setType(Apartment.Type.valueOf(rs.getObject(TYPE, String.class)));
                    apartment.setWifi(rs.getBoolean(WIFI));
                    apartment.setTvSet(rs.getBoolean(TV));
                    apartment.setAirCondition(rs.getBoolean(AIR_CONDITION));
                    apartment.setView(Apartment.View.valueOf(rs.getObject(VIEW, String.class)));
                    apartment.setPricePerNight(rs.getObject(PRICE, Integer.class));

                    result.add(apartment);
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return result;
    }

    @Override
    public Apartment findById(int id) {
        Apartment result = new Apartment();

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND)) {
            ps.setObject(1,id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result.setId(rs.getObject(ID, Integer.class));
                    result.setType(Apartment.Type.valueOf(rs.getObject(TYPE, String.class)));
                    result.setWifi(rs.getBoolean(WIFI));
                    result.setTvSet(rs.getBoolean(TV));
                    result.setAirCondition(rs.getBoolean(AIR_CONDITION));
                    result.setView(Apartment.View.valueOf(rs.getObject(VIEW, String.class)));
                    result.setPricePerNight(rs.getObject(PRICE, Integer.class));
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return result;
    }

    @Override
    public void add(Apartment apartment) {

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT)) {

            ps.setObject(1, apartment.getType());
            ps.setObject(2, apartment.isWifi());
            ps.setObject(3, apartment.isTvSet());
            ps.setObject(4, apartment.isAirCondition());
            ps.setObject(5, apartment.getView());
            ps.setObject(6, apartment.getPricePerNight());

            ps.execute();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
