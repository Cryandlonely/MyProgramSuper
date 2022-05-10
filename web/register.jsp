<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<center>
<h1>用户注册</h1>
<hr>
<table>
    <form action="http://localhost:8080<%=request.getContextPath()%>/program/register" method="post">
        <tr>
            <td>账号</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><input type="submit" value="提交"></td>
        </tr>
    </form>
</table>
</center>
</body>
</html>