<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 30.03.2020
  Time: 18:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="style/css/allFaculties.css">
    <link rel="stylesheet" href="allFaculties.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>${title}</title>


    <style>
        body {
            /* Full height */
            height: 100%;

            background: url('admin-back.jpg') no-repeat center;
            background-size: cover;
        }

        .small {
            margin: 20px auto;
            width: 30%;
        }

        .table,
        .table tr,
        .table td {
            /*background-color: #e2e2e2 !important;*/
            font-size: 16px;
        }

        .bs-example {
            margin: 0;
        }

        .navbar {
            position: relative;
        }
    </style>
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
            <form class="form-inline ml-auto">

                <form action="controller" method="get">
                    <input type="hidden" name="command" value="sortFaculties">
                    <!-- select-->
                    <select class="mdb-select md-form colorful-select dropdown-primary" name="sort">
                        <option selected>Select sort</option>
                        <optgroup label="> by count">
                            <option value="count_budget">Count budget</option>
                            <option value="count_total">Count total</option>
                        </optgroup>
                        <optgroup label="> by name">
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
            <c:when test="${not empty listFaculties}">

                <table class="table table-light table-striped table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Budget</th>
                        <th scope="col">Total</th>
                        <th scope="col">Action</th>
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
                                    <form action="controller" method="get">
                                        <input type="hidden" name="command" value="changeFaculty">
                                        <input type="hidden" name="id" value=${item.id}>
                                        <!-- select-->
                                        <select class="mdb-select md-form colorful-select dropdown-primary" name="action">
                                            <option value="edit">Edit faculty</option>
                                            <option value="delete">Delete faculty</option>
                                        </select>
                                        <!--/ select-->
                                        <button type="submit" class="btn btn-outline-primary">Action</button>
                                    </form>
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


</body>
</html>
