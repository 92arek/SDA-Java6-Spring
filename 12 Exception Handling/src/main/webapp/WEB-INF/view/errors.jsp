<%--@elvariable id="message" type="java.lang.String"--%>
<%--@elvariable id="exception" type="java.lang.Exception"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello World</title>
</head>
<body>
<p>
    ${message}: ${exception}
</p>
<ol>
    <c:forEach items="${exception.stackTrace}" var="element">
        <li>${element}</li>
    </c:forEach>
</ol>
</body>
</html>
