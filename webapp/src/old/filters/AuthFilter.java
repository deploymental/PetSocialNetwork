/*
package old.filters;

import com.getjavajob.AccountDAO;
import com.getjavajob.AccountService;
import com.getjavajob.common.Account;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class AuthFilter implements Filter {
    private static final String COOKIE_PARAMETER = "cookie";
    private static String authCookie;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        authCookie = filterConfig.getServletContext().getInitParameter(COOKIE_PARAMETER);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getSession().getAttribute("login") == null) {
            String email = cookieValue(req);
            AccountService as = new AccountService(new AccountDAO());
            Account ac;
            try {
                ac = as.getAccount(email);
            } catch (SQLException e) {
                resp.sendRedirect(req.getServletContext().getContextPath() + "/");
            }
            req.getSession().setAttribute("login", email);

        }
    }

    private String cookieValue(HttpServletRequest req) {
        List<Cookie> list = Arrays.asList(req.getCookies());
        Cookie cookie = list.stream()
                .filter(x -> authCookie.equals((x.getName())))
                .findAny().orElse(null);
        return cookie.getValue();
    }


    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        Object loginAttribute = session.getAttribute("login");
        if (loginAttribute != null) {
            return true;
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().startsWith("login")) {
                    String login = cookie.getName().substring(5);
                    request.getSession().setAttribute("login", login);
                    return true;
                }
            }
        }
        return true;
    }
}
*/
