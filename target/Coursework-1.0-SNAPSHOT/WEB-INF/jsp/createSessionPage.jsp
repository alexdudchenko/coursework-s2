
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create page</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%@include file="header.jsp"%>
<h1>Create a new session!</h1>

<div class="create-page-form">
<form action="create" method="post">
    <label>
        ID:
        <input name="id">
    </label>

    <label>
        Film:
        <input name="film">
    </label>

    <label>
        Duration:
        <input name="duration">
    </label>

    <label>
        Start time (in "yyyy-MM-dd HH:mm" format):
        <input name="startTime">
    </label>

    <label>
        Price:
        <input name="price">
    </label>

    <input type="submit" value="Create" class="submit">
</form>
</div>

<%@include file="footer.jsp"%>
</body>
</html>
