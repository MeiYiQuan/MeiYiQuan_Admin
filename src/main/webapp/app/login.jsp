<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/" ;
pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">

    <title>美艺圈登录</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">
    <link href="${pageScope.basePath }app/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/font-awesome.min.css?v=4.4.0" type="text/css" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/animate.min.css" type="text/css" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/style.min.css" type="text/css" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/login.min.css" type="text/css" rel="stylesheet">
    <script src="${pageScope.basePath }app/js/jquery.min.js?v=2.1.4"></script>
</head>
<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>美艺圈</h1>
                    </div>
                    <div class="m-b"></div>
                    <h4>欢迎使用 <strong>美艺圈后台管理</strong></h4>
                </div>
            </div>
            <div class="col-sm-5">
                <form method="post" action="${pageScope.basePath }login/login.do">
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录到美艺圈后台主题UI框架</p>
                    <input type="text" name="loginName" class="form-control uname" placeholder="用户名" />
                    <input type="password" name="password" class="form-control pword m-b" placeholder="密码" />
                    
                    <button class="btn btn-success btn-block">登录</button>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            
        </div>
    </div>
</body>
</html>