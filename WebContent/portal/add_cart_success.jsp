<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>笔记本商城·我的订单</title>
<link href="/laptopmall/css/bootstrap.css" rel="stylesheet">
<script src="/laptopmall/js/jquery-3.3.1.min.js"></script>
<script src="/laptopmall/js/bootstrap.min.js"></script>
<style type="text/css">
#listDiv {
	margin: 15px auto;
	width: 900px;
	height: 67%;
}

img {
	margin: 0;
	padding: 0;
	width: 100px;
}

.table tbody tr td {
	vertical-align: middle;
}
</style>
</head>
<body>
	<%@ include file="/common/top_bar.jsp"%>
	<div id="listDiv">
		<h3 style="text-align: center;">添加购物车成功</h3>
		<br /> <br />
		<div>
			<a href="product_list" class="btn btn-primary btn-sm" role="button">&lt;&lt; 继续浏览</a>

			<a href="list_cart" class="btn btn-primary btn-sm" role="button">查看购物车 &gt;&gt;</a>

		</div>
		<br />


	</div>
</body>
</html>