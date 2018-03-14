<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--@elvariable id="players" type="java.util.List"--%>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
<form:form method="post" modelAttribute="player" action="/players/form">
    <ul>
        <c:forEach items="${players}" var="player">
            <li>${player.name} ${player.surname}</li>
        </c:forEach>
        <label>Name:<form:input path="name" type="text"/></label>
        <label>Surname:<form:input path="surname" type="text"/></label>
        <button type="submit" value="ADD">Add</button>
    </ul>
</form:form>
</body>
</html>
