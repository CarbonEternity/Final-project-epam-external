<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 22.04.2020
  Time: 20:47
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="style/css/showEnrolleeImageStyles.css">
    <title><fmt:message key="title.certificate"/></title>
</head>

<body>

<div class="bs-example">
    <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">
        <a href="#" class="navbar-brand">
            <img src="style/icon/karazin-logo.png" width="30" height="30" class="d-inline-block align-top" alt="logo">
            Karazin University</a>

        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse1">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse1">
            <div class="navbar-nav">
                <a href="#" class="nav-item nav-link"><fmt:message key="common.about"/></a>
                <a href="controller?command=showFacultiesForCompetition" class="nav-item nav-link"><fmt:message
                        key="admin.header.showFacultiesForCompetition"/></a>
                <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
            </div>
            <form class="form-inline ml-auto">
                <a href="controller?command=logout" class="nav-item nav-link active"><fmt:message
                        key="common.logout"/></a>
            </form>
        </div>
    </nav>
</div>


<div id="container">
    <form action="controller" method="get">
        <input type="hidden" name="command" value="showEnrolee">
        <input type="hidden" name="id_enr" value="${enroleeInfo.id}">
        <input type="hidden" name="id_fac" value="${id_fac}">
        <button type="submit" name="back" class="btn btn-lg btn-outline-warning" id="button1">&#8592; <fmt:message
                key="common.back"/></button>
        <button type="submit" name="admit" class="btn btn-lg btn-outline-warning" id="button2"><fmt:message
                key="admin.listEnrollees.admit"/></button>
    </form>
</div>


<div class="center">
    <img src="data:image/jpg;base64,${base64Image}"  class="passe-partout" alt="certificate"/>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</body>
</html>