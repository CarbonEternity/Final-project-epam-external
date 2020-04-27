<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 21.03.2020
  Time: 23:56
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="style/css/listFaculties.css">
</head>
<body>

<div class="bs-example">
    <nav class="navbar  fixed-top navbar-expand-lg navbar-dark bg-dark">
        <a href="#" class="navbar-brand">
            <img src="style/icon/karazin-logo.png" width="30" height="30" class="d-inline-block align-top" alt="logo">
            Karazin University</a>

        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse1">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse1">
            <div class="navbar-nav">
                <a href="#" class="nav-item nav-link"><fmt:message key="common.about"/></a>
                <a href="controller?command=userHome" class="nav-item nav-link"><fmt:message key="client.home"/></a>
                <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>

                <form action="controller" method="get">
                    <input type="hidden" name="command" value="changeLocale">
                    <!-- select-->
                    <select class="mdb-select md-form colorful-select dropdown-primary" name="locale">
                        <option selected><fmt:message key="common.locale.selectLocale"/></option>
                        <option value="ru"><fmt:message key="common.locale.optionRULocale"/></option>
                        <option value="en"><fmt:message key="common.locale.optionENLocale"/></option>
                    </select>
                    <!--/ select-->
                    <button type="submit" class="btn btn-outline-light"><fmt:message key="common.locale.localeButton"/></button>
                </form>

            </div>
            <form class="form-inline ml-auto">

                <form action="controller" method="get" id="formController">
                    <input type="hidden" name="command" value="sortFacultyList">
                    <!-- select-->
                    <select class="mdb-select md-form colorful-select dropdown-primary" name="sort">
                        <option selected><fmt:message key="common.selectSort"/></option>
                        <optgroup label="<fmt:message key="common.sort.label.by.count"/>">
                            <option value="count_budget"><fmt:message key="client.sort.countBudget"/></option>
                            <option value="count_total"><fmt:message key="client.sort.countTotal"/></option>
                        </optgroup>
                        <optgroup label="<fmt:message key="common.sort.label.by.name"/>">
                            <option value="namefirst"><fmt:message key="client.sort.NameAZ"/></option>
                            <option value="namelast"><fmt:message key="client.sort.NameZA"/></option>
                        </optgroup>
                    </select>
                    <!--/ select-->
                    <button type="submit" class="btn btn-outline-light"><fmt:message key="common.sort.sortButton"/></button>
                </form>

                <a href="controller?command=logout" class="btn btn-primary" id="logoutButton"><fmt:message key="common.logout"/></a>
            </form>
        </div>
    </nav>
</div>


<div class="small">
    <div class="col align-self-center">
        <c:choose>
            <c:when test="${not empty listFaculties}">

                <table class="table table-dark table-striped table-hover">
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
                    <c:forEach var="item" items="${listFaculties}">

                        <tr>
                            <th>${item.id}</th>
                            <td>${item.name}</td>
                            <td>${item.countBudget}</td>
                            <td>${item.countTotal}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${listApplications.contains(item.id)}">
                                        <form action="controller" method="get">
                                            <input type="hidden" name="command" value="deleteApplication">
                                            <input type="hidden" name="id_faculty" value="${item.id}">
                                            <input class="btn btn-primary" type="submit" value="<fmt:message key="client.facultiesTable.deleteApplication"/>">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="controller" method="get">
                                            <input type="hidden" name="command" value="viewFacultyAndRequirements">
                                            <input type="hidden" name="id_faculty" value="${item.id}">
                                            <input class="btn btn-success" type="submit" value="<fmt:message key="client.facultiesTable.showRequirements"/>">
                                        </form>
                                    </c:otherwise>
                                </c:choose>
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
