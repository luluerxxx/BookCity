<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>铛铛铛铛会员登录页面</title>
	<!--写base标签，永远固定相对路径跳转的结果-->
<!--	<base href="http://localhost:8099/bookcity1/">-->
<%--	<base href="http://localhost:8099/bookcity1_war_exploded/">--%>
<!--	<base href="http://localhost:63343/bookcity1/">-->

<%--	<link type="text/css" rel="stylesheet" href="static/css/style.css" >--%>

	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/DDlogoNEW.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎登录</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>铛铛铛铛会员</h1>
								<a href="pages/user/regist.jsp">立即注册</a>
							</div>
							<div class="msg_cont">
								<b></b>
								<span class="errorMsg">
<%--									//提示用户输入，回显错误信息--%>
<%--									<%=request.getAttribute("msg")==null?"请输入用户名和密码":request.getAttribute("msg")%>--%>
									${ empty requestScope.msg ? "请输入用户名和密码":requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="loginServlet" method="get">
<%--									<input type="hidden" name="action" value="login">--%>
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username"
<%--										   当用户输入新的用户名时，让输入框保留用户上一次输入的值--%>
<%--											value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"--%>
											value="${cookie.username.value}"
									/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" />
									<br />
									<br />
									<input type="submit" value="登录" id="sub_btn" />
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>