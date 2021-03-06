<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false"%>--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-filestyle/2.1.0/bootstrap-filestyle.min.js"></script>
<script type='text/javascript' src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<script src="/resources/custom/js/phoneFieldsGeneration.js"></script>
<body>
<%--//todo front-end validation requered--%>
<div class="container">

    <form action="/doRegistrationOrModification" method="post" enctype="multipart/form-data">
        <div class="panel panel-warning">
            <div class="panel-heading">
                <c:choose>
                    <c:when test="${accountSession == null}">
                        <h1>Add Employee</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>Update Employee</h1>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-3 col-lg-3 ">
                        <%--&lt;%&ndash;//todo reuse image if changes not detected &ndash;%&gt;
                        <c:if test="${not empty homePageOwner.getImage()}">
                            <c:set var="preview" scope="page" value="${homePageOwner.getImage()}"/>
                        </c:if>--%>
                        <div align="center">
                            <img src="${preview}" height="186" width="186" id="previewImage">
                        </div>
                        <div style="width:250px;margin: 15px;" align="center">
                            <div style="float: left; width: 124px" align="center">
                                <input type="file" id='image' name='imag'
                                       accept='image/x-png,image/gif,image/jpeg' <%--data-buttonName="btn-primary"--%>>
                            </div>
                            <div style="float: right; width: 124px" align="center">
                                <button class="btn btn-danger" type="button" onclick="setPreview(null)">
                                    <span class="glyphicon glyphicon-remove"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <div class=" col-md-9 col-lg-9 ">
                        <table class="table table-user-information">
                            <tbody>
                            <input type="hidden" value="${account.id}" id="account-id">
                            <c:if test='${accountSession == null}'>
                                <tr>
                                    <td><span class="glyphicon glyphicon-user"></span></td>
                                    <td><input name="surName" value="${account.surName}" type="text"
                                               class="form-control" placeholder="Last Name"/></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input name="name" value="${account.name}" type="text" class="form-control"
                                               placeholder="First Name"/></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td><input name="middleName" value="${account.middleName}" type="text"
                                               class="form-control" placeholder="Middle Name"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td><span class="glyphicon glyphicon-king"></span></td>
                                    <td><input type="radio" name="sex" value="MALE" checked></td>
                                </tr>
                                <tr>
                                    <td><span class="glyphicon glyphicon-queen"></span></td>
                                    <td><input type="radio" name="sex" value="FEMALE" checked></td>
                                </tr>
                                <tr>
                                    <td><span class="glyphicon glyphicon-calendar"></span></td>
                                    <td>
                                        <div id="date">
                                                <%--<p class="help-block">Birth Day</p>--%>
                                            <input type="date" name="birthDate" value="${account.birthDate}"
                                                   class="form-control">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><span class="glyphicon glyphicon-envelope"></span></td>
                                    <td><input name="email" value="${account.email}" type="text" class="form-control"
                                               placeholder="Email"/></td>
                                </tr>
                            </c:if>

                            <tr>
                                <td><span class="glyphicon glyphicon-log-in"></span></td>
                                <td><input name="password" value="${account.password}" type="text" class="form-control"
                                           placeholder="Password"/></td>
                            </tr>
                            <%--<tr>
                                <td></td>
                                <td><input name="passrepeat" type="text" class="form-control"
                                           placeholder="Repeat password"/></td>
                            </tr>--%>
                            <tr>
                                <td><span class="glyphicon glyphicon-comment"></span></td>
                                <td><input name="icq" value="${account.icq}" type="text" class="form-control"
                                           placeholder="ICQ"/></td>
                            </tr>
                            <tr>
                                <td><span class="glyphicon glyphicon-headphones"></span></td>
                                <td><input name="skype" value="${account.skype}" type="text" class="form-control"
                                           placeholder="Skype"/></td>
                            </tr>
                            <tr>
                                <td><span class="glyphicon glyphicon-globe"></span></td>
                                <td><input name="homeAddress" value="${account.homeAddress}" type="text"
                                           class="form-control"
                                           placeholder="Home Address"/></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td><input name="workAddress" value="${account.workAddress}" type="text"
                                           class="form-control"
                                           placeholder="Work Address"/></td>
                            </tr>
                            <tr>
                                <td><span class="glyphicon glyphicon-book"></span></td>
                                <td><textarea class="form-control" name="additionalInfo"
                                              value="${account.additionalInfo}"
                                              placeholder="Additional Information" rows="5" id="comment"></textarea>
                                </td>
                            </tr>

                            <tr>
                                <td><span class="glyphicon glyphicon-phone"></span></td>
                                <td>
                                    <div class="input-group control-group after-add-more">
                                        <input type="text" name="addmore[]" class="form-control"
                                               placeholder="Add Phone">
                                        <div class="input-group-btn">
                                            <button class="btn btn-success add-more" type="button"><i
                                                    class="glyphicon glyphicon-plus"></i> Add
                                            </button>
                                        </div>
                                    </div>


                                    <!-- Copy Fields-These are the fields which we get through jquery and then add after the above input,-->
                                    <div class="copy-fields hide">
                                        <div class="control-group input-group" style="margin-top:10px">
                                            <input type="text" name="addmore[]" class="form-control"
                                                   placeholder="Add Phone">
                                            <div class="input-group-btn">
                                                <button class="btn btn-danger remove" type="button"><i
                                                        class="glyphicon glyphicon-remove"></i> Remove
                                                </button>
                                            </div>
                                        </div>
                                    </div>

                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="form-group">
                    <div <%--class="col-sm-offset-2 col-sm-10"--%>>
                        <span class="pull-right">
                            <c:choose>
                                <c:when test="${accountSession == null}">
                                    <button type="submit" class="btn-lg btn-primary pull-right"><i
                                            class="glyphicon glyphicon-floppy-disk"></i> Add
                                    </button>
                                </c:when>
                                <c:otherwise>
                                    <button type="submit" class="btn-lg btn-primary pull-right"><i
                                            class="glyphicon glyphicon-floppy-disk"></i> Update
                                    </button>
                                </c:otherwise>
                            </c:choose>
                            <span class="pull-left">
                            <c:if test="${accountSession != null}">
                                    <a href="${pageContext.request.contextPath}/remove/${accountSession.id}"
                                       class="btn btn-sm btn-friend">
                                <i class="ace-icon fa fa-plus-circle bigger-120"></i>
                                <span class="bigger-110">REMOVE</span>
                            </a>
                            </c:if>
                        </span>
                            <form:hidden path="id"/>
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>


<c:if test="${empty homePageOwner.getImage()}">
    <script>
        setPreview(null);
    </script>
</c:if>
<c:if test="${not empty homePageOwner.getImage()}">
    <script>
        setPreview("${homePageOwner.getImage()}");
    </script>
</c:if>
</body>
</html>