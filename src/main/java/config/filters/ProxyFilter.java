package config.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import utils.CookieUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class ProxyFilter extends ZuulFilter {

    private CookieUtils cookieUtils = new CookieUtils();

    private static Logger log = LoggerFactory.getLogger(ProxyFilter.class);

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        ctx.addZuulRequestHeader("Hello", cookieUtils.getCookieValue(request, "NewUniversalCookie"));
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
        return null;
    }


}
