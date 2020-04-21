<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 21.03.2020
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="style/css/userHome.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <title>Home Page</title>
</head>
<body>

    <div class="bs-example">
        <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
            <a href="#" class="navbar-brand">Home</a>
            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse1">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarCollapse1">
                <div class="navbar-nav">
                    <a href="controller?command=sortFacultyList" class="nav-item nav-link active">All faculties</a>
                    <a href="#" class="nav-item nav-link">Never again)</a>
                    <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
                </div>
                <form class="form-inline ml-auto">
                    <a href="controller?command=logout" class="nav-item nav-link active">Logout</a>
                </form>
            </div>
        </nav>
    </div>

    <form action="controller" method="post">
        <input type="hidden" name="command" value="changeApplication">

            <div class="small">
                <div class="col align-self-center">
                    <c:choose>
                        <c:when test="${not empty listApplications}">

                            <table class="table table-striped table-hover">
                                <thead class="thead-dark">
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Status</th>
                                    <th scope="col">Action</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${listApplications}">
                                    <tr>
                                        <td>${item.name}</td>
                                        <td>Allowed</td>
                                        <td>
                                            <form action="controller" method="get">
                                                <input type="hidden" name="command" value="deleteApplication">
                                                <input type="hidden" name="id_faculty" value="${item.id}">
                                                <input class="btn btn-primary" type="submit"
                                                       value="delete application">
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


</body>
</html>
