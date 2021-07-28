
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Log in</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="login">


    <div class="login-container">
        <h1>Log in, please</h1>
    <form action="login" method="post">
        <label>
            Login:
            <input name="login">
        </label>
        <br>
        <label>
            Password:
            <input name="password" type="password">
        </label>
        <br>
        <input type="submit" class="submit">
    </form>
    </div>
    <c:if test="${!empty requestScope.loginMessage}">
        <h2>
            ${requestScope.loginMessage}
        </h2>
    </c:if>

</body>
</html>
