<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SIAS商铺管理系统</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
    <script>


        function fun_onblur() {
            //正则表达式
            var reg=/^[0-9]{7,10}$/;
            var account=document.getElementById("account");
            var accountValue=account.value;
            if(reg.test(accountValue)){
                alert("账号符合规则！");
            }else {
                alert("帐号不符合规则！请重新输入");
                account.value="";
            }
        }

        function fun_onmouseover() {
            if(document.getElementById("account").value==""){
                document.getElementById("right").setAttribute("position","absolute").value="请输入10位数字的账号！";
            }
        }

    </script>
</head>
<body>
<div id="tit">销售管理系统</div>
<div id="log">
    <p class="p">用户登录</p>
    <form action="${pageContext.request.contextPath}/admin/login.action" method="post">
        <input class="log_in" type="text" placeholder="请输入账号" name="account" onblur="fun_onblur()" onmouseover="fun_onmouseover()" id="account">
        <!--<div id="right" style="text-align: center;"></div>-->
        <input class="log_in" type="password" placeholder="请输入密码" name="password">
        <div class="log_cs">
            <input type="checkbox" > 记住密码 &nbsp;&nbsp;&nbsp;&nbsp;
            <span style="color:#FF0000" id="right"> ${nameOrPasswordIsWrong }</span>
            <input  id="btn" class="log_sub" type="submit" value="登录">
        </div>
    </form>
    <div class="log_a">
        <a href="#">忘记密码？点击修改</a>
    </div>
</div>
</body>
</html>