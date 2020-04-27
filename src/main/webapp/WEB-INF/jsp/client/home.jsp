<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 21.03.2020
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" href="style/css/userHome.css">
    <title><fmt:message key="title.Home"/></title>
</head>
<body>

    <div class="bs-example">
        <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
            <a href="#" class="navbar-brand">
                <img src="style/icon/karazin-logo.png" width="30" height="30" class="d-inline-block align-top" alt="logo">
                Karazin University</a>

            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse1">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarCollapse1">
                <div class="navbar-nav">
                    <a href="controller?command=sortFacultyList" class="nav-item nav-link"><fmt:message key="client.header.allFaculties"/></a>
                    <a href="#" class="nav-item nav-link"><fmt:message key="common.about"/></a>
                    <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
                </div>
                <form class="form-inline ml-auto">
                    <a href="controller?command=logout" class="nav-item nav-link active"><fmt:message key="common.logout"/></a>
                </form>
            </div>
        </nav>
    </div>

    <form>
            <div class="small">
                <div class="col align-self-center">
                    <c:choose>
                        <c:when test="${not empty listApplications}">

                            <table class="table table-light table-striped table-hover">
                                <thead class="thead-dark">
                                <tr>
                                    <th scope="col"><fmt:message key="table.faculties.name"/></th>
                                    <th scope="col"><fmt:message key="table.faculties.status"/></th>
                                    <th scope="col"><fmt:message key="table.faculties.action"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${listApplications}">
                                    <tr>
                                        <td>${item.name}</td>
                                        <td><fmt:message key="table.faculties.statusAllowed"/></td>
                                        <td>
                                            <form action="controller" method="post">
                                                <input type="hidden" name="command" value="deleteApplication">
                                                <input type="hidden" name="id_faculty" value="${item.id}">
                                                <input class="btn btn-primary" type="submit"
                                                       value="<fmt:message key="client.facultiesTable.deleteApplication"/>">
                                            </form>
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
    </form>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
