<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 21.03.2020
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>

    <meta charset="utf-8">
    <title>Login</title>
    <style>
        body {
            background: url('karazina-kharkov-2.jpg') no-repeat;
            background-size: cover;
        }

        #login_form {
            position: relative;
            max-width: 600px;
            padding: 30px 50px;
            margin: 10px auto;

            background: rgba(3, 3, 59, 0.767);
        }

         #login_form:before {
        content: "";
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background: linear-gradient(to right bottom,rgba(0, 0, 0, 0.233),rgba(255, 255, 255, 0.233));
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
</div>
</body>

</html>