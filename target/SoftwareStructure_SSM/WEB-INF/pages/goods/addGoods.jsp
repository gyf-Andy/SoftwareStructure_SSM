<%--
  Created by IntelliJ IDEA.
  User: 22413
  Date: 2020/11/2
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/goods/addGoods.action" method="post" enctype="multipart/form-data">
        文件名：<input type="text" name="g_name">
        选择文件：<input type="file" name="g_img">
                 <input type="submit" value="点击提交">
    </form>
</body>
</html>
