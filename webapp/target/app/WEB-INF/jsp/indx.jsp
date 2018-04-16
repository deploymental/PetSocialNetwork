<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/webjars/jquery-ui-themes/base/jquery-ui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/w3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/w3-theme-blue-grey.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/index.css">
    <script src="${pageContext.request.contextPath}/webjars/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/webjars/jquery-ui/jquery-ui.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/index.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/search.js"></script>
    <script>var context = "${pageContext.request.contextPath}"</script>
</head>
<title>Мой акаунт</title>
<body class="w3-theme-l5">
<div class="w3-top">
    <div class="w3-bar w3-theme-d2 w3-left-align w3-large">
        <a class="w3-bar-item w3-button w3-hide-medium w3-hide-large w3-right w3-padding-large w3-hover-white w3-large w3-theme-d2"
           href="javascript:void(0);" onclick=""><i class="fa fa-bars"></i></a>
        <a href="${pageContext.request.contextPath}/index" class="w3-bar-item w3-button w3-padding-large w3-theme-d4">
            <i class="glyphicon glyphicon-home w3-margin-right"></i>Home</a>
        <a href="#" class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" title="News">
            <i class="fa fa-globe"></i></a>
        <a href="${pageContext.request.contextPath}/information" class="w3-bar-item w3-button w3-hide-small
        w3-padding-large w3-hover-white"
           title="Account Settings">
            <i class="glyphicon glyphicon-user"></i></a>
        <a href="${pageContext.request.contextPath}/messages" class="w3-bar-item w3-button w3-hide-small
        w3-padding-large w3-hover-white" title="Messages">
            <i class="glyphicon glyphicon-envelope"></i></a>

        <a class="w3-bar-item w3-button w3-hide-small w3-padding-large w3-hover-white" title="Поиск..."
           onclick="searchFunction()"> <i class="glyphicon glyphicon-search"></i></a>

        <input class="w3-bar-item " id="search" name="search" placeholder="Поиск..."
               style="height: 42px; color: black;">

        <a href="${pageContext.request.contextPath}/logout"
           class="w3-bar-item w3-button w3-hide-small w3-right w3-padding-large w3-hover-white"
           title="Выйти">
            <img src="${pageContext.request.contextPath}/resources/image/logout.jpg" class="w3-circle"
                 style="height:25px;width:25px" alt="LogOut"></a>
    </div>
</div>
<!-- Navbar on small screens -->
<div id="navDemo" class="w3-bar-block w3-theme-d2 w3-hide w3-hide-large w3-hide-medium w3-large">
    <a href="#" class="w3-bar-item w3-button w3-padding-large">Link 1</a>
    <a href="#" class="w3-bar-item w3-button w3-padding-large">Link 2</a>
    <a href="#" class="w3-bar-item w3-button w3-padding-large">Link 3</a>
    <a href="#" class="w3-bar-item w3-button w3-padding-large">My Profile</a>
</div>
<!-- Page Container -->
<div class="w3-container w3-content" id="mainContainer" style="max-width:1100px; margin-top:20px;min-height: 500px;">
    <!-- The Grid -->
    <div class="w3-row">
        <!-- Left Column -->
        <div class="w3-col m3">
            <!-- Profile -->
            <div class="w3-card-2 w3-round w3-white">
                <div class="w3-container">
                    <c:set var="img" value="${account.getImage()}"/>
                    <c:if test="${img == null}">
                        <c:set var="img" value="${pageContext.request.contextPath}/resources/image/avatar.png"/>
                    </c:if>
                    <p class="w3-center"><img src="${img}" class="w3-card-4" style="height:106px;width:106px"
                                              alt="Avatar"></p>
                    <hr>
                    <c:if test="${not empty account.name}">
                        <p> Имя : <i class="name">${account.name}</i></p>
                    </c:if>
                    <c:if test="${not empty account.patronymic}">
                        <p> Отчество : <i class="patronymic">${account.patronymic}
                        </i></p>
                    </c:if>
                    <c:if test="${not empty account.surname}">
                        <p> Фамилия : <i class="surname">${account.surname}
                        </i></p>
                    </c:if>
                    <c:if test="${not empty account.homeAddress}">
                        <p><i class="fa fa-home fa-fw w3-margin-right w3-text-theme"></i>
                                ${account.homeAddress.city},${account.homeAddress.country}
                        </p>
                    </c:if>
                    <c:if test="${not empty account.birthday}">
                        <p><i class="fa fa-birthday-cake fa-fw w3-margin-right w3-text-theme"></i>
                                ${account.birthday}
                        </p>
                    </c:if>
                </div>
            </div>
            <br>
            <!-- Accordion -->
            <div class="w3-card-2 w3-round">
                <div class="w3-white">
                    <button id="account" onclick="window.location.href = context + '/information'"
                            class="w3-button w3-block w3-theme-l1 w3-left-align">
                        <i class="fa fa-cog fa-fw w3-margin-right"></i>Мой аккаунт
                    </button>
                </div>
                <div class="w3-white">
                    <button id="groups" onclick="redirectFunction('groups')"
                            class="w3-button w3-block w3-theme-l1 w3-left-align"
                            style="margin-top: 6px;"><i class="fa fa-circle-o-notch fa-fw w3-margin-right">
                    </i>Мои группы
                    </button>
                </div>
                <div class="w3-white">
                    <button id="friends" onclick="window.location.href = context + '/friends'"
                            class="w3-button w3-block w3-theme-l1 w3-left-align"
                            style="margin-top: 6px;"><i class="fa fa-user fa-fw w3-margin-right"></i>Мои друзья
                    </button>
                </div>
                <div class="w3-white">
                    <button id="messages" onclick="window.location.href = context + '/messages'"
                            class="w3-button w3-block w3-theme-l1 w3-left-align"
                            style="margin-top: 6px;"><i class="fa fa-envelope fa-fw w3-margin-right"></i>Сообщения
                    </button>
                </div>
            </div>
            <!-- End Left Column -->
        </div>
        <!-- Middle Column -->
    </div>
    <%--Column--%>
    <div class="w3-col m4">
        <div class="w3-card-2 w3-round w3-white w3-center">
            <div class="w3-container" id="rightColumn">

            </div>
        </div>
    </div>
</div>

<br>

<!--
<footer if = "footer" class="footer w3-container w3-theme-d3 ">
  <h5>Social network</h5>
</footer>
 
-->
<div class="modal fade" id="searchAlert" role="dialog">
    <div class="modal-dialog modal-sm">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title">Ошибка!</h4>
            </div>
            <div class="modal-body">
                <p>Пустой запрос поиска!!!</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary active" data-dismiss="modal">Закрыть</button>
            </div>
        </div>
    </div>
</div>
</body>
</html> 
