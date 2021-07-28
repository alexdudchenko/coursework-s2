
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="header.jsp"%>

<a href="createPage" class="body-button">Create page</a>

<table>
    <thead>
    <tr>
        <th><a href="adminPage?sorting=BY_ID">ID</a></th>
        <th><a href="adminPage?sorting=BY_TITLE">Film</a></th>
        <th><a href="adminPage?sorting=NEW_FIRST">Date and Time</a></th>
        <th>Duration (min.)</th>
        <th>Price</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="cinemaSession" items="${requestScope.cinemaSessions}">
        <tr>
            <td>${cinemaSession.sessionId}</td>
            <td><a href="editPage?id=${cinemaSession.sessionId}">${cinemaSession.filmTitle}</a></td>
            <td>${cinemaSession.startTime.format(cinemaSession.formatter)}</td>
            <td>${cinemaSession.durationInMinutes}</td>
            <td>${cinemaSession.price} UAH</td>
            <td>
                <a href="remove?id=${cinemaSession.sessionId}">Remove</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<%@ include file="footer.jsp"%>
</body>
</html>
