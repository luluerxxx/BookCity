<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>铛铛铛铛会员注册页面</title>
	<!--写base标签，永远固定相对路径跳转的结果-->
<!--	<base href="http://localhost:8089/bookDemo01/">-->
<%--	<base href="http://localhost:8099/bookcity1_war_exploded/">--%>

<%--<link type="text/css" rel="stylesheet" href="static/css/style.css" >--%>
	<%@include file="/pages/common/head.jsp"%>

	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
</style>
</head>
<body>
		<div id="header">
				<img class="logo_img" alt="" src="static/img/DDlogoNEW.gif" >
				<span class="wel_word"></span>
				<div>
					<span>欢迎<span class="um_span">${sessionScope.ruser.username}</span>光临铛铛铛铛书城</span>
					<a href="http://localhost:8099/bookcity1_war_exploded/pages/order/order.jsp">我的订单</a>
					<a href="http://localhost:8099/bookcity1_war_exploded/index.jsp">注销</a>&nbsp;&nbsp;
					<a href="http://localhost:8099/bookcity1_war_exploded/index.jsp">返回</a>
				</div>
		</div>
		
		<div id="main">
		
			<h1>注册成功! <a href="http://localhost:8088/bookDemo01/index.jsp">转到主页</a></h1>
	
		</div>

		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>