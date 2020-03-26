<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 19.03.2020
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Register form</title>
</head>
<body>
<div align="center">
    <h1>Enrollee Register Form</h1>
    <form id="reg_form" action="controller" method="post">


        <%--===========================================================================
        Hidden field. In the query it will act as command=login.
        The purpose of this to define the command name, which have to be executed
        after you submit current form.
        ===========================================================================--%>
        <input type="hidden" name="command" value="register"/>

        <table style="with: 80%">
            <tr>
                <td>First Name</td>
                <td><input type="text" name="firstName" /></td>
            </tr>
            <tr>
                <td>Second Name</td>
                <td><input type="text" name="secName" /></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" name="lastName" /></td>
            </tr>
            <tr>
                <td>Email</td>
                <td><input type="text" name="email" /></td>
            </tr>
            <tr>
                <td>City</td>
                <td><input type="text" name="city" /></td>
            </tr>
            <tr>
                <td>Region</td>
                <td><input type="text" name="region" /></td>
            </tr>
            <tr>
                <td>School</td>
                <td><input type="text" name="school" /></td>
            </tr>
            <tr>
                <td>Password</td>
                <td><input type="password" name="password" /></td>
            </tr>
            <tr>
                <td>Login</td>
                <td><input type="text" name="login" /></td>
            </tr>
        </table>
        <input type="submit" value="Submit" />
    </form>
</div>
</body>
</html>
