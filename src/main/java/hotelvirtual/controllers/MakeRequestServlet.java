package hotelvirtual.controllers;

import hotelvirtual.commands.AddNewCustomer;
import hotelvirtual.commands.AddNewRequest;
import hotelvirtual.commands.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name="MakeRequestServlet", urlPatterns = {"/makerequestservlet"})
public class MakeRequestServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command cmd = new AddNewCustomer();

        cmd.execute(req, resp);

        cmd = new AddNewRequest();

        cmd.execute(req, resp);

        resp.sendRedirect("/approverequest.jsp");
    }
}
