<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 03.04.2020
  Time: 20:53
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

    <link rel="stylesheet" type="text/css" media="screen" href="style/css/redact.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <title>${faculty.name}</title>

</head>
<body>

<div class="bs-example">
    <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
        <a href="#" class="navbar-brand">Home</a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse1">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse1">
            <div class="navbar-nav">
                <a href="#" class="nav-item nav-link">About</a>
                <a href="#" class="nav-item nav-link">Maybe)</a>
                <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
                <a href="controller?command=logout" class="nav-item nav-link active">Logout</a>
            </div>
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
            <label for="count_budget">Count budget</label>
            <input type="number" min="50" max="200" name="count_budget" value="${faculty.countBudget}"/>

            <label for="count_total">Count total</label>
            <input type="number" min="70" max="220" name="count_total" value="${faculty.countTotal}"/>

        </fieldset>
        <input type="submit" value="save">
    </div>

    <div class="col align-self-center">
        <c:choose>
            <c:when test="${not empty listExams}">

                <table class="table table-light table-striped table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">Name</th>
                        <th scope="col">Mark</th>
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


</body>
</html>
