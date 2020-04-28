<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 20.04.2020
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" %>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="style/css/errorStyles.css">
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

    <title>Error</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
                <h1> Oops!</h1>
                <%-- this way we obtain an information about an exception (if it has been occurred) --%>
                <core:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
                <core:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
                <core:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

                <core:if test="${not empty code}">
                    <h3>Error code: ${code}</h3>
                </core:if>
                <core:if test="${not empty message}">
                    <h3>${message}</h3>
                </core:if>

                <%-- if we get this page using forward --%>
                <core:if test="${not empty requestScope.errorMessage}">
                    <h3>${requestScope.errorMessage}</h3>
                </core:if>

                <div class="error-details">
                    Sorry, some error has occurred.
                </div>
                <div class="error-actions">

                    <core:if test="${enrolleeBlocked}">
                    <a href="controller?command=sortFacultyList" class="btn btn-primary btn-lg">
                        <span class="glyphicon glyphicon-arrow-left"></span>
                        Back to faculty list</a>
                    </core:if>


                    <%--<a href="#" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-envelope"></span> Contact Support </a>--%>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>