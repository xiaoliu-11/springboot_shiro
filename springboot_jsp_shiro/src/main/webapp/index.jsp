<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>系统</title>
</head>
<body>
asdf

<ul>
    <li><a href="">增</a>
    <ul>
        <shiro:hasPermission name="user:add:*">
            <li><a href="">增加一个</a></li>
        </shiro:hasPermission>


        <shiro:hasPermission name="user:update:*">
            <li><a href="">增加2个</a></li>
        </shiro:hasPermission>
        <shiro:hasPermission name="user:select:*">
            <li><a href="">增加3个</a></li>
        </shiro:hasPermission>
    </ul>

    </li>

    <shiro:hasRole name="admin">
    <li><a href="">珊</a></li>
    <li><a href="">改</a></li>
    <li><a href="">查</a></li>
    </shiro:hasRole>
</ul>

<br />
<br />
<br />
<br />
<hr />
<a href="${pageContext.request.contextPath}/user/logout">退出</a>

</body>
</html>