<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>登录</title>
</head>
<body>
登录界面

<p style="color: red">${msg}</p>
<form action="${pageContext.request.contextPath}/user/login" method="post">
  用户名：  <input type="text" name="username"> <br />
    密码：   <input type="text" name="password"> <br />
    验证码： <input type="text"> <img src=""alt="" /> <br />

   <input type="submit" value="提交">

</form>
</body>
</html>