<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 22.04.2020
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<fmt:setLocale value="${locale}"/>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="style/css/showEnrolleeImageStyles.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>${title}</title>

</head>

<body>

<div class="bs-example">
    <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
        <a href="controller?command=adminHome" class="navbar-brand">Home</a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse1">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse1">
            <div class="navbar-nav">
                <a href="controller?command=showFacultiesForCompetition" class="nav-item nav-link">Faculties with applications</a>
                <a href="#" class="nav-item nav-link">About</a>
                <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
            </div>
            <form class="form-inline ml-auto">
                <a href="controller?command=logout" class="nav-item nav-link active">Logout</a>
            </form>
        </div>
    </nav>
</div>


<div id="container">
    <form action="controller" method="get">
        <input type="hidden" name="command" value="showEnrolee">
        <input type="hidden" name="id_enr" value="${enroleeInfo.id}">
        <input type="hidden" name="id_fac" value="${id_fac}">
        <button type="submit" name= "back" class="btn btn-lg btn-outline-warning" id="button1">&#8592; Back</button>
        <button type="submit" name="admit" class="btn btn-lg btn-outline-warning" id="button2">Admit</button>
    </form>
</div>


<div class="center">
    <img src="data:image/jpg;base64,${base64Image}"  class="passe-partout" alt="certificate"/>
</div>


</body>
</html>