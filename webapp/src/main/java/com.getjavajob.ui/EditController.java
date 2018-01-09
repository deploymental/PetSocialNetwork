package com.getjavajob.ui;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("type") != null) {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/edit/employee/accountEditForm.jsp");
            rd.forward(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/edit/department/groupEditForm.jsp");
            rd.forward(req, resp);
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        AccountService as = new AccountService(new AccountDAO());
        Account acc = null;
        try {
            acc = as.getAccount((String) req.getSession().getAttribute("accEmail"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String firstName = req.getParameter("fname");
        String surName = req.getParameter("lname");
        String middleName = req.getParameter("mname");
        //byte[] picture = (req.getParameter("image")).getBytes();
        String homeAddress = req.getParameter("homeAddress");
        String workAddress = req.getParameter("workAddress");
        String icq = req.getParameter("icq");
        String skype = req.getParameter("skype");
        //String phone = req.getParameter("phone");
        String additionalInfo = req.getParameter("additionalInfo");
        String email = req.getParameter("email");
        String password = req.getParameter("pass");
        if (firstName != null && !firstName.isEmpty()) {
            acc.setName(firstName);
        }
        if (surName != null && !surName.isEmpty()) {
            acc.setSurName(surName);
        }
        if (middleName != null && !middleName.isEmpty()) {
            acc.setMiddleName(middleName);
        }
        if (homeAddress != null && !homeAddress.isEmpty()) {
            acc.setHomeAddress(homeAddress);
        }
        if (workAddress != null && !workAddress.isEmpty()) {
            acc.setWorkAddress(workAddress);
        }
        if (icq != null && !icq.isEmpty()) {
            acc.setIcq(Integer.parseInt(icq));
        }
        if (skype != null && !skype.isEmpty()) {
            acc.setSkype(skype);
        }
        if (additionalInfo != null && !additionalInfo.isEmpty()) {
            acc.setAdditionalInfo(skype);
        }
        if (email != null && !email.isEmpty()) {
            acc.setEmail(skype);
        }
        if (password != null && !password.isEmpty()) {
            acc.setPassword(skype);
        }

        try {
            as.editAccount(acc);
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/account/accountDetail.jsp");
            rd.include(req, resp);
        } catch (SQLException e) {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/edit/employee/accountEditForm.jsp");
            out.println("<font color=red>Something goes wrong</font>");
            rd.include(req, resp);
        }*/
    }
}
