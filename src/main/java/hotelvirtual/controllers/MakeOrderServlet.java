package hotelvirtual.controllers;

import hotelvirtual.commands.Command;
import hotelvirtual.commands.MakeOrder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet(name="MakeOrderServlet", urlPatterns = {"/makeorder"})
public class MakeOrderServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command cmd = new MakeOrder();
        cmd.execute(req, resp);

        resp.sendRedirect("/administrator.jsp");

    }
}
