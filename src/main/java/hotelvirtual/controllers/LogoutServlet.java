package hotelvirtual.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;


//@WebServlet(name="LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet{

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException {

        HttpSession session = req.getSession();

        if (session.getAttribute("user") != null) {
            session.setAttribute("user", null);
            session.invalidate();
        }

        try {
            resp.sendRedirect("/index.jsp");
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

}
