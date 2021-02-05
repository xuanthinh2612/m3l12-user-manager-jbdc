<%--
  Created by IntelliJ IDEA.
  User: xuant
  Date: 2/4/2021
  Time: 2:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User Information</title>
</head>
<body>
<h1>Update User Information</h1>
<form method="post">
    <table>
        <tr>
            <td>Name</td>
            <td><input type="text" name = "name" value="${user.getName()}"></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name = "email"  value="${user.getEmail()}"></td>
        </tr>
        <tr>
            <td>Country</td>
            <td><input type="text" name = "country"  value="${user.getCountry()}"></td>
        </tr>
    </table>
    <input type="submit" value="Update">
</form>
</body>
</html>
