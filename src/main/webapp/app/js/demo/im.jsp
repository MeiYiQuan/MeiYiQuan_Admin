<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/" ;
pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type='text/javascript' src='webim.config.js'></script>
<script type='text/javascript' src='strophe-1.2.8.min.js'></script>
<script type='text/javascript' src='websdk-1.4.5.js'></script>
<script src='http://downloads.easemob.com/downloads/cdn/websdk-1.4.5.js'></script>
<script src='http://downloads.easemob.com/downloads/cdn/websdk-1.4.5.min.js'></script>



<title>Insert title here</title>
</head>
<body>

</body>
</html>