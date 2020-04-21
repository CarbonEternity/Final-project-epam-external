<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 15.04.2020
  Time: 16:11
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

    <link rel="stylesheet" type="text/css" media="screen" href="style/css/statementStyles.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <title>Statement</title>

</head>
<body>

<div class="bs-example">
    <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
        <a href="controller?command=adminHome" class="navbar-brand">Home</a>
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

<div id="container">
    <form action="controller" method="get">
        <input type="hidden" name="command" value="runCompetition">
        <button type="submit" name="run" class="btn btn-lg btn-outline-warning" id="button1">Run competition</button>
    </form>
</div>

<div class="small">
    <div class="col align-self-center">
        <c:choose>
            <c:when test="${not empty mapOfList}">

                <table class="table table-light table-striped table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Faculty</th>
                        <th scope="col">First name</th>
                        <th scope="col">Second name</th>
                        <th scope="col">Last name</th>
                        <th scope="col">City</th>
                        <th scope="col">@Email</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:set var="k" value="0"/>
                    <c:forEach var="list" items="${mapOfList}">

                        <c:choose>
                            <c:when test="${not empty list.value}">

                                <c:forEach var="listItem" items="${list.value}">
                                    <c:set var="k" value="${k+1}"/>
                                    <tr>
                                        <td>${k}</td>
                                        <td>${list.key.name}</td>
                                        <td>${listItem.firstName}</td>
                                        <td>${listItem.secName}</td>
                                        <td>${listItem.lastName}</td>
                                        <td>${listItem.city}</td>
                                        <td>${listItem.email}</td>
                                    </tr>
                                </c:forEach>

                            </c:when>
                        </c:choose>

                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise><h4><fmt:message key="error.mapOfList.empty"/></h4></c:otherwise>
        </c:choose>

    </div>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>

</body>
</html>



