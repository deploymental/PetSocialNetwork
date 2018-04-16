<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">

<jsp:include page="fragments/header.jsp"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

<style>
    .round {
        border-radius: 100px; /* Радиус скругления */
        box-shadow: 0 0 0 3px green, 0 0 13px #333; /* Параметры теней */
    }
</style>
<body>
<div class="container">


    <div class="panel panel-info">
        <div class="panel-heading">
            <h1>Search Result</h1>
        </div>

        <table class="top-name center-block text-center">
            <br/><br/>
            <center>
                <c:forEach items="${accounts}" var="account">
                    <br/><br/>
                    <div class="block2" id="blockSearch${index}">
                        <div>
                            <img id="image${index}" src="/account/image/${account.id}"
                                 class="round" width="200" height="200"
                                 onerror="this.src='/resources/custom/img/default.jpg'"/>
                        </div>
                        <br/>
                        <div>
                            <a href="/account/${account.id}" id="link${index}">${account.name} ${account.surName}</a>
                        </div>
                    </div>

                    <c:set var="index" value="${index + 1}"/>
                </c:forEach>
            </center>
            <br/><br/><br/><br/>
        </table>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>