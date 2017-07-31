<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/" ;
pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>投票详情</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!--标准mui.css-->
		<link rel="stylesheet" href="${pageScope.basePath }app/share/url/css/mui.min.css">
		<!--自定义的css-->
		<link rel="stylesheet" type="text/css" href="${pageScope.basePath }app/share/url/css/style.css"/>
		<style>
			.mui-icon-arrowright{
				color: #dadada;
			}
			.mui-table-view-chevron .mui-table-view-cell {
				padding-right: 38px;
			}
			.mui-table-view .mui-media-object {
				line-height: 42px;
				max-width: 60px;
				height: 60px;
			}
			.anniu{
			background-color: #FB5500;
    padding-top: 3px;
    padding-bottom: 3px;
    text-align: center;
    position: fixed;
    width: 100%;
    z-index: 999;
    bottom: 0px;}
	pre {
white-space: pre-wrap;
word-wrap: break-word;
}
		</style>
	</head>
	<body style="background-color:#fff">
		<div class="anniu"><a href="${requestScope.downloadUrl }" style="color:#fff;" >下载APP了解更多</a></div>
		<div class="mui-col-sm-12 mui-col-xs-12"><img src='${requestScope.data.head_url} '></div>
		<div class="mui-content" >
			<div class="common-setting">
				<div class="mui-col-sm-12 mui-col-xs-12"><h3>求教程投票详情</h3></div>
				<div class="mui-col-sm-12 mui-col-xs-12" style="margin-top:10px;">
				<span><img src="${pageScope.basePath }app/share/url/find_time.png" style="width:20px;" align="center"></span> 
				<span>求教程名称：${requestScope.data.course_name }</span> 
				</div>
				<div class="mui-col-sm-12 mui-col-xs-12" style="margin-top:10px;">
				<span><img src="${pageScope.basePath }app/share/url/find_place.png" style="width:20px;" align="center"></span> 
				<span>求教程讲师名称：${requestScope.data.name }</span> 
				</div>
				<div class="mui-col-sm-12 mui-col-xs-12" style="margin-top:10px;">
				<span><img src="${pageScope.basePath }app/share/url/find_Sponsor.png" style="width:20px;" align="center"></span> 
				<span>已投票数：${requestScope.data.con }</span> 
				</div>
				</div>
				<div class="mui-col-sm-12 mui-col-xs-12" style="margin:5px;"><pre>${requestScope.data.remark }</pre></div>
			</div>
	</body>
	<script type="text/javascript" src="${pageScope.basePath }app/share/url/js/mui.min.js"></script>
	
</html>
