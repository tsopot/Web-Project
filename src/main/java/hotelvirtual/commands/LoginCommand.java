package hotelvirtual.commands;

import hotelvirtual.dao.DAOFactory;
import hotelvirtual.model.User;
import hotelvirtual.dao.UserDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String USER = "user";
    private static final String PASSWORD = "password";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        
        String login = req.getParameter(USER);
        String password = req.getParameter(PASSWORD);
        
        User user = new User();

        user.setLogin(login);
        user.setPassword(password);

        UserDAO userDAO = DAOFactory.getUserDAO();
        
            if (userDAO.findUser(user)) {

                HttpSession httpSession = req.getSession(true);
                httpSession.setAttribute(USER, login);
            } else {
                LOGGER.warn("Invalid attempt to login as administrator");
            }
    }

}
