<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 05.04.2020
  Time: 21:58
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<fmt:setLocale value="${locale}"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css" media="screen" href="style/css/actionWithEnroleesStyles.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <title>List enrollees</title>
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
                    <select class="mdb-select md-form colorful-select dropdown-primary" name="sort">
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


<div class="small">
    <div class="col align-self-center">
        <c:choose>
            <c:when test="${not empty enrolleesList}">

                <table class="table table-dark table-striped table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">First name</th>
                        <th scope="col">Second name</th>
                        <th scope="col">Last name</th>
                        <th scope="col">City</th>
                        <th scope="col">@Email</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${enrolleesList}">

                        <tr>
                            <th>${item.id}</th>
                            <th>${item.firstName}</th>
                            <td>${item.secName}</td>
                            <td>${item.lastName}</td>
                            <td>${item.city}</td>
                            <td>${item.email}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${item.accessAllowed==true}">
                                        <form action="controller" method="get">
                                            <input type="hidden" name="command" value="actionWithEnrollees">

                                            <input type="hidden" name="block" value="${item.id}">
                                                <%--                                            <input type="hidden" name="id_enrollee" value="${item.id}">--%>
                                            <input class="btn btn-warning" type="submit" value="block">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="controller" method="get">
                                            <input type="hidden" name="command" value="actionWithEnrollees">

                                            <input type="hidden" name="unblock" value="${item.id}">
                                                <%--                                            <input type="hidden" name="id_enrollee" value="${item.id}">--%>
                                            <input class="btn btn-danger" type="submit" value="unblock">
                                        </form>
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

<script lang="javascript">
    // Material Select Initialization
    $(document).ready(function () {
        $('.mdb-select').materialSelect();
    });
</script>
</body>
</html>

