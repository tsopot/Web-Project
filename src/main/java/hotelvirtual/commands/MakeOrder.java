package hotelvirtual.commands;

import hotelvirtual.dao.*;
import hotelvirtual.email.SendEmail;
import hotelvirtual.model.Apartment;
import hotelvirtual.model.Customer;
import hotelvirtual.model.Order;
import hotelvirtual.model.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.MessagingException;

import java.util.Calendar;

public class MakeOrder implements Command {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String REQUEST_ID = "requestId";
    private static final String APARTMENT_ID = "apartmentId";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        int requestId = Integer.parseInt(req.getParameter(REQUEST_ID));
        int apartmentId = Integer.parseInt(req.getParameter(APARTMENT_ID));

        ApartmentDAO appDao = DAOFactory.getApartmentDAO();
        RequestDAO reqDao = DAOFactory.getRequestDAO();
        OrderDAO ordDao = DAOFactory.getOrderDAO();

        Request request = reqDao.findById(requestId);
        Apartment apartment = appDao.findById(apartmentId);
        Customer customer = request.getCustomer();
        Order order = new Order();

        Calendar calCI = Calendar.getInstance();
        Calendar calCO = Calendar.getInstance();
        calCI.setTime(request.getCheckInDate());
        calCO.setTime(request.getCheckOutDate());

        int nights = (int) ((calCO.getTimeInMillis() - calCI.getTimeInMillis()) / 86400000);

        order.setCheckOutDate(request.getCheckOutDate());
        order.setCheckInDate(request.getCheckInDate());
        order.setCustomer(customer);
        order.setApartmentId(apartmentId);
        order.setPrice(apartment.getPricePerNight() * nights);

        ordDao.add(order);

        reqDao.deleteById(requestId);

        SendEmail sa = new SendEmail();
        String toMail = customer.getEmail();
        String body = "<html><body>Price for the requested period in our hotel is " + order.getPrice() + "</body></html>";

        try {
            sa.SendingEmail(toMail, body);
        } catch (MessagingException e) {
            LOGGER.error(e);
        }
    }
}
