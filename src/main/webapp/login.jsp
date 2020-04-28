<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 21.03.2020
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<fmt:setLocale value="${locale}"/>

<html lang="en">
<head>
    <link rel="icon" href="style/icon/karazin.ico">
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
            <input type="email" autocomplete="on" name="email"/>

            <label for="password">Password</label>
            <input type="password" name="password" pattern="[A-Za-z0-9]+"/>
        </fieldset>
        <input type="submit" value="login"/>

        <div class="register">
            <p>Doesn't have an account? <a href="/final/register.jsp" class="link">Register now</a></p>
        </div>
    </div>

</form>


</body>
</html>
