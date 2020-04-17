<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 30.03.2020
  Time: 19:05
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

    <link rel="stylesheet" href="style/css/admin_home.css">
    <link rel="stylesheet" href="admin_home.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>Home</title>


    <style>
        .centered {
            position: absolute;
            top: 30%;
            left: 40%;
            width: 500px;
            height: 300px;
            background: #f0f0f0;
        }
        body {
            height: 100%;
            background: url('admin-back.jpg') no-repeat center;
            background-size: cover;
        }

        .bs-example {
            margin: 0;
        }

        .navbar {
            position: relative;
        }

        /*---------------------------*/




        .block-1 {
            width: 450px
            height: 120px;
            position: absolute;
            top: 20%;
            left: 37%;
        }

        .block-2{
            width: 450px;
            height: 120px;
            position: absolute;
            top: 31%;
            left: 37%;
        }
        .block-3{
            width: 450px;
            height: 120px;
            position: absolute;
            top: 51%;
            left: 37%;
        }
        .block-4{
            width: 450px;
            height: 120px;
            position: absolute;
            top: 71%;
            left: 37%;
        }

        .one , .two ,  .three , .four {
            width: 450px;
            height: 120px;
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            margin: auto;
            box-shadow: 0 0 20px 0 rgba(0, 0, 0, 0.2), 0 5px 5px 0
            rgba(0, 0, 0, 0.24);
            background: #FFFFFF;
        }

        button {
            width: 80px;
            height: 50px;
            position: absolute;
            top: 0;
            right: -70%;
            bottom: 0;
            left: 0;
            margin: auto;

        }

        .text{
            width: 320px;
            height: 110px;
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: -20%;
            margin: auto;
            word-wrap: break-word;
        }


    </style>

</head>
<body>
<div class="bs-example">
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
        <a href="#" class="navbar-brand">Home</a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse1">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse1">
            <div class="navbar-nav">
                <a href="#" class="nav-item nav-link">About</a>
                <a href="#" class="nav-item nav-link">Maybe)</a>
                <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
            </div>
            <form class="form-inline ml-auto">
                <a href="controller?command=logout" class="nav-item nav-link active">Logout</a>
            </form>
        </div>
    </nav>
</div>

<%--
<div class="centered">
    <div class="form-row text-center">
        <form action="controller" method="get">
            <input type="hidden" name="command" value="actionWithFaculties">
            <button type="submit" class="btn btn-success btn-lg">Action with faculties</button>
        </form>

        <form action="controller" method="get">
            <input type="hidden" name="command" value="actionWithEnrollees">
            <button type="submit" class="btn btn-primary btn-lg">Action with enrollees</button>
        </form>

        <form action="controller" method="get">
            <input type="hidden" name="command" value="showFacultiesForCompetition">
            <button type="submit" class="btn btn-warning btn-lg">Show Faculties For Competition</button>
        </form>

        <form action="controller" method="get">
            <input type="hidden" name="command" value="showStatement">
            <button type="submit" class="btn btn-secondary btn-lg">Show statement</button>
        </form>
    </div>
</div>
--%>

<div class="block-1">
    <div class="one">
        <div class="text">
            <a>Action with faculties</a>
        </div>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="actionWithFaculties">
            <button type="submit" class="btn btn-success btn-lg">Go!</button>
        </form>
    </div>
</div>

<div class="block-2">
    <div class="two">
        <div class="text">
            <a>Action with enrollees</a>
        </div>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="actionWithEnrollees">
            <button type="submit" class="btn btn-primary btn-lg">Go!</button>
        </form>
    </div>
</div>

<div class="block-3">
    <div class="three">
        <div class="text">
            <a>Show Faculties For Competition</a>
        </div>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="showFacultiesForCompetition">
            <button type="submit" class="btn btn-warning btn-lg">Go!</button>
        </form>
    </div>
</div>

<div class="block-4">
    <div class="four">
        <div class="text">
            <a>Show statement</a>
        </div>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="showStatement">
            <button type="submit" class="btn btn-secondary btn-lg">Go!</button>
        </form>
    </div>
</div>




</body>
</html>
