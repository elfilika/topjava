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
            <th>exceed</th>
        </tr>
        </thead>
        <tbody>
        <jsp:useBean id="MealList" scope="request" type="java.util.List"/>
        <c:forEach items="${MealList}" var="meal">
            <tr style="background-color:${meal.isExcess() ? 'greenyellow' : 'red'}">
                <td>
                    <fmt:parseDate value="${ meal.getDateTime() }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"/>
                    <fmt:formatDate pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}"/>
                </td>
                <td><c:out value="${meal.getDescription()}"/></td>
                <td><c:out value="${meal.getCalories()}"/></td>
                <td><c:out value="${meal.isExcess()}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</ul>
</body>
</html>