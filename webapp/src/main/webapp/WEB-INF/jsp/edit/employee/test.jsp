<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<jsp:include page="../../fragments/header.jsp"/>

<body>

<div class="container">
    <div class="panel panel-danger">
        <div class="panel-heading"><h1>Add Employee</h1></div>

        <div class="panel-body">
            <%--//todo class--%>
            <form:form modelAttribute="employee" action="/test" method="post" role="form">
                <div class="form-group">
                    <label>Name: </label>
                    <form:input class="form-control" path="name"/>
                    <c:forEach items="${employee.phones}" var="p">
                        <label for="phone">phone</label>
                        <form:input id="phone" type="text" path="h" value="${p.phone}"/>
                        <label for="id">id</label>
                        <input id="id" type="number" name="phones" value="${p.id} "/>
                        <%--<input type="text" name="phones.type" value="${p.type}"/>--%>
                    </c:forEach>
                    <form:hidden path="id"/>
                </div>
                <div align="right">
                    <button type="submit" class="btn-danger">update</button>
                    |
                </div>
            </form:form>
        </div>
    </div>
</div>


<jsp:include page="../../fragments/footer.jsp"/>
<script src="../../../../resources/custom/js/contactAddAndDelete.js"></script>

</body>
</html>