<%--
  Created by IntelliJ IDEA.
  User: carbon
  Date: 24.03.2020
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<fmt:setLocale value="${locale}"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" type="text/css" media="screen" href="style/css/viewRequirementsStyles.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <title>${title}</title>

</head>
<body>

<div class="bs-example">
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
        <a href="#" class="navbar-brand">Home</a>
        <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse1">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarCollapse1">
            <div class="navbar-nav">
                <a href="#" class="nav-item nav-link">About</a>
                <a href="controller?command=sortFacultyList" class="nav-item nav-link">All faculties</a>
                <a href="#" class="nav-item nav-link">Never again)</a>
                <a href="#" class="nav-item nav-link">${user.firstName} (${user.email})</a>
            </div>
            <form class="form-inline ml-auto">
                <a href="controller?command=logout" class="nav-item nav-link active">Logout</a>
            </form>
        </div>
    </nav>
</div>

<form action="controller" method="post">
    <input type="hidden" name="command" value="createOrder">
    <input type="hidden" name="title" value=${facultyInfo.name}>

    <div class="info-block">
        <p>${facultyInfo.name}</p>
        <div class="faculty_info">
            <ul>
                <li>Count budget ->  ${facultyInfo.countBudget}</li>
                <li>Count total ->  ${facultyInfo.countTotal}</li>
            </ul>
        </div>
        <input id="want" class="btn btn-success" type="submit" value="I want study here!">
    </div>


    <div class="block">

        <div class="row">

            <div class="one">
                <div class="col align-self-center">
                    <c:choose>
                        <c:when test="${not empty disciplineList}">

                            <table class="table table-light table-striped table-hover">
                                <thead class="thead-dark">
                                <tr>
                                    <th scope="col">Name</th>
                                    <th scope="col">Minimal needed mark</th>
                                    <th scope="col">Your mark</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${disciplineList}">
                                    <tr>
                                        <td>${item.disciplineName}</td>
                                        <td>${item.mark}</td>
                                        <td><input type="number" min="100" max="200" required
                                                   name="zno_${item.disciplineName}" class="form-control"></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise><h4><fmt:message key="error.disciplineList.empty"/></h4>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>

            <div class="small">
                <div class="col align-self-center">
                    <c:choose>
                        <c:when test="${not empty certificateDisciplineList}">

                            <table class="table table-dark table-striped table-hover">
                                <thead class="thead-dark">
                                <tr>
                                    <th scope="col"> #</th>
                                    <th scope="col"> Discipline</th>
                                    <th scope="col"> Mark</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item2" items="${certificateDisciplineList}">
                                    <tr>
                                        <td>${item2.id}</td>
                                        <td>${item2.disciplineName}</td>
                                        <td><input type="number" required min="1" max="12"
                                                   name="cert_${item2.disciplineName}" class="form-control"></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:when>
                        <c:otherwise><h4><fmt:message key="error.certificateDisciplineList.empty"/></h4>
                        </c:otherwise>
                    </c:choose>


                </div>
            </div>
        </div>
    </div>
</form>


</body>
</html>
