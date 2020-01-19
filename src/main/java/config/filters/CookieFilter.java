package config.filters;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.GenericFilterBean;
import utils.CookieUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(Ordered.HIGHEST_PRECEDENCE)
public class CookieFilter extends GenericFilterBean {

    private CookieUtils cookieUtils = new CookieUtils();


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        if (validateRequest(httpServletRequest)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        chain.doFilter(httpServletRequest, httpServletResponse);
    }

    private boolean validateRequest(HttpServletRequest httpServletRequest) {
        return cookieUtils.getCookieValue(httpServletRequest, "NewUniversalCookie") == null
                && httpServletRequest.getRequestURL().toString().contains("mortgages");
    }

}
