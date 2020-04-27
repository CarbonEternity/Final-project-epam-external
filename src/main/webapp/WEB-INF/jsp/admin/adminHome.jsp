<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 30.03.2020
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<%@ include file="/WEB-INF/jspf/head.jspf"%>

<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" media="screen" href="style/css/adminHomeStyles.css">
    <title><fmt:message key="title.Home"/></title>
</head>

<body>
<div class="bs-example">
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
        <a href="#" class="navbar-brand">
            <img src="style/icon/karazin-logo.png" width="30" height="30" class="d-inline-block align-top" alt="logo">
            Karazin University</a>

        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse1">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse1">
            <div class="navbar-nav">
                <a href="#" class="nav-item nav-link"><fmt:message key="common.about"/></a>
                <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
            </div>
            <form class="form-inline ml-auto">

                <form action="controller" method="post">
                    <input type="hidden" name="command" value="changeLocale">
                    <!-- select-->
                    <select class="mdb-select md-form colorful-select dropdown-primary" name="locale">
                        <option selected><fmt:message key="common.locale.selectLocale"/></option>
                        <option value="ru"><fmt:message key="common.locale.optionRULocale"/></option>
                        <option value="en"><fmt:message key="common.locale.optionENLocale"/></option>
                    </select>
                    <!--/ select-->
                    <button type="submit" class="btn btn-outline-light"><fmt:message key="common.locale.localeButton"/></button>
                </form>


                <a href="controller?command=logout" class="nav-item nav-link active"><fmt:message key="common.logout"/></a>
            </form>
        </div>
    </nav>
</div>

<div class="block-1">
    <div class="one">
        <div class="text">
            <a><fmt:message key="admin.home.actionWithFaculties"/></a>
        </div>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="actionWithFaculties">
            <button type="submit" id="buttonForm1" class="btn btn-success btn-lg">Go!</button>
        </form>
    </div>
</div>

<div class="block-2">
    <div class="two">
        <div class="text">
            <a><fmt:message key="admin.home.actionWithEnrollees"/></a>
        </div>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="actionWithEnrollees">
            <button type="submit" id="buttonForm2" class="btn btn-primary btn-lg">Go!</button>
        </form>
    </div>
</div>

<div class="block-3">
    <div class="three">
        <div class="text">
            <a><fmt:message key="admin.home.showFacultiesForCompetition"/></a>
        </div>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="showFacultiesForCompetition">
            <button type="submit" id="buttonForm3" class="btn btn-warning btn-lg">Go!</button>
        </form>
    </div>
</div>

<div class="block-4">
    <div class="four">
        <div class="text">
            <a><fmt:message key="admin.home.showStatement"/></a>
        </div>
        <form action="controller" method="get">
            <input type="hidden" name="command" value="showStatement">
            <button type="submit" id="buttonForm4" class="btn btn-secondary btn-lg">Go!</button>
        </form>
    </div>
</div>



<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
