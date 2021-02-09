<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<ul>
    <table border=1>
        <thead>
        <tr>
            <th>dateTime</th>
            <th>description</th>
            <th>calories</th>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="mealToList" scope="request" type="java.util.List"/>
        <c:forEach items="${mealToList}" var="meal">
            <tr style="color:${meal.excess ? 'red' : 'green'}">
                <td>
                    <fmt:parseDate value="${ meal.dateTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${parsedDateTime}"/>
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</ul>
</body>
</html>