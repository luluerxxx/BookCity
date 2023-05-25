<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
	<%--	静态包含base标签，css样式，jquery文件--%>
	<%@ include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="../../static/img/DDlogoNEW.gif" >
			<span class="wel_word">编辑图书</span>
			<%@include file="/pages/common/manager_menu.jsp"%>
		</div>
		
		<div id="main">
			<form action="manager/bookServlet" method="get">
				<input type="hidden" name="pageNo" value="${param.pageNo}">
				<input type="hidden" name="pageTotal" value="${requestScope.page.pageTotal}">
				<input type="hidden" name="action" value="${ empty param.id ? "add" : "update"}" />
				<input type="hidden" name="id" value="${ requestScope.book.id }" />
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text" value="${requestScope.book.name}"/></td>
						<td><input name="price" type="text" value="${requestScope.book.price}"/></td>
						<td><input name="author" type="text" value="${requestScope.book.author}"/></td>
						<td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
						<td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
						<td><input type="submit" id="submit_btn" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>

		<%@include file="/pages/common/footer.jsp"%>
		<script type="text/javascript">
			$(function () {
				//跳到指定的页码
				$("#submit_btn").click(function () {
					// var pageNo = $("#pn_input").val();
					var pageNo = ${param.pageTotal}
					<%--var pageTotal = ${requestScope.page.pageTotal};--%>
					<%--alert(pageTotal);--%>
					// javaScript 语言中提供了一个 location 地址栏对象
					// 它有一个属性叫 href. 它可以获取浏览器地址栏中的地址
					// 修改head.jsp文件，加上pageContext.setAttribute("basePath", basePath);
					location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
				})
			})

		</script>

</body>
</html>