package com.getjavajob.ui;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HeaderController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*PrintWriter out = resp.getWriter();
        String search = req.getParameter("search");
        AccountService as = new AccountService(new AccountDAO());
        Account acc = null;
        try {
            acc = as.getAccount(search);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (acc != null) {
            req.setAttribute("email", search);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/home.jsp");
            rd.forward(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/home.jsp");
            rd.forward(req, resp);
        }*/

        String name = req.getParameter("value");
        //System.out.println(name);
        if (name.equals("groups")) {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/group/list.jsp");
            rd.forward(req, resp);
        } else if (name.equals("friends")) {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/account/list.jsp");
            rd.forward(req, resp);
        } else if (name.equals("messages")) {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/chatCap.jsp");
            rd.forward(req, resp);
        }
    }
}