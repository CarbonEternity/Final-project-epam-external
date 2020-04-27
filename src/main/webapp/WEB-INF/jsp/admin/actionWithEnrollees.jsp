<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 05.04.2020
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="style/css/actionsWithEnroleesStyles.css">
    <title><fmt:message key="title.actionsWithEnrolees"/></title>
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
                    <input type="hidden" name="command" value="sortEnrolleeList">
                    <!-- select-->
                    <select class="mdb-select md-form colorful-select dropdown-primary" name="sort">
                        <option selected disabled><fmt:message key="common.selectSort"/></option>
                        <optgroup label="<fmt:message key="admin.sort.enrollee.byLastName"/>">
                            <option value="namefirst"><fmt:message key="admin.sort.enrollee.NameAZ"/></option>
                            <option value="namelast"><fmt:message key="admin.sort.enrollee.NameZA"/></option>
                        </optgroup>
                    </select>
                    <!--/ select-->
                    <button type="submit" class="btn btn-outline-light"><fmt:message key="common.sort.sortButton"/></button>
                    <a href="controller?command=logout" class="nav-item nav-link active"><fmt:message key="common.logout"/></a>
                </form>
            </form>
        </div>
    </nav>
</div>


<div class="small">
    <div class="col align-self-center">
        <c:choose>
            <c:when test="${not empty enrolleesList}">

                <table class="table table-dark table-striped table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col"><fmt:message key="admin.listEnrollees.firstName"/></th>
                        <th scope="col"><fmt:message key="admin.listEnrollees.secName"/></th>
                        <th scope="col"><fmt:message key="admin.listEnrollees.lastName"/></th>
                        <th scope="col"><fmt:message key="admin.listEnrollees.city"/></th>
                        <th scope="col">@Email</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="k" value="0"/>
                    <c:forEach var="item" items="${enrolleesList}">
                        <c:set var="k" value="${k+1}"/>
                        <tr>
                            <td>${k}</td>
                            <td>${item.firstName}</td>
                            <td>${item.secName}</td>
                            <td>${item.lastName}</td>
                            <td>${item.city}</td>
                            <td>${item.email}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${item.accessAllowed==true}">
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="actionWithEnrollees">

                                            <input type="hidden" name="block" value="${item.id}">
                                            <input class="btn btn-warning" type="submit" value="<fmt:message key="admin.listEnrollees.lock"/>">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="actionWithEnrollees">

                                            <input type="hidden" name="unblock" value="${item.id}">
                                            <input class="btn btn-danger" type="submit" value="<fmt:message key="admin.listEnrollees.unlock"/>">
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise>
                <div class="errorBlock">
                    <div class="errorMessage">
                        <p>
                        <h4><fmt:message key="error.enrolleesList.empty"/></h4>
                        </p>
                    </div>
                </div>
                </c:otherwise>
        </c:choose>

    </div>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>

