package hotelvirtual.commands;

import hotelvirtual.dao.CustomerDAO;
import hotelvirtual.dao.DAOFactory;
import hotelvirtual.model.Customer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddNewCustomer implements Command {

    private final static String NAME = "name";
    private final static String SURNAME = "surname";
    private final static String PASSPORT = "passportId";
    private final static String EMAIL = "email";
    private final static String GENDER = "gender";
    private final static String CUSTOMER = "customer";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter(NAME);
        String surname = req.getParameter(SURNAME);
        String passportId = req.getParameter(PASSPORT);
        String email = req.getParameter(EMAIL);
        boolean isManEng = req.getParameter(GENDER).equals("Male");
        boolean isManUkr = req.getParameter(GENDER).equals("„олов≥ча");
        String genderValue = (isManEng || isManUkr) ? "Male" : "Female";
        Customer.Gender gender = Customer.Gender.valueOf(genderValue);

        Customer customer = new Customer();

        customer.setName(name);
        customer.setSurname(surname);
        customer.setPassportId(passportId);
        customer.setEmail(email);
        customer.setGender(gender);

        CustomerDAO dao = DAOFactory.getCustomerDAO();

        dao.add(customer);

        req.setAttribute(CUSTOMER, customer);
    }
}
