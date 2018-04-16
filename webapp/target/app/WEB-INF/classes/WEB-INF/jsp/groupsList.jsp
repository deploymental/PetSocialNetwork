<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html lang="en">

<jsp:include page="fragments/header.jsp"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-filestyle/2.1.0/bootstrap-filestyle.min.js"></script>
<script type='text/javascript' src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<style>
    .round {
        border-radius: 100px; /* Радиус скругления */
        box-shadow: 0 0 0 3px green, 0 0 13px #333; /* Параметры теней */
    }
</style>
<body>

<div class="container">

    <%--<c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>--%>
    <div class="panel panel-info">
        <div class="panel-heading">
            <h1>Groups List
                <li class="active pull-right">
                    <a href="/groupRegistration"><i class="glyphicon glyphicon-plus-sign"></i></a>
                </li>
            </h1>

        </div>

        <table class="top-name center-block text-center">
            <br/><br/>
            <center>
                <c:forEach items="${groups}" var="group">
                    <br/><br/>
                    <div class="block2" id="blockSearch${index}">
                        <div>
                            <img id="image${index}" src="/group/image/${group.id}"
                                 class="round" width="200" height="200"
                                 onerror="this.src='/resources/custom/img/default.jpg'"/>
                        </div>
                        <br/>
                        <a href="${pageContext.request.contextPath}/sendFriendRequest/${account.id}"
                           class="btn btn-sm btn-friend">
                            <i class="ace-icon fa fa-plus-circle bigger-120"></i>
                            <span class="bigger-110">SEND REQUEST</span>
                        </a>
                    </div>
                    <c:set var="index" value="${index + 1}"/>
                </c:forEach>
            </center>
            <br/><br/><br/><br/>
        </table>
        <%--<table class="table table-striped">
            <br/><br/>
            <c:forEach items="${groups}" var="group">
                <div class="friends-block" id="blockSearch${index}">
                    <div class="col-md-1 col-lg-1 ">
                        <c:choose>
                            <c:when test="${group.image != null}">
                                <img id="image${index}" src="/group/image/${group.id}"
                                     class="round" width="60" height="60"
                                     onerror="this.src='/resources/custom/img/default.jpg'"/>
                            </c:when>
                            <c:otherwise>
                                <p><img id="image${index}" src="/resources/custom/img/default.jpg"
                                        class="round" width="60" height="60"></p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div>
                        <a href="/group/${group.id}" id="link${index}">${group.name}</a>
                    </div>
                </div>
                <c:set var="index" value="${index + 1}"/>
            </c:forEach>
            </center>
            <br/><br/><br/><br/>
        </table>--%>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>

</body>
</html>