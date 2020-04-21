<%--
Created by IntelliJ IDEA.
User: carbon
Date: 22.03.2020
Time: 12:35
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="ISO-8859-1">
<%--    <link rel="stylesheet" href="style/css/register.css">--%>

    <link rel="stylesheet" type="text/css" media="screen" href="style/css/register.css">

    <title>Register form</title>

</head>
<body background="WEB-INF/img/karazina-kharkov-2.jpg" >
<div>

    <form id="reg_form" action="controller" method="post">
      <!--  ===========================================================================
        Hidden field. In the query it will act as command=login.
        The purpose of this to define the command name, which have to be executed
        after you submit current form.
        ===========================================================================-->
        <input type="hidden" name="command" value="register"/>


        <div class="form-inner">
        <h3>Enrollee Register Form</h3>
        <input type="hidden" name="command" value="register"/>
        <fieldset>
            <legend>PERSONAL INFORMATION</legend>
                <label for="firstName">Name</label> 
                <input type="text" name="firstName" />
          
               <label for="secName">Second Name</label> 
               <input type="text" name="secName" />
           
               <label for="lastName">Last Name</label> 
               <input type="text" name="lastName" />
               <label for="city">City</label>
               <input type="text" name="city" />
               <label for="region">Region</label>
               <input type="text" name="region" />
               <label for="school">School</label>
               <input type="text" name="school" />

        </fieldset>
        
                <fieldset>
                <legend>REGISTRATION INFO</legend>
                <label for="email">Email</label>
                <input type="text" name="email" />
                    <label for="password">Password</label>
                    <input type="password" name="password" />
                    <label for="login">Login</label>
                    <input type="text" name="login" />
                </fieldset>
                
                
           
        <input type="submit" value="Submit" />
        </div>
    </form>
</div>
</body>
</html>
