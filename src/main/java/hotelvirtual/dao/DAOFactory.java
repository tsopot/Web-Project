package hotelvirtual.dao;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DAOFactory {

    private static final String URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static DataSource ds;

    static {
        PoolProperties p = new PoolProperties();
        p.setUrl(URL);
        p.setDriverClassName(DRIVER);
        p.setUsername(USERNAME);
        p.setPassword(PASSWORD);

        ds = new org.apache.tomcat.jdbc.pool.DataSource();
        ds.setPoolProperties(p);
    }

    public static CustomerDAO getCustomerDAO() {
        return new CustomerJdbcDAO(ds);
    }

    public static ApartmentDAO getApartmentDAO() { return new ApartmentJdbcDAO(ds); }

    public static OrderDAO getOrderDAO() { return new OrderJdbcDAO(ds); }

    public static RequestDAO getRequestDAO() { return new RequestJdbcDAO(ds); }

    public static UserDAO getUserDAO() { return new UserJdbcDAO(ds); }
}
