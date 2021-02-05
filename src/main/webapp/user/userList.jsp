<%--
  Created by IntelliJ IDEA.
  User: xuant
  Date: 2/4/2021
  Time: 2:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User List    </title>
</head>
<body>

<h1>User List</h1>
<a href="/users?action=create">Create new</a>
<form method="get">
    <input type="text" name="country" size="30">
    <input type="submit" name="action" value="search">
</form>
<table>
    <tr>
        <td>Name<form method="get"><input type="submit" name="action" value="sort"></form> </td>
        <td>Email</td>
        <td>Country</td>
    </tr>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.getName()}</td>
            <td>${user.getEmail()}</td>
            <td>${user.getCountry()}</td>
            <td><a href="/users?action=update&id=${user.getId()}">Update</a></td>
            <td><a href="/users?action=delete&id=${user.getId()}">Delete</a></td>
        </tr>

    </c:forEach>
</table>


</body>
</html>
