<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 11.04.2020
  Time: 20:10
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

    <link rel="stylesheet" type="text/css" media="screen" href="style/css/competitionStyles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <title>Competition</title>

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
            <form class="form-inline ml-auto">

                <form action="controller" method="get">
                    <input type="hidden" name="command" value="sortEnrolleeList">
                    <!-- select-->
                    <select class="mdb-select md-form colorful-select dropdown-primary" name="sortForCompetition">
                        <option selected disabled>Select sort</option>
                        <optgroup label="> by last name">
                            <option value="namefirst">Name A-Z</option>
                            <option value="namelast">Name Z-A</option>
                        </optgroup>
                    </select>
                    <!--/ select-->
                    <button type="submit" class="btn btn-outline-light">Sort</button>
                </form>
            </form>
        </div>
    </nav>
</div>

<div id="container">
    <form action="controller" method="get">
        <input type="hidden" name="command" value="competition">
        <button type="submit" name= "back" class="btn btn-lg btn-outline-warning" id="button1">&#8592; Back</button>
    </form>
</div>

<div class="small">
    <div class="col align-self-center">
        <c:choose>
            <c:when test="${not empty enrolleesList}">

                <table class="table table-light table-striped table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">First name</th>
                        <th scope="col">Second name</th>
                        <th scope="col">Last name</th>
                        <th scope="col">City</th>
                        <th scope="col">@Email</th>
                        <th scope="col"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${enrolleesList}">

                        <tr>
                            <td>${item.id}</td>
                            <td>${item.firstName}</td>
                            <td>${item.secName}</td>
                            <td>${item.lastName}</td>
                            <td>${item.city}</td>
                            <td>${item.email}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${admittedEnrollees.contains(item)==false}">
                                        <form action="controller" method="post">
                                            <input type="hidden" name="command" value="competition">
                                            <input type="hidden" name="id_enr" value=${item.id}>
                                            <input type="hidden" name="id_fac" value=${id_fac}>
                                            <!-- select-->
                                            <select class="mdb-select md-form colorful-select dropdown-warning"
                                                    name="action">
                                                <option selected disabled>select action</option>
                                                <option value="show">show profile and marks</option>
                                                <option value="admit">admit to the competition</option>
                                            </select>
                                            <!--/ select-->
                                            <button type="submit" class="btn btn-outline-primary">Action</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <select class="mdb-select md-form colorful-select" name="action">
                                            <option selected disabled>enrollee admitted</option>
                                        </select>
                                        <button type="submit" class="btn btn-outline-success" disabled>No action
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise><h4><fmt:message key="error.enrolleesList.empty"/></h4></c:otherwise>
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


