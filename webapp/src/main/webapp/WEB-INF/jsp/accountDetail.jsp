<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false"%>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<jsp:include page="fragments/header.jsp"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}css\wall.css">--%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../../resources/custom/js/imagePreviewAndSave.js"></script>
<style>
    .round {
        border-radius: 100px; /* Радиус скругления */
        box-shadow: 0 0 0 3px green, 0 0 13px #333; /* Параметры теней */
    }
</style>
<%--<link href="../../../resources/custom/css/style.css/../resources/custom/css/style.css/../../resources/custom/css/style.css"
      rel="stylesheet"/>--%>
<body>

<div class="container">

    <div class="panel panel-info">
        <div class="panel-heading">
            <h1>Evil Account
                <li class="active pull-right">
                    <a href="/registration"><i class="glyphicon glyphicon-edit"></i></a>
                </li>
            </h1>

        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-3 col-lg-3 ">
                    <c:if test="${image != null} ">
                        <c:set var="preview" scope="page" value="/account/image/${account.id}"/>
                    </c:if>
                    <div align="center">
                        <a rel="nofollow" target="_blank" href="/account/image/${account.id}">
                            <img class="round" src="/account/image/${account.id}" height="186"
                                 width="186" <%--id="previewImage"--%>
                                 onerror="this.src='/resources/custom/img/default.jpg'">
                        </a>
                    </div>
                    <%--<c:if test='${relation != "MY_ACCOUNT"}'>
                        <a href="${pageContext.request.contextPath}/account/${authAccount.id}"
                           class="btn btn-sm btn-friend">
                            <span class="bigger-110">Вернуться на свою страницу</span>
                        </a>
                    </c:if>--%>
                    <div <%--style="float: right; width: 124px"--%> align="center">
                        <c:if test='${relation == "GUEST"}'>
                            <a href="${pageContext.request.contextPath}/sendFriendRequest/${account.id}"
                               class="btn btn-sm btn-friend">
                                <i class="ace-icon fa fa-plus-circle bigger-120"></i>
                                <span class="bigger-110">SEND REQUEST</span>
                            </a>
                        </c:if>
                        <c:if test='${relation == "FOLLOWER"}'>
                            <a href="${pageContext.request.contextPath}/acceptFriendRequest/${account.id}"
                               class="btn btn-sm btn-friend">
                                <i class="ace-icon fa fa-plus-circle bigger-120"></i>
                                <span class="bigger-110">ADD IN FRIENDS</span>
                            </a>
                            <a href="${pageContext.request.contextPath}/deleteFriend/${account.id}"
                               class="btn btn-sm btn-friend">
                                <i class="ace-icon fa fa-plus-circle bigger-120"></i>
                                <span class="bigger-110">REMOVE REQUEST</span>
                            </a>
                        </c:if>
                        <c:if test='${relation == "RECIPIENT"}'>
                            <a href="${pageContext.request.contextPath}/deleteFriend/${account.id}"
                               class="btn btn-sm btn-friend">
                                <i class="ace-icon fa fa-plus-circle bigger-120"></i>
                                <span class="bigger-110">REMOVE INCOMING REQUEST</span>
                            </a>
                        </c:if>
                        <c:if test='${relation == "FRIEND"}'>
                            <a href="${pageContext.request.contextPath}/deleteFriend/${account.id}"
                               class="btn btn-sm btn-friend">
                                <i class="ace-icon fa fa-plus-circle bigger-120"></i>
                                <span class="bigger-110">REMOVE FROM FRIENDS</span>
                            </a>
                        </c:if>
                        <%--<c:if test='${relation == "MY_ACCOUNT"}'>
                            <a href="${pageContext.request.contextPath}/showRelatedAccounts?account=${account.id}"
                               class="btn btn-sm btn-friend">
                                <i class="ace-icon fa fa-plus-circle bigger-120"></i>
                                <span class="bigger-110">Показать друзей</span>
                            </a>
                        </c:if>--%>
                    </div>
                </div>
                <div class=" col-md-9 col-lg-9 ">
                    <table class="table table-user-information">
                        <tbody>
                        <tr>
                            <td><span class="glyphicon glyphicon-user"></span> Last Name</td>
                            <td>${account.surName}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-user"></span> First Name</td>
                            <td>${account.name}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-user"></span> Middle Name</td>
                            <td>${account.middleName}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-book"></span> AdditionalInfo</td>
                            <td>${account.additionalInfo}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-calendar"></span> Birth Day</td>
                            <td>${account.birthDate.toString()}</td>
                        </tr>
                        <td><span class="glyphicon glyphicon-comment"></span> ICQ</td>
                        <td>${account.icq}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-headphones"></span> Skype</td>
                            <td>${account.skype}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-envelope"></span> Email</td>
                            <td>${account.email}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-globe"></span> HomeAddress</td>
                            <td>
                                <c:out value="${account.homeAddress}"></c:out>
                                <c:if test="${empty account.homeAddress}">
                                    <div class="bg-danger">
                                        None
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-globe"></span> WorkAddress</td>
                            <td>
                                <c:out value="${account.workAddress}"></c:out>
                                <c:if test="${empty account.workAddress}">
                                    <div class="bg-danger">
                                        None
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-phone"></span> Phones</td>
                            <td>
                                <table class="table contact-table table-sortable">
                                    <thead>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="phone" items="${phones}" varStatus="phoneIndex">
                                        <tr>
                                            <td>${phone.number}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <c:if test="${empty phones}">
                                    <div class="bg-danger">
                                        None
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <%--<div class="form-group">
                    <textarea class="form-control" placeholder="Comments" rows="5" id="comment"></textarea>
                </div>
                <button type="submit" class="btn-lg btn-primary btn btn-success pull-right"><i
                        class="glyphicon glyphicon-pushpin"></i> Send
                </button>
--%>


                <div class="panel-body">
                    <form action="/addPostToAccountWall${account.id}" method="post">
            <textarea class="form-control counted" id="text" name="message" placeholder="Type in your message" rows="3"
                      style="margin-bottom:10px;"></textarea>

                        <button type="submit" class="btn-lg btn-primary pull-right"><i
                                class="glyphicon glyphicon-floppy-disk"></i> Post
                        </button>
                        <%--</c:otherwise>
                    </c:choose>--%>
                    </form>
                </div>

                <div id="newPost">
                    <c:forEach var="post" items="${posts}">
                        <form action="/deleteAccountWallPost${post.id}/fromUser${account.id}" method="post">
                            <div class="panel panel-default" id="${post.id}">
                                <div class="panel-heading">
                                    <c:if test="${post.sender.id eq accountSession.id}">
                                        <div class="block" style="float:right;">
                                            <button type="submit" class="glyphicon glyphicon-remove"
                                                    data-postId="${post.id}"
                                                    id="postDeleteButton"></button>
                                        </div>
                                    </c:if>
                                    <div class="block">
                                        <a href="" class="anchor-username ">
                                                ${post.sender.surName} ${post.sender.name}
                                        </a>
                                    </div>
                                    <br/>
                                    <div class="block">
                                        <img src="/account/image/${post.sender.id}" class="round" width="60" height="60"
                                             onerror="this.src='/resources/custom/img/default.jpg'" class="round"
                                             width="60"
                                             height="60">
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <p>${post.text}</p>
                                </div>
                                <div class="panel-footer">
                                    <small class="form-text text-muted">${post.date}</small>
                                </div>
                            </div>
                        </form>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<jsp:include page="fragments/footer.jsp"/>

<c:if test="${empty employee.image}">
    <script>
        setPreview(null);
    </script>
</c:if>

</body>
</html>