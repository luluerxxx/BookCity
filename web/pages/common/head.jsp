<%--
  Created by IntelliJ IDEA.
  User: 86166
  Date: 2022/11/3
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>--%>
<%--<head>--%>
<%--    <title></title>--%>
<%--</head>--%>
<%--<body>--%>

<%--</body>--%>
<%--</html>--%>
<%
    String basePath = request.getScheme()  //协议名称
            + "://"
            +request.getServerName()        //服务器名
            +":"
            +request.getServerPort()        //端口号
            +request.getContextPath()       //项目名称
            +"/";
    pageContext.setAttribute("basePath",basePath);
%>
<%--<%=basePath%>--%>
<%--写base标签，永远固定相对路径跳转的结果--%>

<base href="<%=basePath%>">
<link rel="stylesheet" href="static/css/style.css" type="text/css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>