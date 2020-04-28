<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 17.04.2020
  Time: 00:46
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="style/css/resultStyles.css">
    <title><fmt:message key="title.result"/></title>
</head>
<body>

<div class="bs-example">
    <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
        <a href="#" class="navbar-brand">
            <img src="style/icon/karazin-logo.png" width="30" height="30" class="d-inline-block align-top" alt="logo">
            Karazin University</a>

        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse1">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse1">
            <div class="navbar-nav">
                <a href="#" class="nav-item nav-link"><fmt:message key="common.about"/></a>
                <a href="controller?command=showFacultiesForCompetition" class="nav-item nav-link"><fmt:message key="admin.header.showFacultiesForCompetition"/></a>
                <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
            </div>
            <form class="form-inline ml-auto">
                <a href="controller?command=logout" class="nav-item nav-link active"><fmt:message key="common.logout"/></a>
            </form>
        </div>
    </nav>
</div>

<div class="general">

    <div class="block">
        <div class="table-mark">

            <h3><fmt:message key="admin.result.enrolledTableHead"/></h3>
            <core:choose>
                <core:when test="${not empty enrolled}">
                    <table class="table table-light table-striped table-hover">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"><fmt:message key="admin.result.facultyName"/></th>
                            <th scope="col"><fmt:message key="admin.listEnrollees.firstName"/></th>
                            <th scope="col"><fmt:message key="admin.listEnrollees.secName"/></th>
                            <th scope="col"><fmt:message key="admin.listEnrollees.lastName"/></th>
                            <th scope="col"><fmt:message key="admin.listEnrollees.city"/></th>
                            <th scope="col">@Email</th>
                        </tr>
                        </thead>
                        <tbody>
                        <core:set var="k" value="0"/>
                        <core:forEach var="list" items="${enrolled}">

                            <core:choose>
                                <core:when test="${not empty list.value}">

                                    <core:forEach var="listItem" items="${list.value}">
                                        <core:set var="k" value="${k+1}"/>
                                        <tr>
                                            <td>${k}</td>
                                            <td>${list.key.name}</td>
                                            <td>${listItem.firstName}</td>
                                            <td>${listItem.secName}</td>
                                            <td>${listItem.lastName}</td>
                                            <td>${listItem.city}</td>
                                            <td>${listItem.email}</td>
                                        </tr>
                                    </core:forEach>

                                </core:when>
                            </core:choose>

                        </core:forEach>
                        </tbody>
                    </table>
                </core:when>
                <core:otherwise><h4><fmt:message key="error.enrolled.empty"/></h4></core:otherwise>
            </core:choose>

        </div>
    </div>

    <div class="block">
        <div class="table-mark">

            <h3><fmt:message key="admin.result.notEnrolledTableHead"/></h3>
            <core:choose>
                <core:when test="${not empty notEnrolled}">
                    <table class="table table-light table-striped table-hover">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"><fmt:message key="admin.result.facultyName"/></th>
                            <th scope="col"><fmt:message key="admin.listEnrollees.firstName"/></th>
                            <th scope="col"><fmt:message key="admin.listEnrollees.secName"/></th>
                            <th scope="col"><fmt:message key="admin.listEnrollees.lastName"/></th>
                            <th scope="col"><fmt:message key="admin.listEnrollees.city"/></th>
                            <th scope="col">@Email</th>
                        </tr>
                        </thead>
                        <tbody>
                        <core:set var="k" value="0"/>
                        <core:forEach var="list" items="${notEnrolled}">

                            <core:choose>
                                <core:when test="${not empty list.value}">

                                    <core:forEach var="listItem" items="${list.value}">
                                        <core:set var="k" value="${k+1}"/>
                                        <tr>
                                            <td>${k}</td>
                                            <td>${list.key.name}</td>
                                            <td>${listItem.firstName}</td>
                                            <td>${listItem.secName}</td>
                                            <td>${listItem.lastName}</td>
                                            <td>${listItem.city}</td>
                                            <td>${listItem.email}</td>
                                        </tr>
                                    </core:forEach>

                                </core:when>
                            </core:choose>

                        </core:forEach>
                        </tbody>
                    </table>
                </core:when>
                <core:otherwise><h4><fmt:message key="error.notEnrolled.empty"/></h4></core:otherwise>
            </core:choose>

        </div>
    </div>

</div>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
