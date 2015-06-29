package hotelvirtual.commands;

import hotelvirtual.dao.DAOFactory;
import hotelvirtual.dao.RequestDAO;
import hotelvirtual.model.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

public class MakeRequestsList implements Command {

    private static final String REQUESTS_LIST = "requestsList";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        RequestDAO dao = DAOFactory.getRequestDAO();

        List<Request> requests = dao.findAll();

        req.setAttribute(REQUESTS_LIST, requests);
    }
}
