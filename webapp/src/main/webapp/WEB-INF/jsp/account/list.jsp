<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>
    <div class="panel panel-info">
        <div class="panel-heading">
            <h1>Friends List</h1>
        </div>

        <table class="table table-striped">
            <%--<thead>
            <tr>
                <th>Name</th>
                <th>Department</th>
                <th>Action</th>
            </tr>
            </thead>--%>

            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.fullName}</td>
                    <td>
                        <c:if test="${not empty employee.department}">
                            <a href="<c:url value="/department/showInfo?id=${employee.department.id}"/>">${employee.department.name}</a>
                        </c:if>
                        <c:if test="${empty employee.department}">
                            <p>None</p>
                        </c:if>
                    </td>
                    <td>
                        <spring:url value="/employee/showInfo?id=${employee.id}" var="infoUrl"/>
                        <spring:url value="/edit/employee/delete?id=${employee.id}" var="deleteUrl"/>
                        <spring:url value="/edit/employee/update?id=${employee.id}" var="updateUrl"/>

                        <button class="btn btn-warning" onclick="location.href='${infoUrl}'">Show info</button>
                        <button class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
                        <button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<jsp:include page="../fragments/footer.jsp"/>

</body>
</html>