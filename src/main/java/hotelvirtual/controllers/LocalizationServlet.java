package hotelvirtual.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

import java.io.IOException;

import java.util.Locale;

//@WebServlet(name = "LocaleServlet", urlPatterns = {"/localization"})
public class LocalizationServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String LOCALE = "locale";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        handleRequest(req, resp);
    }

    private void handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String localeName = req.getParameter(LOCALE);

        if (localeName != null) {
            Locale locale = Locale.forLanguageTag(localeName);

            if (locale != null) {
                Config.set(req.getSession(), Config.FMT_LOCALE, locale);
                req.getSession().setAttribute(LOCALE, locale);
            }
        }

        try {
            resp.sendRedirect("/index.jsp");
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

}
