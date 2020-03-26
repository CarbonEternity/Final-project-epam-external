<%--
Created by IntelliJ IDEA.
User: carbon
Date: 22.03.2020
Time: 12:35
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Register form</title>
    <style>
        body{
            background-size: cover;
            /*background: url("../resources/img/karazina-kharkov-2.jpg") no-repeat;*/
        }
        #reg_form{
            position: relative;
            max-width: 600px;
            padding: 30px 50px;
            margin: 10px auto;
            
            background: rgb(37, 37, 37);
        }
       
                #reg_form:before {
                content: "";
                position: absolute;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background: linear-gradient(to right bottom,rgba(0, 0, 0, 0.233),rgba(255, 255, 255, 0.233));
                }
                .form-inner {position: relative;}
                .form-inner h3 {
                    position: relative;
                    margin-top: 0;
                    color: rgb(255, 255, 255);
                    font-family: 'Roboto', sans-serif;
                    font-weight: 300;
                    font-size: 26px;
                    text-transform: uppercase;
                    }

                    .form-inner label {
                        display: block;
                        padding-left: 15px;
                        font-family:'Roboto', sans-serif;
                        color: rgb(255, 255, 255);
                        text-transform: uppercase;
                        font-size: 16px;
                        }


                        .form-inner input {
                            display: block;
                            width: 70%;
                            padding: 0 35px;
                            margin: 10px  auto;
                            border-width: 0;
                            line-height: 35px;
                            border-radius: 10px;
                            color: rgba(92, 226, 208, 0.932);
                            font-size: 18px;
                            background: rgba(163, 163, 163, 0.2);
                            font-family: 'Roboto', sans-serif;
                            }
                            .form-inner fieldset{
                                border-radius: 10px;
                                border-color: rgb(79, 202, 196);
                                margin: 10px 0;
                                background-color: rgba(0, 0, 0, 0.158);
                                transition:  background-color 1.5s linear;
                            } 
                            .form-inner fieldset:hover{
                               
                                background-color: rgba(24, 28, 29, 0.651);
                            }
                            .form-inner legend {
                                
                                color: rgb(0, 0, 0);
                            font-size: 20px;
                            }

                

                      
                            .form-inner input[type="submit"] {background: rgba(29, 31, 36, 0.719);}
        
    </style>
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
