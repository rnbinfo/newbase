<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>演示系统 - 登录</title>
</head>
<body>
<div id="content">
    <div class="login-header">
        演示系统
    </div>
    <form th:action="@{/login}" method="post">
        <div class="login-input-box">
            <span class="icon icon-user"></span>
            <input type="text" name="username" placeholder="请输入账户名">
        </div>
        <div class="login-input-box">
            <span class="icon icon-key"></span>
            <input type="password" name="password" placeholder="请输入密码">
        </div>
        <div class="login-button-box">
            <button type="submit">登录</button>
        </div>
    </form>
    <div th:if="${param.error}">
        <label style="color:red">用户名或密码不正确</label>
    </div>
</div>
</body>
</html>