package hotelvirtual.controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hotelvirtual.commands.Command;
import hotelvirtual.commands.LoginCommand;

import java.io.IOException;

//@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command cmd = new LoginCommand();
        cmd.execute(req, resp);

        resp.sendRedirect("/administrator.jsp");
    }

}