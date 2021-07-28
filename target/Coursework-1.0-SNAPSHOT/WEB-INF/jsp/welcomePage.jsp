
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@include file="header.jsp"%>
<br>
<h1>Choose session you want below</h1>
<br>
    <table>
        <thead>
            <tr>
                <th><a href="?sorting=BY_TITLE">Film</a></th>
                <th><a href="?sorting=NEW_FIRST">Date and Time</a></th>
                <th>Duration (min.)</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="cinemaSession" items="${requestScope.cinemaSessions}">
                <tr>
                    <td><a href="sessionPage?id=${cinemaSession.sessionId}">${cinemaSession.filmTitle}</a></td>
                    <td>${cinemaSession.startTime.format(cinemaSession.formatter)}</td>
                    <td>${cinemaSession.durationInMinutes}</td>
                    <td>${cinemaSession.price} UAH</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <%@include file="footer.jsp"%>
</body>
</html>
