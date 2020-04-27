<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 03.04.2020
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="style/css/redact.css">
    <title><fmt:message key="title.redactFaculty"/></title>
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
                <a href="controller?command=adminHome" class="nav-item nav-link"><fmt:message key="admin.home"/></a>
                <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
            </div>
            <form class="form-inline ml-auto">
                <a href="controller?command=logout" class="nav-item nav-link active"><fmt:message
                        key="common.logout"/></a>
            </form>
        </div>
    </nav>
</div>


<form id="redact_form" action="controller" method="post">
    <input type="hidden" name="command" value="redactFaculty"/>
    <input type="hidden" name="faculty_id" value="${faculty.id}"/>
    <input type="hidden" name="faculty_name" value="${faculty.name}"/>

    <div class="form-inner">

        <fieldset>
            <legend align="center">${faculty.name}</legend>
            <label for="count_budget"><fmt:message key="admin.faculties.createFaculty.countBudget"/></label>
            <input type="number" min="50" max="200" name="count_budget" value="${faculty.countBudget}"/>

            <label for="count_total"><fmt:message key="admin.faculties.createFaculty.countTotal"/></label>
            <input type="number" min="70" max="220" name="count_total" value="${faculty.countTotal}"/>

        </fieldset>
        <input type="submit" value="<fmt:message key="admin.faculties.saveButton"/>">
    </div>

    <div class="col align-self-center">
        <c:choose>
            <c:when test="${not empty listExams}">

                <table class="table table-light table-striped table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col"><fmt:message key="admin.faculties.createFaculty.disciplineName"/></th>
                        <th scope="col"><fmt:message key="admin.faculties.createFaculty.disciplineMark"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${listExams}">
                        <tr>
                            <td>
                                <select class="mdb-select md-form colorful-select dropdown-primary" name="disciplineName">
                                    <option selected value="${item.disciplineName}">${item.disciplineName}</option>

                                    <c:forEach var="newDisc" items="${allDisciplines}">
                                        <option value="${newDisc}">${newDisc}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td><input type="number" step="10" min="100" name="mark"  max="200" value="${item.mark}"></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise><h4><fmt:message key="error.listExams.empty"/></h4></c:otherwise>
        </c:choose>

    </div>

</form>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
