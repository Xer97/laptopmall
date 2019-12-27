<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>笔记本商城·注册</title>
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
<br/>
<br/>
<div id="main">
	<h4 style="text-align: center">我的信息</h4>

    <form action="/laptopmall/save_user" method="post" class="input-group-sm">
        <div class="form-group input-group-sm">
            <label for="login_name">登录名:</label><span style="float: right;color:red;">${info}</span>
            <input type="text" name="login_name" id="login_name" class="form-control"
                    maxlength="20" value="${CURRENT_USER.loginName}" disabled required/>
        </div>
        <!-- <div class="form-group input-group-sm">
            <label for="password">输入密码:</label>
            <input type="password" name="password" id="password" class="form-control password" required/>
        </div>
        <div class="form-group input-group-sm">
            <label for="checkPassword">确认密码:</label><span id="s_password" style="float: right;color:red;">${pwdMsg}</span>
            <input type="password" name="check_password" id="checkPassword" class="form-control password" required/>
        </div> -->
        <div class="form-group input-group-sm">
            <label for="real_name">真实姓名:</label>
            <input type="text" name="real_name" id="real_name" class="form-control" value="${CURRENT_USER.realName}" required/>
        </div>
        <div class="form-group input-group-sm">
            <label for="phone">联系方式:</label>
            <input type="text" name="phone" id="phone" class="form-control" value="${CURRENT_USER.phone}" required/>
        </div>
        <div class="form-group input-group-sm">
            <label for="address">收货地址:</label>
            <input type="text" name="address" id="address" class="form-control" value="${CURRENT_USER.address}" required/>
        </div>
         <br/>
        <button type="submit" class="btn btn-primary btn-block" id="bt">保存</button>
    </form>
</div>
</body>
</html>