<%--
  Created by IntelliJ IDEA.
  User: xuant
  Date: 2/5/2021
  Time: 8:02 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Search Result</title>
</head>
<body>
<h1>Search Result</h1>
<table>
    <tr>
        <td>Name</td>
        <td>Email</td>
        <td>Country</td>
    </tr>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.getName()}</td>
            <td>${user.getEmail()}</td>
            <td>${user.getCountry()}</td>
        </tr>

    </c:forEach>
</table>
</body>
</html>
