
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@ include file="header.jsp"%>
<form method="post" action="edit">

    <input type="hidden" name="id" value="${requestScope.cinemaSession.sessionId}">
    <div class="create-page-form">
    <label>
        Film:
        <input name="film" value="${requestScope.cinemaSession.filmTitle}">
    </label>

    <label>
        Start time (in "yyyy-MM-dd HH:mm" format):
        <input name="startTime" value="${requestScope.cinemaSession.startTime.format(requestScope.cinemaSession.formatter)}">
    </label>

    <label>
        Duration:
        <input name="duration" value="${requestScope.cinemaSession.durationInMinutes}">
    </label>

    <label>
        Price:
        <input name="price" value="${requestScope.cinemaSession.price}">
    </label>

    <table class="places">
            <tr>
                <c:forEach items="${requestScope.cinemaSession.placeArrayList}" var="place" begin="0" end="9">
                    <td>
                        <c:choose>
                            <c:when test="${place.free}">
                                ${place.number}
                            </c:when>
                            <c:otherwise>
                                X
                            </c:otherwise>
                        </c:choose>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <c:forEach items="${requestScope.cinemaSession.placeArrayList}" var="place" begin="10">
                    <td>
                        <c:choose>
                            <c:when test="${place.free}">
                                ${place.number}
                            </c:when>
                            <c:otherwise>
                                X
                            </c:otherwise>
                        </c:choose>
                    </td>
                </c:forEach>
            </tr>
        </table>

    <label>
        Cancel booking at:
        <input name="cancelBook">
    </label>

    <input type="submit" value="Edit" class="submit">
    </div>
<%@include file="footer.jsp"%>
</body>
</html>
