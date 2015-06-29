package hotelvirtual.commands;

import hotelvirtual.dao.DAOFactory;
import hotelvirtual.dao.OrderDAO;
import hotelvirtual.model.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

public class MakeOrdersList implements Command {

    private static final String ORDERS_LIST = "ordersList";

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) {
        OrderDAO dao = DAOFactory.getOrderDAO();

        List<Order> orders = dao.findAll();

        req.setAttribute(ORDERS_LIST, orders);
    }

}
