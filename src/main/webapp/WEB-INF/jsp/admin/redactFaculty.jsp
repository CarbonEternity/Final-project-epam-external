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
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="redact.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>

    <title>${faculty.name}</title>

    <style>
        body {
            background: url('admin-back.jpg');
            background-repeat: no-repeat;
            background-size: cover;
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

        #redact_form {
            position: relative;
            max-width: 600px;
            padding: 30px 50px;
            margin: 150px auto;
            background: rgba(3, 3, 59, 0.767);
        }

        #redact_form:before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: linear-gradient(to right bottom, rgba(0, 0, 0, 0.233), rgba(255, 255, 255, 0.233));
        }

        .form-inner {
            position: relative;
        }

        .form-inner h3 {
            position: relative;
            margin-top: 0;
            color: rgba(255, 255, 255, 0.603);
            font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
            font-weight: 300;
            font-size: 24px;
            text-transform: uppercase;
        }

        .form-inner label {
            display: block;
            padding-left: 15px;
            font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
            color: rgba(255, 255, 255, 0.774);
            text-transform: uppercase;
            font-size: 14px;
        }


        .form-inner input {
            display: block;
            width: 70%;
            padding: 0 35px;
            margin: 10px auto;
            border-width: 0;
            line-height: 35px;
            border-radius: 10px;
            color: rgba(255, 227, 66, 0.932);
            font-size: 18px;
            background: rgba(163, 163, 163, 0.2);
            font-family: 'Roboto', sans-serif;
        }

        .form-inner fieldset {
            border-radius: 10px;
            border: 2px;
            border-style: solid;
            border-color: rgb(250, 247, 64);
            margin: 10px 0;
            background-color: rgba(223, 221, 211, 0);
            transition: background-color 1.5s linear;
        }

        .form-inner fieldset:hover {

            background-color: rgba(3, 15, 53, 0.836);
        }

        .form-inner legend {
            color: rgb(241, 231, 87);
            font-family: Cambria, Cochin, Georgia, Times, 'Times New Roman', serif;
            font-weight: 300;
            font-size: 24px;
            text-transform: uppercase;
        }


        .form-inner input[type="submit"]:hover {
            background: rgba(12, 20, 43, 0.719);
        }

        .form-inner input[type="submit"] {
            background: rgba(1, 8, 26, 0.719);
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
                            <td><input type="number" step="10" min="100" name="minMark"  max="200" value="${item.minMark}"></td>
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
