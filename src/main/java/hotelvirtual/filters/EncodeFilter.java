package hotelvirtual.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class EncodeFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        LOGGER.info("EncodingFilter was initiated.");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding(ENCODING);
        resp.setCharacterEncoding(ENCODING);
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        LOGGER.info("EncodingFilter was destroyed.");
    }
}
