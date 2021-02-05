<%--
  Created by IntelliJ IDEA.
  User: xuant
  Date: 2/4/2021
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new User</title>
</head>
<body>
<form method="post">
    <table>
        <tr>
            <td>Name</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td>Country</td>
            <td><input type="text" name="country"></td>
        </tr>
    </table>
    <button type="submit" value="Create new">Create new</button>
</form>
</body>
</html>
