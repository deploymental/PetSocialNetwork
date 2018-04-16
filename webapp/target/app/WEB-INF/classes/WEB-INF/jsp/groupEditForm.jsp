<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page session="false"%>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">

<jsp:include page="fragments/header.jsp"/>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.7.1/js/bootstrap-datepicker.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-filestyle/2.1.0/bootstrap-filestyle.min.js"></script>
<script type='text/javascript' src="http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>

<body>

<div class="container">

    <form action="/groupModification/${group_id}" method="post" enctype="multipart/form-data">
        <div class="panel panel-warning">
            <div class="panel-heading">
                <c:choose>
                    <c:when test="${update != true}">
                        <h1>Add Group</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>Update Group</h1>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-md-3 col-lg-3 ">
                        <div align="center">
                            <img src="${preview}" height="186" width="186" id="previewImage">
                        </div>
                        <div style="width:250px;margin: 15px;" align="center">
                            <div style="float: left; width: 124px" align="center">
                                <input type="file" id='imag' name='imag'
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
                            <tr>
                                <td><span class="glyphicon glyphicon-tower"></span></td>
                                <td><input name="name" type="text" class="form-control" placeholder="Name"/></td>
                            </tr>
                            <tr>
                                <td><span class="glyphicon glyphicon-user"></span></td>
                                <td><textarea class="form-control" name="description"
                                              placeholder="Description" rows="5"></textarea></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                        <span class="pull-right">
                            <c:choose>
                                <c:when test="${true}">
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
                            <c:if test="${!true}">
                                    <button type="submit" class="btn-lg btn-primary pull-right"><i
                                            class="glyphicon glyphicon-remove-sign"></i> Remove
                                    </button>
                            </c:if>
                        </span>
                            <form:hidden path="id"/>
                        </span>
                </div>
            </div>
        </div>
    </form>
</div>
<jsp:include page="fragments/footer.jsp"/>

</body>
</html>