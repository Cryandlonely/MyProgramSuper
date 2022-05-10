<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>create</title>
</head>
<body style="text-align: center">
<h1>新人入队</h1>
<hr>
<form action="<%=request.getContextPath()%>/program/create" method="get">
    编号<input type="text" name="id"><br>
    姓名<input type="text" name="name"><br>
    地址<input type="text" name="address"><br>
    电话<input type="text" name="phonenum"><br>
    <hr>
    <input type="submit" value="提交">
</form>
</body>
</html>
