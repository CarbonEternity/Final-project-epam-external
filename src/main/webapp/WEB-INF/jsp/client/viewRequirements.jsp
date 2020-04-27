<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 24.03.2020
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="style/css/viewRequirementsStyles.css">
    <title><fmt:message key="title.requirements"/></title>
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
                <a href="controller?command=sortFacultyList" class="nav-item nav-link"><fmt:message
                        key="client.header.allFaculties"/></a>
                <a href="controller?command=userHome" class="nav-item nav-link"><fmt:message key="client.home"/></a>
                <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
            </div>
            <form class="form-inline ml-auto">
                <a href="controller?command=logout" class="btn btn-primary" id="logoutButton"><fmt:message
                        key="common.logout"/></a>
            </form>
        </div>
    </nav>
</div>
<div class="content">

    <form action="controller" method="post">
        <input type="hidden" name="command" value="createOrder">
        <input type="hidden" name="title" value=${facultyInfo.name}>

        <div class="info-block">
            <p>${facultyInfo.name}</p>
            <div class="faculty_info">
                <ul>
                    <li><fmt:message key="common.faculty.info.countBudget"/> ${facultyInfo.countBudget}</li>
                    <li><fmt:message key="common.faculty.info.countTotal"/> ${facultyInfo.countTotal}</li>
                </ul>
            </div>
            <input id="want" class="btn btn-success" type="submit"
                   value="<fmt:message key="common.faculty.applyForStudy"/>">
        </div>


        <div class="block">

            <div class="row">

                <div class="one">
                    <div class="col align-self-center">
                        <c:choose>
                            <c:when test="${not empty disciplineList}">

                                <table class="table table-light table-striped table-hover">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th scope="col"> #</th>
                                        <th scope="col"><fmt:message key="table.faculties.name"/></th>
                                        <th scope="col"><fmt:message key="table.faculties.recommended_mark"/></th>
                                        <th scope="col"><fmt:message key="table.faculties.your_mark"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="k" value="0"/>
                                    <c:forEach var="item" items="${disciplineList}">
                                        <c:set var="k" value="${k+1}"/>
                                        <tr>
                                            <td>${k}</td>
                                            <td>${item.disciplineName}</td>
                                            <td>${item.mark}</td>
                                            <td>
                                                <input type="number" min="100" max="200" required
                                                       name="zno_${item.disciplineName}" class="form-control">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise><h4><fmt:message key="error.disciplineList.empty"/></h4>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>

                <div class="small">
                    <div class="col align-self-center">
                        <c:choose>
                            <c:when test="${not empty certificateDisciplineList}">

                                <table class="table table-dark table-striped table-hover">
                                    <thead class="thead-dark">
                                    <tr>
                                        <th scope="col"> #</th>
                                        <th scope="col"><fmt:message key="table.faculties.disciplinesName"/></th>
                                        <th scope="col"><fmt:message key="table.faculties.disciplinesMark"/></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:set var="k" value="0"/>
                                    <c:forEach var="item2" items="${certificateDisciplineList}">
                                        <c:set var="k" value="${k+1}"/>
                                        <tr>
                                            <td>${k}</td>
                                            <td>${item2.disciplineName}</td>
                                            <td><input type="number" required min="1" max="12"
                                                       name="cert_${item2.disciplineName}" class="form-control"></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:when>
                            <c:otherwise><h4><fmt:message key="error.certificateDisciplineList.empty"/></h4>
                            </c:otherwise>
                        </c:choose>


                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
