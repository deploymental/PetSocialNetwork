/*
package old;

import com.getjavajob.AccountDAO;
import com.getjavajob.AccountService;
import com.getjavajob.common.Account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class LoginController extends HttpServlet {
    private static final int COOKIE_LIFETIME = (int) TimeUnit.DAYS.toSeconds(30);
    */
/* AccountDAO acc;
     List<Account> acs;
     private String name;

     //config
     public void init(ServletConfig sc) {
         name = sc.getInitParameter("name");
         acc = new AccountDAO("HerokuProperties");

     }*//*

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        */
/*File fi = new File("/resources/custom/img/trooper.jpg");
        byte[] fileContent = Files.readAllBytes(fi.toPath());
        String base64DataString = new String(fileContent , "UTF-8");
        req.setAttribute("preview", base64DataString);*//*

        String email = (String) req.getSession().getAttribute("login");
        if(email == null){
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
            rd.forward(req, resp);
        } else {
            AccountService as = new AccountService(new AccountDAO());
            Account acc = null;
            try {
                acc = as.getAccount(email);
            } catch (SQLException e) {
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
                rd.forward(req, resp);
            }
            req.setAttribute("homePageOwner", acc);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/account/accountDetail.jsp");
            rd.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");
        String password = req.getParameter("pass");
        AccountService as = new AccountService(new AccountDAO());
        Account acc = null;
        if (email.isEmpty() || password.isEmpty()) {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
            out.println("<font color=red>Fill all fields</font>");
            rd.include(req, resp);
        } else {
            try {
                acc = as.getAccount(email);
                if (acc.getPassword().equals(password)) {
                    req.getSession().setAttribute("login", email);
                    req.setAttribute("homePageOwner", acc);
                    if(req.getParameter("remember") != null){
                        Cookie cookie = new Cookie("cookie", email);
                        cookie.setMaxAge(COOKIE_LIFETIME);
                        resp.addCookie(cookie);
                    }
                    RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/account/accountDetail.jsp");
                    rd.forward(req, resp);
                } else {
                    throw new NullPointerException();
                }
            } catch (NullPointerException | SQLException e) {
                */
/*req.getSession().setAttribute("login", email);
                req.setAttribute("homePageOwner", acc);
                e.printStackTrace();
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/account/accountDetail.jsp");
                rd.forward(req, resp);*//*

                //out.println(e);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
                rd.include(req, resp);

            }
        }
    }
}*/
