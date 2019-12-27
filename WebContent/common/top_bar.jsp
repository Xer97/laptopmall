<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="/laptopmall/css/pricing.css" rel="stylesheet">
</head>
<body>
	<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
      <c:if test="${CURRENT_USER.role==0}">
	      <h5 class="my-0 mr-md-auto font-weight-normal"><a href="/laptopmall/product_list">笔记本商城</a></h5>
	      <nav class="my-2 my-md-0 mr-md-3">
	        <a class="p-2 text-dark" href="/laptopmall/list_cart">我的购物车</a>
	        <a class="p-2 text-dark" href="/laptopmall/list_order">我的订单</a>
	        <a class="p-2 text-dark" href="/laptopmall/portal/user_info.jsp">我的信息</a>
	      </nav>
      </c:if>
		<c:if test="${CURRENT_USER.role==1}">
	      <h5 class="my-0 mr-md-auto font-weight-normal"><a href="/laptopmall/product_list">笔记本商城·后台管理</a></h5>
	      <nav class="my-2 my-md-0 mr-md-3">
	        <a class="p-2 text-dark" href="/laptopmall/product_list">商品管理</a>
	        <a class="p-2 text-dark" href="/laptopmall/list_order">订单详情</a>
	      </nav>
      </c:if>      
      <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/login.jsp">退出</a>
    </div>

</body>
</html>