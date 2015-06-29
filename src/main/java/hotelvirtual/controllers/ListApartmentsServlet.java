package hotelvirtual.controllers;

import hotelvirtual.commands.Command;
import hotelvirtual.commands.MakeApartmentsList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet(name="ListApartmentsServlet", urlPatterns = {"/listapartmentsservlet"})
public class ListApartmentsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command cmd = new MakeApartmentsList();
        cmd.execute(req, resp);
    }
}
