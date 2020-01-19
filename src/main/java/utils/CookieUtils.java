package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class CookieUtils {

    public String getCookieValue(HttpServletRequest req, String cookieName) {
        return req.getCookies() == null ? null :
                Arrays.stream(req.getCookies())
                        .filter(c -> c.getName().equals(cookieName))
                        .findFirst()
                        .map(Cookie::getValue)
                        .orElse(null);
    }
}
