package hotelvirtual.dao;

import hotelvirtual.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class UserJdbcDAO implements UserDAO {

    private static final Logger LOGGER = LogManager.getLogger();

    private final static String FIND = "SELECT COUNT(*) FROM users WHERE username = ? AND password = ?;";

    private DataSource ds;

    public UserJdbcDAO(DataSource ds) {
        this.ds = ds;
    }
    
    public boolean findUser(User user) {
        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(FIND)) {

            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());

            try (ResultSet rs = ps.executeQuery()) {
                return (rs.next());
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return false;
    }
    
}
