<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false"%>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<jsp:include page="../fragments/header.jsp"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-filestyle/2.1.0/bootstrap-filestyle.min.js"></script>
<script type='text/javascript' src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<body>

<div class="container">

    <div class="panel panel-info">
        <div class="panel-heading">
            <h1>Department Detail</h1>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-3 col-lg-3 ">
                    <table class="table table-department-information">
                        <tbody>
                        <tr>
                            <td><span class="glyphicon glyphicon-tower"></span> Name</td>
                            <td>${department.name}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-user"></span> Head</td>
                            <td>
                                <c:if test="${not empty department.head}">
                                    <a href="<c:url value="/employee/showInfo?id=${department.head.id}"/>">${department.head.fullName}</a>
                                </c:if>
                                <c:if test="${empty department.head}">
                                    <div class="bg-danger">
                                        None
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="row" align="right">
                        <spring:url value="/edit/department/delete?id=${department.id}" var="deleteUrl"/>
                        <spring:url value="/edit/department/update?id=${department.id}" var="updateUrl"/>

                        <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
                        <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>