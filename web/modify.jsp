<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>modify</title>
</head>
<body style="text-align: center">
<h1>修改信息</h1>
<hr>
<form action="<%=request.getContextPath()%>/program/modify" method="post">
    编号<input type="text" name="id"><br>
    姓名<input type="text" name="name"><br>
    地址<input type="text" name="address"><br>
    电话<input type="text" name="phonenum"><br>
    <hr>
    <input type="submit" value="确定">
</form>
</body>
</html>
