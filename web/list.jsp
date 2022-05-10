<%@ page import="others.bean" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>list</title>
</head>
<script type="text/javascript">
    function del(id) {
        if (window.confirm("你确定？")) {
            window.location = "<%=request.getContextPath()%>/program/delete?id=" + id
        }
    }
</script>
<body>
<center>
    <h1>员工列表</h1>
    <hr>
    <table border="1px" width="50%">
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>地址</th>
            <th>操作</th>
        </tr>
        <%
            List<bean> beanList = (List<bean>) request.getAttribute("beanList");
            for (bean loop : beanList) {
        %>
        <tr>
            <td><%=loop.getId()%></td>
            <td><%=loop.getName()%></td>
            <td><%=loop.getAddress()%></td>
            <td>
                <a href="javascript:void(0)" onclick="del(<%=loop.getId()%>)">删除</a>
                <a href="<%=request.getContextPath()%>/modify.jsp">修改</a>
                <a href="<%=request.getContextPath()%>/program/details?id=<%=loop.getId()%>">详情</a>
            </td>
        </tr>
        <%}%>
    </table>
    <hr>
    <a href="<%=request.getContextPath()%>/create.jsp">添加</a>
    <a href="<%=request.getContextPath()%>/program/exit">安全退出</a>
</center>
</body>
</html>
