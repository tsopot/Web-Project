package hotelvirtual.commands;

import hotelvirtual.dao.DAOFactory;
import hotelvirtual.dao.RequestDAO;
import hotelvirtual.model.Customer;
import hotelvirtual.model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewRequest implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String FORMAT = "yyyy-MM-dd";
    private static final String GUESTS = "guests";
    private static final String CHECK_IN = "check-in-date";
    private static final String CHECK_OUT = "check-out-date";
    private static final String CUSTOMER = "customer";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);

        int guests = Integer.parseInt(req.getParameter(GUESTS));
        Date checkInDate = null;
        Date checkOutDate = null;

        try {
            checkInDate = sdf.parse(req.getParameter(CHECK_IN));
            checkOutDate = sdf.parse(req.getParameter(CHECK_OUT));
        } catch (ParseException e) {
            LOGGER.error(e);
        }

        Customer customer = (Customer) req.getAttribute(CUSTOMER);

        Request request = new Request();

        request.setGuests(guests);
        request.setCheckInDate(checkInDate);
        request.setCheckOutDate(checkOutDate);
        request.setCustomer(customer);

        RequestDAO dao = DAOFactory.getRequestDAO();
        dao.add(request);
    }

}
