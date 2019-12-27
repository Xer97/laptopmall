<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>笔记本商城·订单管理</title>
<link href="/laptopmall/css/bootstrap.css" rel="stylesheet">
<script src="/laptopmall/js/jquery-3.3.1.min.js"></script>
<script src="/laptopmall/js/bootstrap.min.js"></script>
<style type="text/css">
        #listDiv {
            margin: 70px auto;
            width: 900px;
            height: 67%;
        }

		.table tbody tr td{
            vertical-align: middle;
        }
		
    </style>
</head>
<body>
<%@ include file="/common/top_bar.jsp" %>
<div id="listDiv">
    <table class="table table-bordered table-sm table-hover" style="width: 900px; text-align: center;">
        <thead>
        <tr>
            <th>订单号</th>
            <th>订单金额</th>
            <th>下单时间</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${pageInfo.list}" var="order">
                <tr>
                    
                    <td>${order.id}</td>
                    <td>${order.payment}</td>
                    <td><fmt:formatDate value="${order.payTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <span style="font-size: 18px;">
        第&nbsp;${pageInfo.currentPage}&nbsp;/&nbsp;${pageInfo.totalPage}&nbsp;页
        &nbsp;&nbsp;&nbsp;总订单数 ${pageInfo.totalCount}  &nbsp;&nbsp;总销售额  <span style="color:red;">${salesValue}</span> 元

    </span>
    <ul class="pagination" style="float: right;">
        <li class="page-item"><a class="page-link" href="list_order?currentPage=1" >&laquo;</a></li>
        <li class="page-item"><a class="page-link" href="list_order?currentPage=${pageInfo.prePage}" >上一页</a></li>
        <li class="page-item"><a class="page-link" href="list_order?currentPage=${pageInfo.nextPage}" >下一页</a></li>
        <li class="page-item"><a class="page-link" href="list_order?currentPage=${pageInfo.totalPage}" >&raquo;</a></li>
    </ul>

</div>
</body>
</html>