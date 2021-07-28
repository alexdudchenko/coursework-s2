
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Pick up places you want</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@include file="header.jsp"%>
    <section>
        <table class="description">
            <thead>
                <tr>
                    <th>Info</th>
                    <th>Description</th>
                </tr>
            </thead>

            <tbody>
                <tr>
                    <td>Film title</td>
                    <td>${requestScope.cinemaSession.filmTitle}</td>
                </tr>

                <tr>
                    <td>Start time</td>
                    <td>${requestScope.cinemaSession.startTime.format(requestScope.cinemaSession.formatter)}</td>
                </tr>

                <tr>
                    <td>Duration (min.)</td>
                    <td>${requestScope.cinemaSession.durationInMinutes}</td>
                </tr>

                <tr>
                    <td>Finish time</td>
                    <td>${requestScope.cinemaSession.finishTime.format(requestScope.cinemaSession.formatter)}</td>
                </tr>

                <tr>
                    <td>Price</td>
                    <td>${requestScope.cinemaSession.price} UAH</td>
                </tr>
            </tbody>
        </table>
    </section>

    <section>
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
    </section>


    <section>
        <h2>Enter free cinema room places you want to book with spaces</h2>
        <form method="post" action="book" class="place-form">
            <label>
                Places:
                <input name="places">
            </label>
            <br>
            <input type="hidden" name="sessionId" value="${requestScope.cinemaSession.sessionId}">
            <br>
            <input type="submit" value="Confirm" class="submit">
        </form>
    </section>
<%@include file="footer.jsp"%>
</body>
</html>
