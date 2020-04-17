<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 17.04.2020
  Time: 00:46
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css" media="screen" href="style/css/showEnrolleeStyles.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <title>Result</title>
</head>
<style>
    body {
        background-color: orange;
        background-size: cover;
    }

    .block {
        padding: 30px;
        float:left;
    }


    .table-mark{

        color:whitesmoke;
        border-collapse:collapse;
        padding: 30px;
        background: rgba(60, 63, 65, 0.6);
    }

    .general {
        position: absolute;
        margin-left: 150px;
        text-align:center;
    }

    h3{
        text-align: center;
    }

    .table,
    .table tr,
    .table td {
        font-size: 16px;
    }

    .bs-example {
        margin: 0;
    }

    .navbar {
        position: relative;
    }

    #button1{
        width: 300px;
        height: 40px;
    }
    #button2{
        width: 300px;
        height: 40px;
    }
    #container{
        padding-top: 30px;
        text-align: center;
    }

</style>
<body>


<div class="bs-example">
    <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
        <a href="controller?command=adminHome" class="navbar-brand">Home</a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse1">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse1">
            <div class="navbar-nav">
                <a href="controller?command=showFacultiesForCompetition" class="nav-item nav-link">Faculties with applications</a>
                <a href="#" class="nav-item nav-link">About</a>
                <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
            </div>
            <form class="form-inline ml-auto">
                <a href="controller?command=logout" class="nav-item nav-link active">Logout</a>
            </form>
        </div>
    </nav>
</div>


<div id="container">
    <form action="controller" method="get">
        <input type="hidden" name="command" value="showEnrolee">
        <input type="hidden" name="id_enr" value="${enroleeInfo.id}">
        <input type="hidden" name="id_fac" value="${id_fac}">
        <button type="submit" name= "back" class="btn btn-lg btn-outline-primary" id="button1">&#8592; Back</button>
        <button type="submit" name="admit" class="btn btn-lg btn-outline-primary" id="button2">Admit</button>
    </form>
</div>

<div class="general">

    <div class="block">
        <div class="table-mark">

            <h3>ENROLLED</h3>
            <c:choose>
                <c:when test="${not empty enrolled}">
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
                        <c:forEach var="list" items="${enrolled}">

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
                <c:otherwise><h4><fmt:message key="error.enrolled.empty"/></h4></c:otherwise>
            </c:choose>

        </div>
    </div>

    <div class="block">
        <div class="table-mark">

            <h3>NOT ENROLLED</h3>
            <c:choose>
                <c:when test="${not empty notEnrolled}">
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
                        <c:forEach var="list" items="${notEnrolled}">

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
                <c:otherwise><h4><fmt:message key="error.notEnrolled.empty"/></h4></c:otherwise>
            </c:choose>

        </div>
    </div>

</div>
</body>
</html>
