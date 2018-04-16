<%@ page import="com.getjavajob.common.Account" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="accountSession" scope="session"
       value="${accountSession}"/>


<head>
    <title>EvilNetwork</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <%--<link rel="icon" href="../../../resources/custom/icon/redBook.ico">--%>

    <!-- css -->
    <%--<spring:url value="/resources/css/bootstrap.min.css"
                var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>--%>
</head>

<%--<spring:url value="/" var="urlHome"/>
<spring:url value="/edit/account/add" var="urlAddEmployee"/>
<spring:url value="/edit/group/add" var="urlAddDepartment"/>
<spring:url value="/showDepartments" var="urlShowDepartments"/>
<spring:url value="/login" var="urlLogin"/>
<spring:url value="/doLogout" var="urlLogout"/>--%>

<nav class="navbar navbar-inverse ">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="/login">EvilNetwork</a>
        </div>

        <div id="navbar">
            <ul class="nav navbar-nav navbar-left">
                <li class="active">
                    <div class="input-group">
                        <form class="navbar-form navbar-left" role="search" action="/search" method="post">
                            <div class="input-group navbar-searchbox">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-default">Go</button>
                                </div>
                                <input id="search" name="search" type="text" class="form-control"
                                       placeholder="Search for...">
                            </div>
                        </form>
                    </div>
                </li>
            </ul>
            <div class="navbar-form navbar-right">
                <c:if test="${accountSession == null}">
                    <button class="btn btn-success" onclick="location.href='/login'">Login</button>
                </c:if>
                <c:if test="${accountSession != null}">
                    <a href="#"><%= ((Account) session.getAttribute("accountSession")).getEmail()%>
                    </a>
                    <button class="btn btn-danger" onclick="location.href='/logout'">Logout</button>
                </c:if>
            </div>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="/account/groups">Groups</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="/account/friendsList">Friends</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="https://web.telegram.org/">Messages</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="/chat">Evil Chat Room</a></li>
            </ul>
        </div>
    </div>
</nav>