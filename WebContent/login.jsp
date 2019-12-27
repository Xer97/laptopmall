<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>笔记本商城·登录</title>
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
	<h4 style="text-align: center">登 录</h4>
    <span style="color: red;">${errMsg}</span>
    <%
        session.invalidate();
    %>
    <form action="login" method="post" class="input-group-sm">
        <div class="form-group input-group-sm">
            <label for="login_name">账号:</label>
            <input type="text" name="login_name" id="login_name" class="form-control" required/>
        </div>
        <div class="form-group input-group-sm">
            <label for="password">密码:</label>
            <input type="password" name="password" id="password" class="form-control" required/>
        </div>
        <a href="register.jsp">还没账号？点我去注册！</a>
        <button type="submit" class="btn btn-primary btn-block">登录</button>
    </form>
</div>
</body>
</html>