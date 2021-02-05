<%--
  Created by IntelliJ IDEA.
  User: xuant
  Date: 2/4/2021
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
<form method="post">
    <table>
        <tr>
            <td>Name</td>
            <td>${user.getName()}</td>
        </tr>
        <tr>
            <td>Email</td>
            <td>${user.getEmail()}</td>
        </tr>
        <tr>
            <td>Country</td>
            <td>${user.getCountry()}</td>
        </tr>
    </table>
    <button type="submit">Delete</button>

</form>
</body>
</html>
