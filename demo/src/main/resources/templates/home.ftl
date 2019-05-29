<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>演示系统</title>
</head>
    <div>欢迎您，[[${#httpServletRequest.remoteUser}]], 点击这里<a href="/logout">退出</a></div>
    <p/>
    <div><a href="/user">点击这里到用户页面</a></div>
    <p/>
    <div><a href="/admin">点击这里到管理员页面</a></div>
<body>
</body>
</html>