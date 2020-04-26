<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 21.03.2020
  Time: 23:56
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

    <link rel="stylesheet" href="style/css/listFaculties.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <title>List faculties</title>
</head>
<body>

<div class="bs-example">
    <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
        <a href="#" class="navbar-brand"><fmt:message key="common.home"/></a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse1">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse1">
            <div class="navbar-nav">
                <a href="#" class="nav-item nav-link"><fmt:message key="common.about"/></a>
                <a href="#" class="nav-item nav-link">Karazin University</a>
                <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
                <a href="controller?command=logout" class="nav-item nav-link active"><fmt:message key="common.logout"/></a>
                <form action="controller" method="get">
                    <input type="hidden" name="command" value="changeLocale">
                    <!-- select-->
                    <select class="mdb-select md-form colorful-select dropdown-primary" name="locale">
                        <option selected><fmt:message key="common.locale.selectLocale"/></option>
                            <option value="ru"><fmt:message key="common.locale.optionRULocale"/></option>
                            <option value="en"><fmt:message key="common.locale.optionENLocale"/></option>
                    </select>
                    <!--/ select-->
                    <button type="submit" class="btn btn-outline-light"><fmt:message key="common.locale.localeButton"/></button>
                </form>
            </div>
            <form class="form-inline ml-auto">

                <form action="controller" method="get">
                    <input type="hidden" name="command" value="sortFacultyList">
                    <!-- select-->
                    <select class="mdb-select md-form colorful-select dropdown-primary" name="sort">
                        <option selected><fmt:message key="common.selectSort"/></option>
                        <optgroup label="> by count">
                            <option value="count_budget"><fmt:message key="client.sort.countBudget"/></option>
                            <option value="count_total"><fmt:message key="client.sort.countTotal"/></option>
                        </optgroup>
                        <optgroup label="> by name">
                            <option value="namefirst"><fmt:message key="client.sort.NameAZ"/></option>
                            <option value="namelast"><fmt:message key="client.sort.NameZA"/></option>
                        </optgroup>
                    </select>
                    <!--/ select-->
                    <button type="submit" class="btn btn-outline-light"><fmt:message key="common.sort.sortButton"/></button>
                </form>
            </form>
        </div>
    </nav>
</div>


<div class="small">
    <div class="col align-self-center">
        <c:choose>
            <c:when test="${not empty listFaculties}">

                <table class="table table-dark table-striped table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col"><fmt:message key="table.faculties.name"/></th>
                        <th scope="col"><fmt:message key="table.faculties.budget"/></th>
                        <th scope="col"><fmt:message key="table.faculties.total"/></th>
                        <th scope="col"><fmt:message key="table.faculties.action"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${listFaculties}">

                        <tr>
                            <th>${item.id}</th>
                            <td>${item.name}</td>
                            <td>${item.countBudget}</td>
                            <td>${item.countTotal}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${listApplications.contains(item.id)}">
                                        <form action="controller" method="get">
                                            <input type="hidden" name="command" value="deleteApplication">
                                            <input type="hidden" name="id_faculty" value="${item.id}">
                                            <input class="btn btn-primary" type="submit" value="delete application">
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form action="controller" method="get">
                                            <input type="hidden" name="command" value="viewFacultyAndRequirements">
                                            <input type="hidden" name="id_faculty" value="${item.id}">
                                            <input class="btn btn-success" type="submit" value="show requirements">
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:otherwise><h4><fmt:message key="error.listFaculties.empty"/></h4></c:otherwise>
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
