package old;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class LogoutController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session != null) {
            Cookie cookie = new Cookie((String) session.getAttribute("login"), "");
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
            session.invalidate();
        }
        req.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(req, resp);
    }
}
