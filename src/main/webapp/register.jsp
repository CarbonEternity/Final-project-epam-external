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
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" media="screen" href="style/css/register.css">

    <meta charset="utf-8" content="multipart/form-data">

    <title>Register form</title>
</head>
<body>


<form id="reg_form" action="controller" method="post" enctype="multipart/form-data">
    <input type="hidden" name="command" value="register"/>

    <form id="form1" action="controller" method="post" >

        <div class="form-inner">

            <fieldset>
                <legend>PERSONAL INFORMATION</legend>

                <label for="firstName">Name</label>
                <input type="text" name="firstName" required/>

                <label for="secName">Second Name</label>
                <input type="text" name="secName" required/>

                <label for="lastName">Last Name</label>
                <input type="text" name="lastName" required/>

                <label for="city">City</label>
                <input type="text" name="city" required/>

                <label for="region">Region</label>
                <input type="text" name="region" required/>

                <label for="school">School</label>
                <input type="text" name="school" required/>

                <label for="img">Certificate</label>
                <input type="file" id="file" name="img" accept="image/jpeg, image/png" required/>
            </fieldset>

            <fieldset>
                <legend>REGISTRATION INFO</legend>
                <label for="email">Email</label>
                <input type="email" name="email"/>

                <label for="password">Password</label>
                <input type="password" name="password"/>
            </fieldset>


            <button type="submit">submit</button>
        </div>

    </form>

</form>


<script type="javascript">
    var uploadField = document.getElementById("file");
    uploadField.onchange = function () {
        if (this.files[0].size > 5000000) {
            alert("File is too big!");
            this.value = "";
        }
    };


    function submitForm() {
        document.getElementById("reg_form").submit();
        document.getElementById("form1").submit();
    }
</script>
</body>
</html>