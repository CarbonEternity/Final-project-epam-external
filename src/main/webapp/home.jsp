<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 21.03.2020
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Home Page</title>
</head>
<body>
<div style="text-align: center">
    <h1>Welcome selection committee</h1>
    <b>${user.firstName} (${user.email})</b>
    <br><br>
    <a href="/logout">Logout</a>
</div>
</body>
</html>
