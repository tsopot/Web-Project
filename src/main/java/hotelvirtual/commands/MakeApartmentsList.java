package hotelvirtual.commands;

import hotelvirtual.dao.ApartmentDAO;
import hotelvirtual.dao.DAOFactory;
import hotelvirtual.model.Apartment;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

public class MakeApartmentsList implements Command {

    private static final String APARTMENTS_LIST = "apartmentsList";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {

        ApartmentDAO dao = DAOFactory.getApartmentDAO();

        List<Apartment> apps = dao.findAll();

        req.setAttribute(APARTMENTS_LIST, apps);
    }
}
