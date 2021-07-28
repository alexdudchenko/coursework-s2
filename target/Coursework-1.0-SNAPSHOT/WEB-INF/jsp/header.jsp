
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
    <a href="." class="button">Welcome</a>


    <c:if test="${empty sessionScope.admin}">
        <a href="loginPage" class="button">Log in (as admin)</a>
    </c:if>


    <c:if test="${!empty sessionScope.admin}">
    <div class="header-left">
        <form method="post" action="logout">
            <p><c:out value="${sessionScope.admin.login}"/><p>
            <input type="submit" value="Log Out"/>
        </form>
    </div>
        <a href="adminPage" class="button">To admin page</a>
    </c:if>

</header>

