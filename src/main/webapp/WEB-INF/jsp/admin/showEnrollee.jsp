<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 11.04.2020
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="style/css/showEnrolleeStyles.css">
    <title><fmt:message key="title.enrolleeInfo"/></title>
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
                <a href="controller?command=showFacultiesForCompetition" class="nav-item nav-link"><fmt:message
                        key="admin.header.showFacultiesForCompetition"/></a>
                <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
            </div>
            <form class="form-inline ml-auto">
                <a href="controller?command=logout" class="nav-item nav-link active"><fmt:message
                        key="common.logout"/></a>
            </form>
        </div>
    </nav>
</div>

<div id="container">
    <form action="controller" method="get">
        <input type="hidden" name="command" value="showEnrolee">
        <input type="hidden" name="id_enr" value="${enroleeInfo.id}">
        <input type="hidden" name="id_fac" value="${id_fac}">
        <button type="submit" name="back" class="btn btn-lg btn-outline-warning" id="button1">&#8592; <fmt:message
                key="common.back"/></button>
        <button type="submit" name="admit" class="btn btn-lg btn-outline-warning" id="button2"><fmt:message
                key="admin.listEnrollees.admit"/></button>
    </form>
</div>

<div class="general">

    <div class="block">
        <div class="info">
            <h3><fmt:message key="admin.enrolleeInfo.info"/></h3>
            <p><b><fmt:message key="admin.enrolleeInfo.name"/>: </b> ${enroleeInfo.firstName}</p>
            <p><b><fmt:message key="admin.enrolleeInfo.secname"/>: </b> ${enroleeInfo.secName}</p>
            <p><b><fmt:message key="admin.enrolleeInfo.lastName"/>: </b> ${enroleeInfo.lastName}</p>
            <p><b>Email: </b> ${enroleeInfo.email}</p>
            <p><b><fmt:message key="admin.enrolleeInfo.city"/>: </b> ${enroleeInfo.city}</p>
            <p><b><fmt:message key="admin.enrolleeInfo.region"/>: </b> ${enroleeInfo.region}</p>
            <p><b><fmt:message key="admin.enrolleeInfo.school"/>: </b> ${enroleeInfo.school}</p>
        </div>
    </div>


    <div class="block">
        <div class="zno">

            <h3>ZNO</h3>
            <c:choose>
                <c:when test="${not empty enroleeZno}">
                    <table class="table table-light table-striped table-hover">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col"><fmt:message key="admin.faculties.createFaculty.disciplineName"/></th>
                            <th scope="col"><fmt:message key="admin.faculties.createFaculty.disciplineMark"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="zno" items="${enroleeZno}">
                            <tr>
                                <td>${zno.disciplineName}</td>
                                <td>${zno.mark}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise><h4><fmt:message key="error.enroleeZno.empty"/></h4></c:otherwise>
            </c:choose>

        </div>


        <form action="controller" method="post">
            <input type="hidden" name="command" value="showEnrolleeCertificate">
            <input type="hidden" name="id_enr" value="${enroleeInfo.id}">
            <input type="hidden" name="id_fac" value="${id_fac}">

            <button class="knopka btn btn-lg btn-outline-warning"><fmt:message
                    key="admin.enrolleeInfo.showCertificate"/></button>
        </form>

    </div>


    <div class="block">
        <div class="table-mark">

            <h3><fmt:message key="admin.enrolleeInfo.certificate"/></h3>
            <c:choose>
                <c:when test="${not empty enroleeCertificate}">
                    <table class="table table-light table-striped table-hover">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col"><fmt:message key="admin.faculties.createFaculty.disciplineName"/></th>
                            <th scope="col"><fmt:message key="admin.faculties.createFaculty.disciplineMark"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="k" value="0"/>
                        <c:forEach var="certificate" items="${enroleeCertificate}">
                            <c:set var="k" value="${k+1}"/>
                            <tr>
                                <td>${k}</td>
                                <td>${certificate.disciplineName}</td>
                                <td>${certificate.mark}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise><h4><fmt:message key="error.enroleeCertificate.empty"/></h4></c:otherwise>
            </c:choose>

        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
