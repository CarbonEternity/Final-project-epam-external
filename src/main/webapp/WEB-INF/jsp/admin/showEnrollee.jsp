<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 11.04.2020
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css" media="screen" href="style/css/showEnrolleeStyles.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <title>Enrolee info</title>
</head>
<style>
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
    <button type="submit" name= "back" class="btn btn-lg btn-outline-warning" id="button1">&#8592; Back</button>
    <button type="submit" name="admit" class="btn btn-lg btn-outline-warning" id="button2">Admit</button>
    </form>
</div>

<div class="general">

    <div class="block">
        <div class="info">
            <h3>INFO</h3>
            <p><b>Name: </b> ${enroleeInfo.firstName}</p>
            <p><b>Second-Name: </b> ${enroleeInfo.secName}</p>
            <p><b>Last-Name: </b> ${enroleeInfo.lastName}</p>
            <p><b>Email: </b> ${enroleeInfo.email}</p>
            <p><b>City: </b> ${enroleeInfo.city}</p>
            <p><b>Region: </b> ${enroleeInfo.region}</p>
            <p><b>School: </b> ${enroleeInfo.school}</p>
        </div>
    </div>



    <div class="block">
        <div class="zno">

            <h3>ZNO</h3>
            <c:choose>
                <c:when test="${not empty enroleeZno}">
                    <table class="table table-light table-striped table-hover">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">Discipline</th>
                            <th scope="col">Mark</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="zno" items="${enroleeZno}">
                            <tr>
                                <td>${zno.disciplineName}</td>
                                <td>${zno.mark}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise><h4><fmt:message key="error.enroleeZno.empty"/></h4></c:otherwise>
            </c:choose>

        </div>


        <form action="controller" method="post">
            <input type="hidden" name="command" value="showEnrolleeCertificate">
            <input type="hidden" name="id_enr" value="${enroleeInfo.id}">
            <input type="hidden" name="id_fac" value="${id_fac}">

            <button class="knopka btn btn-lg btn-outline-warning">BOGDAN JOPA</button>
        </form>

    </div>



    <div class="block">
        <div class="table-mark">

            <h3>CERTIFICATE</h3>
            <c:choose>
                <c:when test="${not empty enroleeCertificate}">
                    <table class="table table-light table-striped table-hover">
                        <thead class="thead-dark">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Dicipline</th>
                            <th scope="col">Mark</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set var="k" value="0"/>
                        <c:forEach var="certificate" items="${enroleeCertificate}">
                            <c:set var="k" value="${k+1}"/>
                            <tr>
                                <td>${k}</td>
                                <td>${certificate.disciplineName}</td>
                                <td>${certificate.mark}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise><h4><fmt:message key="error.enroleeCertificate.empty"/></h4></c:otherwise>
            </c:choose>

        </div>

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
