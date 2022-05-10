<%@ page import="others.bean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>details</title>
</head>
<body style="text-align: center">
<h1>具体信息</h1>
<hr>
<%
    List<bean> beanList = (List<bean>) request.getAttribute("beanList");
    bean loop = beanList.get(0);
%>
编号<input type="text" value="<%=loop.getId()%>" readonly><br>
姓名<input type="text" value="<%=loop.getName()%>" readonly><br>
地址<input type="text" value="<%=loop.getAddress()%>" readonly><br>
电话<input type="text" value="<%=loop.getPhonenum()%>" readonly><br>
<hr>
<input type="button" value="返回" onclick="window.history.back()">
</body>
</html>
