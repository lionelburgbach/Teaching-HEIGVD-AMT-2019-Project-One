<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lionelburgbacher
  Date: 14.10.19
  Time: 21:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Actors</title>
</head>
<body>
    <h1>Actor</h1>
    <table>
    <c:forEach items="${actors}" var="actor">
        <tr>
            <td>${actor.id}</td>
            <td>${actor.firstname}</td>
            <td>${actor.lastname}</td>
        </tr>
    </c:forEach>
    </table>
</body>
</html>
