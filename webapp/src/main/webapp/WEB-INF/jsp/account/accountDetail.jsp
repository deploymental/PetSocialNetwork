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

<%--<link href="../../../resources/custom/css/style.css/../resources/custom/css/style.css/../../resources/custom/css/style.css"
      rel="stylesheet"/>--%>
<body>

<div class="container">

    <div class="panel panel-info">
        <div class="panel-heading">
            <h1>Evil Account
                <li class="active pull-right">
                    <a href="/edit?type=account"><i class="glyphicon glyphicon-edit"></i></a>
                </li>
            </h1>

        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-3 col-lg-3 ">
                    <c:if test="${not empty homePageOwner.getImage()}">
                        <c:set var="preview" scope="page" value="/image?accId=${homePageOwner.getId()}"/>
                    </c:if>
                    <div align="center">
                        <a rel="nofollow" target="_blank" href="/image?accId=${homePageOwner.getId()}">
                            <img src="/image?accId=${homePageOwner.getId()}" height="186"
                                 width="186" <%--id="previewImage"--%>
                                 onerror="this.src='/resources/custom/img/default.jpg'">
                        </a>
                    </div>
                </div>
                <div class=" col-md-9 col-lg-9 ">
                    <table class="table table-user-information">
                        <tbody>
                        <tr>
                            <td><span class="glyphicon glyphicon-user"></span> Last Name</td>
                            <td>${homePageOwner.getSurName()}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-user"></span> First Name</td>
                            <td>${homePageOwner.getName()}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-user"></span> Middle Name</td>
                            <td>${homePageOwner.getMiddleName()}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-book"></span> AdditionalInfo</td>
                            <td>${homePageOwner.getAdditionalInfo()}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-calendar"></span> Birth Day</td>
                            <td>${homePageOwner.getBirthDate().toString()}</td>
                        </tr>
                        <td><span class="glyphicon glyphicon-comment"></span> ICQ</td>
                        <td>${homePageOwner.getIcq()}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-headphones"></span> Skype</td>
                            <td>${homePageOwner.getSkype()}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-envelope"></span> Email</td>
                            <td>${homePageOwner.getEmail()}</td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-globe"></span> HomeAddress</td>
                            <td>
                                <c:out value="${homePageOwner.getHomeAddress()}"></c:out>
                                <c:if test="${empty homePageOwner.getHomeAddress()}">
                                    <div class="bg-danger">
                                        None
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <td><span class="glyphicon glyphicon-globe"></span> WorkAddress</td>
                            <td>
                                <c:out value="${homePageOwner.getWorkAddress()}"></c:out>
                                <c:if test="${empty homePageOwner.getWorkAddress()}">
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
                                    <c:forEach var="phone" items="${homePageOwner.getPhones()}" varStatus="phoneIndex">
                                        <tr>
                                            <td>${phone.getNumber()}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <c:if test="${empty homePageOwner.getPhones()}">
                                    <div class="bg-danger">
                                        None
                                    </div>
                                </c:if>
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <div class="form-group">
                        <textarea class="form-control" placeholder="Comments" rows="5" id="comment"></textarea>
                    </div>
                    <button type="submit" class="btn-lg btn-primary btn btn-success pull-right"><i
                            class="glyphicon glyphicon-pushpin"></i> Send
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../fragments/footer.jsp"/>
<script src="../../../resources/custom/js/imagePreviewAndSave.js"></script>
<c:if test="${empty employee.image}">
    <script>
        setPreview(null);
    </script>
</c:if>

</body>
</html>