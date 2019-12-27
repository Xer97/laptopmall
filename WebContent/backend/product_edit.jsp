<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>笔记本商城·${prod.id==null?"新增":"编辑"}商品</title>
<link href="/laptopmall/css/bootstrap.css" rel="stylesheet">
<script src="/laptopmall/js/jquery-3.3.1.min.js"></script>
<script src="/laptopmall/js/bootstrap.min.js"></script>
	<style type="text/css">
        .form-group,#main {
            margin: 0 auto;
            width: 300px;
        }
        #main {
        	margin-top: 10px;
        }
        
    </style>
</head>
<body>
<%@ include file="/common/top_bar.jsp" %>
<div id="main">
	<h4 style="text-align: center">${prod.id==null?"新增":"编辑"}商品</h4>

    <form action="/laptopmall/save_product" method="post" class="input-group-sm" enctype="multipart/form-data">
    	<input type="hidden" name="product_id" value="${prod.id}" />
        <div class="form-group input-group-sm">
            <label for="product_name">商品名:</label><span style="float: right;color:red;">${info}</span>
            <input type="text" name="product_name" id="product_name" class="form-control"
                    maxlength="20" value="${prod.name}" required/>
        </div>
        <div class="form-group input-group-sm">
            <label for="brand_id">所属品牌:</label>
	        <select class="form-control" name="brand_id" id="brand_id">
                <c:forEach items="${brands}" var="brand">
                	<option value="${brand.id}" ${prod.brandId==brand.id?"selected":""} >${brand.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group input-group-sm">
            <label for="detail">商品描述:</label>
            <textarea name="detail" id="detail" class="form-control"  required rows="5" cols="20">${prod.detail}</textarea>
        </div>
        <div class="form-group input-group-sm">
            <label for="image">展示图:</label>
            <input type="file" name="image" id="image" ${prod.id==null? "required":""} />
        </div>
        <div class="form-group input-group-sm">
            <label for="stock">库存数量:</label>
            <input type="number" name="stock" id="stock" class="form-control" min="1" value="${prod.stock}" required/>
        </div>
        <div class="form-group input-group-sm">
            <label for="price">单价:</label>
            <input type="number" name="price" id="price" class="form-control" min="0.00" max="20000.00" step="0.01" value="${prod.price}" required/>
        </div>
         <br/>
        <button type="submit" class="btn btn-primary btn-block" id="bt">${prod.id==null?"添加":"更新"}</button>
        <a href="/laptopmall/product_list" class="btn btn-primary btn-block" role="button">返回</a>
    </form>
</div>
</body>
</html>