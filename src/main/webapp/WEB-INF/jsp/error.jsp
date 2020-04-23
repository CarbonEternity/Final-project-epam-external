<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 20.04.2020
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>
                <c:set var="message" value="${requestScope['javax.servlet.error.message']}"/>
                <c:set var="exception" value="${requestScope['javax.servlet.error.exception']}"/>

               <%-- <c:if test="${not empty code}">
                    <h3>Error code: ${code}</h3>
                </c:if>--%>
<%--
                <c:if test="${not empty message}">
                    <h3>${message}</h3>
                </c:if>--%>

               <%-- <c:if test="${not empty exception}">
                    <% exception.printStackTrace(new PrintWriter(out)); %>
                </c:if>--%>

                <%-- if we get this page using forward --%>
                <c:if test="${not empty requestScope.errorMessage}">
                    <h3>${requestScope.errorMessage}</h3>
                </c:if>

                <div class="error-details">
                    Sorry, You do not have permission to access the requested resource.
                </div>
                <div class="error-actions">
                    <a href="/final" class="btn btn-primary btn-lg">
                        <span class="glyphicon glyphicon-arrow-left"></span>
                        Back to login </a>

                    <a href="#" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-envelope"></span> Contact Support </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>