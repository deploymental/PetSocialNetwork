<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}" scope="application"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<jsp:include page="fragments/header.jsp"/>

<%--//todo stack trace smaller the default ?--%>
<body>
<div class="container">
    <div class="col-md-8 col-md-offset-2 margin-top-bottom-20">
        <div>
            <fieldset>
                <legend>
                    <small>OOPps... Something goes wrong</small>
                    ${pageContext.exception.printStackTrace()}
                </legend>
                <c:if test="${not empty pageContext.errorData.statusCode}">
                    <div class="col-md-12 text-center margin-20">
                        <small class="text-muted">Error code</small>
                        <h1 class="text-danger">${pageContext.errorData.statusCode}</h1>
                    </div>
                </c:if>

                <c:if test="${not empty pageContext.errorData.requestURI}">
                    <div class="col-md-12 text-center margin-20">
                        <small class="text-muted">URI</small>
                        <h3 class="text-danger">${pageContext.errorData.requestURI}</h3>
                    </div>
                </c:if>

                <c:if test="${not empty pageContext.exception}">
                    <div class="col-md-12 margin-20 text-center">
                        <small>Exception</small>
                        <h4 class="text-danger">${pageContext.exception}</h4>
                    </div>
                </c:if>

                <c:if test="${not empty pageContext.exception.stackTrace}">
                    <div class="col-md-12">
                        <small>Stacktrace</small>

                        <c:forEach var="trace" items="${pageContext.exception.stackTrace}">
                            <p class="text-danger hyphen">${trace}</p>
                        </c:forEach>
                    </div>
                </c:if>
            </fieldset>
        </div>
    </div>
</div>
<div class='notifications top-right'></div>
<jsp:include page="fragments/footer.jsp"/>
<script>
    $(document).ready(function () {
        $('.top-right').notify({
            message: {text: 'Если вы видите эту страницу, то произошла ошибка'},
            fadeOut: {enabled: true, delay: 1500},
            closable: false,
            type: 'info'
        }).show();

        var context = {val: '${context}'};
        setContext(context);
    })
</script>
</body>
</html>