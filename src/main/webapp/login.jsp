<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/page/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>后台管理系统</title>
        <link rel="stylesheet" type="text/css" href="${ctx}/js/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/js/themes/icon.css">
		<script type="text/javascript" src="${ctx}/js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx}/js/locale/easyui-lang-zh_CN.js"></script>
		<style type="text/css">
		a{text-decoration:none;color:black;}
		body{background-color:#DDD;}
		input,img{vertical-align:middle;}
		</style>
        <script>
         function check_f(){
        	
         if(!name||!pwd||!code){alert('请如入完整信息！');return false;}
         	document.login_f.submit();
         }
        </script>
</head>
<body>
<div style="margin:0 auto;width:800px;">
<div id="win" class="easyui-window" minimizable="false" maximizable="false"  collapsible="false"  title="Login" style="width:400px;height:300px;">
    <form action="${ctx}/login/login.jspx" method="post" style="padding:10px 20px 10px 40px;" name="login_f">
        <p>用户名: </p><input class="easyui-validatebox" type="text" id="n" name="name" required="true"/>
        <p>密&nbsp;&nbsp;码: </p><input class="easyui-validatebox"  id="pwd" type="password" name="password" required="true">
        <p>验证码: </p><input class="easyui-validatebox" type="text" id="n" name="ckcode" required="true" style="width:80px;"/>		<img src="${ctx}/verify/getVerifyCode.jspx" width="70" height="20" title="验证码"/>
        <div style="padding:5px;text-align:center;">
            <a href="#" class="easyui-linkbutton" icon="icon-ok" onclick="check_f()">登录</a>
            <a href="#" class="easyui-linkbutton" icon="icon-cancel" onclick="document.login_f.reset()">重置</a>
        </div>
    </form>
</div>
</div>

</body>
</html>
