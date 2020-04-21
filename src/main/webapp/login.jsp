<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 21.03.2020
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>

<%--    <link rel="stylesheet" href="style/css/login.css">--%>
    <link rel="stylesheet" type="text/css" media="screen" href="style/css/login.css">

    <meta charset="utf-8">
    <title>Login</title>
</head>
<body>


<form id="login_form" action="controller" method="post">

    <div class="form-inner">

        <input type="hidden" name="command" value="login"/>
        <fieldset>
            <legend>LOG IN</legend>
            <label for="email">Email</label>
            <input type="email" name="email"/>

            <label for="password">Password</label>
            <input type="password" name="password"/>


        </fieldset>
        <input type="submit" value="login"/>

    </div>
    <input type="hidden" name="command" value="login"/>

</form>
</body>

</html>