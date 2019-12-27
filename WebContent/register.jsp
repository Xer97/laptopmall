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
        div {
            margin: 0 auto;
            width: 300px;
        }
        #main {
        	margin-top: 100px;
        }
        
    </style>
</head>
<body>
<br/>
<br/>
<div id="main">
	<h4 style="text-align: center">注 册</h4>

    <%
        session.invalidate();	//清空Session
    %>
    <form action="register" method="post" class="input-group-sm">
        <div class="form-group input-group-sm">
            <label for="login_name">登录名:</label><span id="s_username" style="float: right;color:red;">${loginNameMsg}</span>
            <input type="text" name="login_name" id="login_name" class="form-control"
                   onKeyUp="value=value.replace(/[^\w\.\/]/ig,'')" maxlength="20" required/>
        </div>
        <div class="form-group input-group-sm">
            <label for="password">输入密码:</label>
            <input type="password" name="password" id="password" class="form-control password" required/>
        </div>
        <div class="form-group input-group-sm">
            <label for="checkPassword">确认密码:</label><span id="s_password" style="float: right;color:red;">${pwdMsg}</span>
            <input type="password" name="check_password" id="checkPassword" class="form-control password" required/>
        </div>
        <a href="/laptopmall/login.jsp">已有账号，点我去登录</a>
        <button type="submit" class="btn btn-primary btn-block" id="bt">注册</button>
    </form>
</div>
</body>
</html>