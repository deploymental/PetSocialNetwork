package com.getjavajob.ui;

import com.getjavajob.AccountDAO;
import com.getjavajob.AccountService;
import com.getjavajob.PhoneDAO;
import com.getjavajob.PhoneService;
import com.getjavajob.common.Account;
import com.getjavajob.common.Phone;
import com.getjavajob.common.enums.Sex;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.commons.io.IOUtils.toByteArray;

@MultipartConfig
public class RegistrationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("new", true);
        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/edit/employee/accountEditForm.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        //req.setAttribute("new", true);
        PrintWriter out = resp.getWriter();
        String firstName = req.getParameter("fname");
        String surName = req.getParameter("lname");
        String middleName = req.getParameter("mname");
        String gender = req.getParameter("gender");

        byte[] image = null;
        Part imagePart = req.getPart("image");
        try (InputStream fileContent = imagePart.getInputStream()) {
            image = toByteArray(fileContent);
            fileContent.close();
        } catch (NullPointerException e) {
        }

        String homeAddress = req.getParameter("homeAddress");
        String workAddress = req.getParameter("workAddress");
        String icq = req.getParameter("icq");
        String skype = req.getParameter("skype");
        String[] phone = req.getParameterValues("addmore[]");
        String additionalInfo = req.getParameter("additionalInfo");
        String email = req.getParameter("email");
        String password = req.getParameter("pass");
        String date = req.getParameter("date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = null;
        try {
            if (!date.isEmpty()) //??
                parsed = format.parse(date);
        } catch (ParseException e) {
        }

        if (firstName.isEmpty() || surName.isEmpty() || email.isEmpty() || password.isEmpty() || gender.isEmpty()
                || date.isEmpty()) {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/edit/employee/accountEditForm.jsp");
            out.println("<font color=red>Please fill all required fields</font>");
            rd.include(req, resp);
        } else {
            AccountService as = new AccountService(new AccountDAO());
            Account acc = new Account();
            acc.setName(firstName);
            acc.setSurName(surName);
            acc.setSex(Sex.valueOf(gender));
            acc.setEmail(email);
            acc.setPassword(password);
            java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
            acc.setBirthDate(sqlDate);
            if (!middleName.isEmpty()) {
                acc.setMiddleName(middleName);
            }
            if (!homeAddress.isEmpty()) {
                acc.setHomeAddress(homeAddress);
            }
            if (!workAddress.isEmpty()) {
                acc.setWorkAddress(workAddress);
            }
            if (!icq.isEmpty()) {
                acc.setIcq(Integer.valueOf(icq));
            }
            if (!skype.isEmpty()) {
                acc.setSkype(skype);
            }
            if (!additionalInfo.isEmpty()) {
                acc.setAdditionalInfo(additionalInfo);
            }
            if (image != null) {
                acc.setImage(image);
            }
            try {
                PhoneService ps = new PhoneService(new PhoneDAO());
                int accId = as.createAccount(acc);
                for (String s : phone) {
                    if (s != null && !s.isEmpty()) {
                        Phone ph = new Phone();
                        ph.setAccId(accId);
                        ph.setNumber(Long.parseLong(s));
                        ps.create(ph);
                    }
                }
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/login.jsp");
                rd.forward(req, resp);
            } catch (SQLException e) {
                out.println(e);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/jsp/edit/employee/accountEditForm.jsp");
                //out.println("<font color=red>Something goes wrong, try again</font>");
                rd.include(req, resp);
            }
        }
    }
}