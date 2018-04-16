<%@ page import="com.getjavajob.AccountDAO" %>
<%@ page import="com.getjavajob.AccountService" %>
<%@ page import="com.getjavajob.common.Account" %>
<%@ page import="com.getjavajob.common.enums.Sex" %>
<%@ page import="java.sql.SQLException" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>User Data</title>
</head>
<style>
    div.ex {
        text-align: center;
        width: 300px;
        padding: 10px;
        margin: 0px
    }
</style>
<center>
    <body>
    <%@ include file="header.jsp" %>
    <%
        String email = (String) request.getSession().getAttribute("accEmail");
        String id = request.getSession().getId();
        String password = request.getParameter("pass");
        AccountService as = new AccountService(new AccountDAO());
        Account acc = null;
        try {
            acc = as.getAccount(email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String firstName = acc.getName();
        String surName = acc.getSurName();
        String middleName = acc.getMiddleName();
        Sex gender = acc.getSex();
        String homeAddress = acc.getHomeAddress();
        String workAddress = acc.getWorkAddress();
        String date = acc.getBirthDate().toString();
        int icq = acc.getIcq();
        String skype = acc.getSkype();
        String additionalInfo = acc.getAdditionalInfo();
       /* Blob image = acc.getImage();
        byte[] imgData = null;
        imgData = image.getBytes(1, (int) image.length());
        //String imgDataBase64 = new String(Base64.getEncoder().encode(imgData));
        //String phone = acc.getPhones();
*/
    %>
    <h1> Edit </h1>
    <div class="ex">
        <form action="/edit" method="post">

            <table border="2" width="30%" cellpadding="5">
                <thead>
                </thead>
                <tbody>
                <tr>
                    <td>First Name</td>
                    <td><%= firstName %>
                    </td>
                    <td><input type="text" name="fname" value=""/></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><%= surName %>
                    </td>
                    <td><input type="text" name="lname" value=""/></td>
                </tr>
                <tr>
                    <td>Middle Name</td>
                    <td><%= middleName %>
                    </td>
                    <td><input type="text" name="mname" value=""/></td>
                </tr>
                <tr>
                    <td>Email</td>
                    <td><%= email %>
                    </td>
                    <td><input type="text" name="email" value=""/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><%= "*" %>
                    </td>
                    <td><input type="password" name="pass" value=""/></td>
                </tr>
                <%--<tr>
                    <td>Male</td>
                    <td><input type="radio" name="gender" value="MALE" checked></td>
                </tr>
                <tr>
                    <td>Female</td>
                    <td><input type="radio" name="gender" value="FEMALE" checked></td>
                </tr>
                <tr>
                    <td><label for="date">Birth date</label></td>
                    <td><input id="date" type="date" name="date"></td>
                </tr>--%>
                <tr>
                    <td>Home address</td>
                    <td><%= homeAddress %>
                    </td>
                    <td><input type="text" name="homeAddress" value=""/></td>
                </tr>
                <tr>
                    <td>Work address</td>
                    <td><%= workAddress %>
                    </td>
                    <td><input type="text" name="workAddress" value=""/></td>
                </tr>
                <tr>
                    <td>ICQ</td>
                    <td><%= icq %>
                    </td>
                    <td><input type="number" name="icq" value=""/></td>
                </tr>
                <tr>
                    <td>Skype</td>
                    <td><%= skype %>
                    </td>
                    <td><input type="text" name="skype" value=""/></td>
                </tr>
                <%--<tr>
                    <td>Phone</td>
                    <td><input type='tel' name="phone" pattern='[\+]\d{2}[\(]\d{2}[\)]\d{4}[\-]\d{4}'
                               title='Phone Number (Format: +99(99)9999-9999)'></td>

                </tr>--%>
                <tr>
                    <td>Additional information</td>
                    <td><%= additionalInfo %>
                    </td>
                    <td><textarea name="additionalInfo" cols="19" rows="5"></textarea></td>
                </tr>
                <%--<tr>
                    <td>Photo</td>
                    <td><input type="file" name="image"></td>
                </tr>--%>


                <tr>
                    <td><input type="submit" value="Edit"/></td>
                </tr>

                </tbody>
            </table>
        </form>
    </div>
    </body>
</center>
</html>