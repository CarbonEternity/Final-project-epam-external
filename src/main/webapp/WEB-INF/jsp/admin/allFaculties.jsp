<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 30.03.2020
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="style/css/allFacultiesStyles.css">
    <title><fmt:message key="title.allFaculties"/></title>
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

                <form action="controller" method="get">
                    <input type="hidden" name="command" value="sortFaculties">
                    <!-- select-->
                    <select class="mdb-select md-form colorful-select dropdown-primary" name="sort">
                        <option selected disabled><fmt:message key="common.selectSort"/></option>
                        <optgroup label="<fmt:message key="common.sort.label.by.count"/>">
                            <option value="count_budget"><fmt:message key="common.sort.countBudget"/></option>
                            <option value="count_total"><fmt:message key="common.sort.countTotal"/></option>
                        </optgroup>
                        <optgroup label="<fmt:message key="common.sort.label.by.name"/>">
                            <option value="namefirst"><fmt:message key="common.sort.NameAZ"/></option>
                            <option value="namelast"><fmt:message key="common.sort.NameZA"/></option>
                        </optgroup>
                    </select>
                    <!--/ select-->
                    <button type="submit" class="btn btn-outline-light"><fmt:message key="common.sort.sortButton"/></button>
                </form>

                <a href="controller?command=logout" class="nav-item nav-link active"><fmt:message key="common.logout"/></a>
            </form>
        </div>
    </nav>
</div>

<form action="controller" method="get">
    <input type="hidden" name="command" value="addFaculty">
    <div class="col add text-center addFaculty">
        <button type="submit" class="btn btn-lg btn-outline-light"><fmt:message key="admin.faculties.addFaculty"/></button>
    </div>
</form>

<div class="small">
    <div class="col align-self-center">
        <c:choose>
            <c:when test="${not empty listFaculties}">

                <table class="table table-light table-striped table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col"><fmt:message key="table.faculties.name"/></th>
                        <th scope="col"><fmt:message key="table.faculties.budget"/></th>
                        <th scope="col"><fmt:message key="table.faculties.total"/></th>
                        <th scope="col"><fmt:message key="table.faculties.action"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="k" value="0"/>
                    <c:forEach var="item" items="${listFaculties}">
                        <c:set var="k" value="${k+1}"/>
                        <tr>
                            <td>${k}</td>
                            <td>${item.name}</td>
                            <td>${item.countBudget}</td>
                            <td>${item.countTotal}</td>
                            <td>
                                    <form action="controller" method="get">
                                        <input type="hidden" name="command" value="changeFaculty">
                                        <input type="hidden" name="id" value=${item.id}>
                                        <!-- select-->
                                        <select class="mdb-select md-form colorful-select dropdown-primary" name="action">
                                            <option value="edit"><fmt:message key="admin.faculties.editFaculty"/></option>
                                            <option value="delete"><fmt:message key="admin.faculties.deleteFaculty"/></option>
                                        </select>
                                        <!--/ select-->
                                        <button type="submit" class="btn btn-outline-primary"><fmt:message key="admin.faculties.makeAction"/></button>
                                    </form>
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise><h4><fmt:message key="error.listFaculties.empty"/></h4></c:otherwise>
        </c:choose>

    </div>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
