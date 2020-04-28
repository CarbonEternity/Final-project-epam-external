<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 11.04.2020
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="style/css/competitionStyles.css">
    <title><fmt:message key="title.competition"/></title>
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
                <a href="controller?command=logout" class="nav-item nav-link active"><fmt:message key="common.logout"/></a>
            </form>
        </div>
    </nav>
</div>

<div id="container">
    <form action="controller" method="get">
        <input type="hidden" name="command" value="competition">
        <button type="submit" name= "back" class="btn btn-lg btn-outline-warning" id="button1">&#8592; <fmt:message key="common.back"/></button>
    </form>
</div>

<div class="small">
    <div class="col align-self-center">
        <core:choose>
            <core:when test="${not empty enrolleesList}">

                <table class="table table-light table-striped table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col"><fmt:message key="admin.listEnrollees.firstName"/></th>
                        <th scope="col"><fmt:message key="admin.listEnrollees.secName"/></th>
                        <th scope="col"><fmt:message key="admin.listEnrollees.lastName"/></th>
                        <th scope="col"><fmt:message key="admin.listEnrollees.city"/></th>
                        <th scope="col">@Email</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <core:set var="k" value="0"/>
                    <core:forEach var="item" items="${enrolleesList}">
                        <core:set var="k" value="${k+1}"/>
                        <tr>
                            <td>${k}</td>
                            <td>${item.firstName}</td>
                            <td>${item.secName}</td>
                            <td>${item.lastName}</td>
                            <td>${item.city}</td>
                            <td>${item.email}</td>
                            <td>
                                <core:choose>
                                    <core:when test="${admittedEnrollees.contains(item)==false}">
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="competition">
                                            <input type="hidden" name="id_enr" value=${item.id}>
                                            <input type="hidden" name="id_fac" value=${id_fac}>
                                            <!-- select-->
                                            <select class="mdb-select md-form colorful-select dropdown-warning"
                                                    name="action">
                                                <option selected disabled><fmt:message key="admin.listEnrollees.selectAction"/></option>
                                                <option value="show"><fmt:message key="admin.listEnrollees.showProfile"/></option>
                                                <option value="admit"><fmt:message key="admin.listEnrollees.admit"/></option>
                                            </select>
                                            <!--/ select-->
                                            <button type="submit" class="btn btn-outline-primary"><fmt:message key="common.button.apply"/></button>
                                        </form>
                                    </core:when>
                                    <core:otherwise>
                                        <select class="mdb-select md-form colorful-select" name="action">
                                            <option selected disabled><fmt:message key="admin.listEnrollees.enrolleeAdmitted"/></option>
                                        </select>
                                        <button type="submit" class="btn btn-outline-success" disabled><fmt:message key="admin.listEnrollees.noActionButton"/>
                                        </button>
                                    </core:otherwise>
                                </core:choose>
                            </td>
                        </tr>

                    </core:forEach>
                    </tbody>
                </table>
            </core:when>
            <core:otherwise>
                <div class="errorBlock">
                    <div class="errorMessage">
                        <p>
                        <h4><fmt:message key="error.enrolleesList.empty"/></h4>
                        </p>
                    </div>
                </div>
                </core:otherwise>
        </core:choose>

    </div>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>


