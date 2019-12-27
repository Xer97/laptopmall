<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>笔记本商城·商品管理</title>
<link href="/laptopmall/css/bootstrap.css" rel="stylesheet">
<script src="/laptopmall/js/jquery-3.3.1.min.js"></script>
<script src="/laptopmall/js/bootstrap.min.js"></script>
<style type="text/css">
        #listDiv {
            margin: 15px auto;
            width: 900px;
            height: 67%;
        }
		.table tbody tr td{
			vertical-align: middle;
        }
        img{
        	margin:0;
        	padding: 0;
        	width: 100px;
        }
		#stock{
			margin:0 20px;
		}
        input, select {
            margin: 0px 10px;
        }
    </style>
    <script type="text/javascript">
	    function goPage(page) {
	        $("#currentPage").val(page);
	        $("#queryFrom").submit();
	    }
	    
	    $(function(){
	    	if(${pageInfo.currentPage}>${pageInfo.totalPage} && ${pageInfo.totalPage!=0}){
	    		$("#currentPage").val(1);
	    		$("#queryFrom").submit();
	    	}
	    });
    </script>
</head>
<body>
<%@ include file="/common/top_bar.jsp" %>
<div id="listDiv">
    <div style="height: 40px;">
        <form action="product_list" method="post" id="queryFrom"
              class="form-inline input-group-sm"
              role="form" style="float: left;">
			关键字:
            <input type="text" class="form-control" name="keyword" style="width: 110px;" value="${qo.keyword}">
			品牌:
            <select class="form-control" name="brand_id">
                <option value="0">-不限-</option>
                <c:forEach items="${brands}" var="brand">
                	<option value="${brand.id}" ${qo.brandId==brand.id?"selected":""} >${brand.name}</option>
                </c:forEach>
            </select>
            <input id="currentPage" name="current_page" type="hidden" value="${qo.currentPage}">
            <button type="submit" class="btn btn-primary btn-sm">筛选</button>
        </form> 
        
        	
        <div style="float: right;">
            <a href="/laptopmall/backend/brand_edit.jsp" class="btn btn-primary btn-sm"
               role="button">新增品类</a>
            <a href="edit_product" class="btn btn-primary btn-sm"
               role="button">新增商品</a>
        </div>
    </div>
    
    <table class="table table-bordered table-sm table-hover" style="width: 900px; text-align: center;">
        <thead>
        <tr>
            <th>商品名</th>
            <th>展示图</th>
            <th>描述</th>
            <th>库存</th>
            <th>单价</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${pageInfo.list}" var="prod">
	                <tr>
	                    <td>${prod.name}</td>
	                    <td><img src="images/${prod.image}"></td>
	                    <td>${prod.detail}</td>
	                    <td><span id="stock">${prod.stock}</span></td>
	                    <td>${prod.price}</td>
	                    <td>
	                    <div style="width: 100px;">
	                        <a href="edit_product?id=${prod.id}"
	                           class="btn btn-primary btn-sm" role="button">编辑</a>
	                        <a href="del_product?id=${prod.id}"
	                           class="btn btn-danger btn-sm" role="button">删除</a>
	                           <!-- <input type="hidden" name="product_id" value="${prod.id}">
	                           <input type="submit" class="btn btn-danger btn-sm" value="加入购物车"> -->
	                    </div>
	                    </td>
	                </tr>
            </c:forEach>
        </tbody>
    </table>

    <span style="font-size: 18px;">
        第&nbsp;${pageInfo.totalPage==0?"0":pageInfo.currentPage}&nbsp;/&nbsp;${pageInfo.totalPage}&nbsp;页
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;共 ${pageInfo.totalCount} 件商品

    </span>
    <c:if test="${pageInfo.totalCount!=0}">
    
	    <ul class="pagination" style="float: right;">
	        <li class="page-item"><a class="page-link" href="#" onclick="goPage(1);">&laquo;</a></li>
	        <li class="page-item"><a class="page-link" href="#" onclick="goPage(${pageInfo.prePage});">上一页</a></li>
	        <li class="page-item"><a class="page-link" href="#" onclick="goPage(${pageInfo.nextPage});">下一页</a></li>
	        <li class="page-item"><a class="page-link" href="#" onclick="goPage(${pageInfo.totalPage});">&raquo;</a></li>
	    </ul>
    </c:if>

</div>
</body>
</html>