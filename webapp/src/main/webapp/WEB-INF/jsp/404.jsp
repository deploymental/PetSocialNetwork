<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="context" value="${pageContext.request.contextPath}" scope="application"/>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<jsp:include page="fragments/header.jsp"/>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-12 text-center">
            <h1 class="text-warning" style="font-size: 20em">
                404
            </h1>
            <h1 class="text-warning">Page not found</h1>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
<script>
    $(document).ready(function () {
        <c:if test="${not empty param.notification}">
        $('.top-right').notify({
            message: {text: 'Страница не найдена, проверьте адрес в строке браузера'},
            fadeOut: {enabled: true, delay: 1500},
            closable: false,
            type: 'info'
        }).show();
        </c:if>

        var context = {val: '${context}'};
        setContext(context);
    })
</script>
</body>
</html>