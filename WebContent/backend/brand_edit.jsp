<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>笔记本商城·编辑商品</title>
<link href="/laptopmall/css/bootstrap.css" rel="stylesheet">
<script src="/laptopmall/js/jquery-3.3.1.min.js"></script>
<script src="/laptopmall/js/bootstrap.min.js"></script>
	<style type="text/css">
        .form-group,#main {
            margin: 0 auto;
            width: 300px;
        }
        #main {
        	margin-top: 100px;
        }
        
    </style>
</head>
<body>
<%@ include file="/common/top_bar.jsp" %>
<div id="main">
	<h4 style="text-align: center">新增品类</h4>

    <form action="/laptopmall/save_brand" method="post" class="input-group-sm">
        <div class="form-group input-group-sm">
            <label for="brand_name">品类名:</label><span style="float: right;color:red;">${info}</span>
            <input type="text" name="brand_name" id="brand_name" class="form-control"
                    maxlength="20"  required/>
        </div>
         <br/>
        <button type="submit" class="btn btn-primary btn-block" id="bt">添加</button>
        <a href="/laptopmall/product_list" class="btn btn-primary btn-block" role="button">返回</a>
    </form>
</div>
</body>
</html>