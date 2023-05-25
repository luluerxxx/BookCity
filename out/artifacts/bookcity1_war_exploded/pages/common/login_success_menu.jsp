<%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2022/11/3
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Title</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--</body>--%>
<%--</html>--%>
<div>
<%--    <span>欢迎 <span class="um_span">xhy</span>光临铛铛铛铛书城</span>--%>
<%--    <a href="../order/order.jsp">我的订单</a>--%>
<%--    <a href="../../index.jsp">注销</a>&nbsp;&nbsp;--%>
<%--    <a href="../../index.jsp">返回</a>--%>
    <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临铛铛铛铛书城</span>
    <a href="http://localhost:8099/bookcity1_war_exploded/pages/order/order.jsp">我的订单</a>
    <a href="http://localhost:8099/bookcity1_war_exploded/index.jsp">注销</a>&nbsp;&nbsp;
    <a href="http://localhost:8099/bookcity1_war_exploded/index.jsp">返回</a>
</div>
