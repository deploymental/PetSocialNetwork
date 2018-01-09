<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="../../resources/custom/css/style.css" rel="stylesheet"/>--%>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="https://mdbootstrap.com/previews/docs/latest/css/bootstrap.min.css" rel="stylesheet">
<link href="https://mdbootstrap.com/previews/docs/latest/css/mdb.min.css" rel="stylesheet">

<body>
<div class="container">
    <div class="panel-body">
        <form method="post" action="EntryPoint">
            <br/><br/><br/><br/><br/><br/><br/><br/>
            <center>
                <table>
                    <img src="/resources/custom/img/trooper.jpg">
                    <thead>
                    <tr>
                        <th colspan="2">Welcome to EvilNetwork</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><span class="glyphicon glyphicon-envelope"></span> Email</td>
                        <td><input type="text" name="email" value=""/></td>
                    </tr>
                    <tr>
                        <td><span class="glyphicon glyphicon-log-in"></span> Password</td>
                        <td><input type="password" name="pass" value=""/></td>
                    </tr>
                    <tr>
                        <td>
                            <button class="btn btn-primary" value="Login" onclick="Login">Login</button>
                        </td>
                        <%--<td><input type="submit" value="Login"/></td>--%>
                        <td><input type="checkbox" id="remember" <%--name="subscribe" value="newsletter"--%>>
                            <label for="remember">Remember me</label></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <a href="/RegistrationController">Sign Up</a>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </center>
        </form>
    </div>
</div>
</body>
</html>