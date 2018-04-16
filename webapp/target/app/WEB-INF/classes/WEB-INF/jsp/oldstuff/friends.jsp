<%@ page import="com.getjavajob.common.enums.Role" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Display</title>
    <style>
        table#nat {
            width: 50%;
        }
    </style>
</head>
<center>
    <body>
    <%@ include file="header.jsp" %>
    <%

    %>
    <div style="float: left;">
        <form action="/webapp" method="get">
            <br><br>
            <tr>
                <td><input type="submit" value="Friends"/></td>
            </tr>
            <br><br><br>
            <tr>
                <td><input type="submit" value="Requests"/></td>
            </tr>
            <br><br><br>
            <tr>
                <td><input type="submit" value="Your Requests"/></td>
            </tr>
            <br><br><br>
        </form>
    </div>
    <table id="nat">
        <%--<tr>
            <td>Image</td>
            <td><img src=/getImage?id=1"&lt;%&ndash;"data:image/jpeg;base64,<%= imgData %>"&ndash;%&gt; &lt;%&ndash;alt="images Here"&ndash;%&gt; &lt;%&ndash;width="130px"
                     height="90px"&ndash;%&gt;/></td>
        </tr>--%>
        <tr>
            <td>Name</td>
            <td><%= firstName %>
            </td>
        </tr>
        <tr>
            <td>SurName</td>
            <td><%= surName %>
            </td>
        </tr>
        <tr>
            <td>MiddleName</td>
            <td><%= middleName %>
            </td>
        </tr>
        <tr>
            <td>Sex</td>
            <td><%= gender %>
            </td>
        </tr>
        <tr>
            <td>HomeAddress</td>
            <td><%= homeAddress %>
            </td>
        </tr>
        <tr>
            <td>WorkAddress</td>
            <td><%= workAddress %>
            </td>
        </tr>
        <tr>
            <td>Mail</td>
            <td><%= email %>
            </td>
        </tr>
        <tr>
            <td>Pass</td>
            <td><%= password %>
            </td>
        </tr>
        <tr>
            <td>Date</td>
            <td><%= date %>
            </td>
        </tr>
        <tr>
            <td>ICQ</td>
            <td><%= icq %>
            </td>
        </tr>
        <tr>
            <td>Skype</td>
            <td><%= skype %>
            </td>
        </tr>
        <tr>
            <td>AddInfo</td>
            <td><%= additionalInfo %>
            </td>
        </tr>
        <%
            if (request.getSession().getAttribute("accEmail").equals(request.getParameter("email"))
                    || request.getSession().getAttribute("accEmail").equals((request.getAttribute("email")))
                    || Role.ADMIN.equals(acc.getRole())) {
        %>
        <form action="/edit" method="get">
            <tr>
                <td><input type="submit" value="Edit"/></td>
            </tr>
        </form>
        <%
            }
        %>
        <%
            if (!request.getSession().getAttribute("accEmail").equals(request.getParameter("email"))
                    && !request.getSession().getAttribute("accEmail").equals((request.getAttribute("email")))) {
        %>
        <form action="/edit" method="get">
            <tr>
                <td><input type="submit" value="Add friend"/></td>
            </tr>
        </form>
        <%
            }
        %>
        <form action="/edit" method="get">
            <tr>
                <td><input type="submit" value="Add friend"/></td>
            </tr>
        </form>
    </table>
    </body>
</center>
</html>