<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 24.03.2020
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="/WEB-INF/style/css/requirements.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <title>${title}</title>
</head>


<div class="bs-example">
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
        <a href="#" class="navbar-brand">Home</a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse1">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse1">
            <div class="navbar-nav">
                <a href="#" class="nav-item nav-link">About</a>
                <a href="controller?command=sortFacultyList" class="nav-item nav-link">All faculties</a>
                <a href="#" class="nav-item nav-link">Never again)</a>
                <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
            </div>
            <form class="form-inline ml-auto">
                <a href="controller?command=logout" class="nav-item nav-link active">Logout</a>
            </form>
        </div>
    </nav>
</div>


<p>${facultyInfo.name}</p>

<div class="faculty_info">
        <ul>
            <li>Count budget ->  ${facultyInfo.countBudget}</li>
            <li>Count total ->  ${facultyInfo.countTotal}</li>
        </ul>
</div>


    <form action="controller" method="post">
        <input type="hidden" name="command" value="createOrder">
        <input type="hidden" name="title" value=${facultyInfo.name}>
        <div class="row">

            <div class="small">
                <div class="col align-self-center">
                    <c:choose>
                        <c:when test="${not empty disciplineList}">

                            <table class="table table-light table-striped table-hover">
                                <thead class="thead-dark">
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Minimal needed mark</th>
                                    <th scope="col">Your mark</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${disciplineList}">
                                    <tr>
                                        <td>${item.disciplineName}</td>
                                        <td>${item.minMark}</td>
                                        <td><input type="text" name="zno_${item.disciplineName}" class="form-control"></td>
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
                                    <th scope="col"> Discipline</th>
                                    <th scope="col"> Mark</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item2" items="${certificateDisciplineList}">
                                    <tr>
                                        <td>${item2.id}</td>
                                        <td>${item2.disciplineName}</td>
                                        <td><input type="text" name="cert_${item2.disciplineName}" class="form-control"></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise><h4><fmt:message key="error.certificateDisciplineList.empty"/></h4>
                        </c:otherwise>
                    </c:choose>

                    <input class="btn btn-success" type="submit" value="I want study here!">
                </div>
            </div>
        </div>
    </form>


</body>
</html>
