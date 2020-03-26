<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 24.03.2020
  Time: 16:43
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

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


    <title>Faculty</title>
    <style>
        body {
            background: url('fondark.jpg') no-repeat;
            background-size: cover;
        }
        .small{
            margin: auto;
            width: 20%;
            margin-bottom: 20px;
        }
        .small2{
            margin: auto;
            width: 30%;
            margin-bottom: 20px;
        }
        h1{
            color: white;
        }
        .table-condensed{
            font-size: 16px;
        }
        .bs-example{
            margin: 0;
        }
        .navbar{
            position: relative;
        }
        p{
            margin-top:10px;
            text-align:center;
            color:#65ff4e;
            font-size:30px;
        }
        .faculty_info{
            width:20%;
            height: 150px;
            background-color: #1d1715;
            color: #65ff4e;
            font-size: 16px;
            margin: auto auto 20px;
        }
        .faculty_info ul li{
            margin: 10px 0; /*расстояние между пунктами по высоте*/
        }
        ul{
            list-style: square;
            text-align: center;
        }
        .fa-map{

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
                <a href="#" class="nav-item nav-link active">Home</a>
                <a href="#" class="nav-item nav-link">Never again)</a>
            </div>
        </div>
    </nav>
</div>


<p>${facultyInfo.name}</p>

<div class="faculty_info">
        <ul>
            <li>Count budget ->  ${facultyInfo.countBudget}</li>
            <li>Count total ->  ${facultyInfo.countTotal}</li>
        </ul>
</div>



    <form action="controller" method="get">
        <input type="hidden" name="command" value="createOrder">
        <div class="small">
        <div class="col align-self-center">
            <c:choose>
            <c:when test="${not empty disciplineList}">

            <table class="table table-striped table-dark table-condensed">
                <thead class="thead-dark">
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Minimal needed mark</th>
                    <th scope="col">Your mark</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${disciplineList}">
                    <tr>
                        <td>${item.disciplineName}</td>
                        <td>${item.minMark}</td>
                        <td><input type="text" name="${item.id}" class="form-control"></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            </c:when>
            <c:otherwise><h4><fmt:message key="error.empty"/></h4>
            </c:otherwise>
            </c:choose>

            <input class="btn btn-success" type="submit" value="I want study here!">
    </form>
</div>
</div>

<div class="small2">
<div class="container">
    <div class="row clearfix">
        <div class="col align-self-center column">
            <table class="table table-striped table-dark table-condensed" id="tab_logic">
                <thead class="thead-dark">
                <tr>
                    <th scope="col"> # </th>
                    <th scope="col"> Discipline </th>
                    <th scope="col"> Mark </th>
                </tr>
                </thead>
                <tbody>
                <tr id='addr0'>
                    <td> 1 </td>
                    <td>
                        <input type="text" name='name0' placeholder='Discipline' class="form-control"/>
                    </td>
                    <td>
                        <input type="text" name='mail0' placeholder='Mark' class="form-control"/>
                    </td>
                </tr>
                <tr id='addr1'></tr>
                </tbody>
            </table>
        </div>
    </div>
    <input class="btn btn-success btn-xs" style="margin-right: 20px;" id="add_row" value="Add Row">
    <input class="btn btn-success btn-xs " id="delete_row" value="Delete Row">

</div>
</div>
<%--       <3  - <3      --%>

<script type="text/javascript">
    $(document).ready(function(){
        var i=1;
        $("#add_row").click(function(){
            $('#addr'+i).html("<td>"+ (i+1) +"</td><td><input name='name"+i+"' type='text' placeholder='Discipline' class='form-control input-md'  /> </td>" +
                "<td><input  name='mark"+i+"' type='text' placeholder='Mark'  class='form-control input-md'></td>");

            $('#tab_logic').append('<tr id="addr'+(i+1)+'"></tr>');
            i++;
        });
        $("#delete_row").click(function(){
            if(i>1){
                $("#addr"+(i-1)).html('');
                i--;
            }
        });

    });
</script>


</body>
</html>
