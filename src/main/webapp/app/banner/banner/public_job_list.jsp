<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/" ;
pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>课程列表</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="${pageScope.basePath }app/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/animate.min.css" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageScope.basePath }app/js/My97DatePicker/skin/default/datepicker.css" rel="stylesheet">
    <link href="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${pageScope.basePath }app/css/paging.css">
	 <script src="${pageScope.basePath }app/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script type="text/javascript" src="${pageScope.basePath }app/js/My97DatePicker/WdatePicker.js"></script>
    <script src="${pageScope.basePath }app/js/layer/layer.js"></script>

    <script>
    // ------------------------------------------------通用部分(开始)--------------------------------------------------------
    
    
    	// 初始化方法
    	/* $(function(){
    		if("${requestScope.message}".trim()!=""){layer.alert("${requestScope.message}");}
    		$("#eachPages").val(pageEach);
    	}); */
    	
    	function order(name){
    		if(name!="0"){
    			 window.location.href ="${pageScope.basePath }message/jobList.do?type="+name+"";
    		}else{
    			window.location.href ="${pageScope.basePath }message/publicList.do";
    		}
      	  	
        }
    	
    	function add(){
   		 window.location.href ="${pageScope.basePath }message/toadd.do?type=${type }";
   		s}
    
    	
    // ------------------------------------------------通用部分(结束)--------------------------------------------------------
    
    
    
    // ------------------------------------------------AJAX(开始)--------------------------------------------------------
    
    // 点击删除
    
    
    // ------------------------------------------------AJAX(结束)--------------------------------------------------------
    </script>

</head>

<body class="gray-bg" style="padding:0px;margin:0px">

    <div class="wrapper wrapper-content animated fadeInUp" style="padding:0px;margin:0px">
        <div class="row">
            <div class="col-sm-12">

                <div class="ibox">
                    <div class="ibox-title">
                        <h5>公共参数管理</h5>
                    
                        <div class="ibox-tools">
                            <!-- <a href="projects.html" class="btn btn-primary btn-xs">创建新项目</a> -->
                        </div>
                         <div class="input-group" align="right">
                   			<span class="input-group-btn" style="font-size:15px">
                               	<button type="button" class="btn btn-sm btn-primary" onclick="return order('3')" >年龄公共参数管理</button>
                            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            	<button type="button" class="btn btn-sm btn-primary" onclick="return order('1')" >职业公共参数管理</button>
                            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            	<button type="button" class="btn btn-sm btn-primary" onclick="return order('2')" >讲师等级参数管理</button>
                            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            	<button type="button" class="btn btn-sm btn-primary" onclick="return order('0')" >默认公共参数管理</button>
                            	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                       	 		<button type="button" class="btn btn-sm btn-primary" onclick="return add()" >添加</button>
                        	 </span>
                    	 </div>
                    </div>
                    
                    <div id="tab-1" class="ibox-content">
                        <div class="project-list">
                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
	                                       	<td class="project-title" align="center">公共参数顺序</td>
	                                       	<td class="project-title" align="center">公共参数内容</td>
	                                        <td class="project-completion" align="center" >操作</td>
                                   	    </tr>
                                	<c:forEach items="${requestScope.code.data.list }" var="data">
                                		<tr id="tr${data.id }">
	                                        <td class="project-title" align="center">
												<small>${data.orderInt }</small>
	                                        </td>
	                                        
	                                     
	                                        
	                                        <td class="project-title" align="center">
												<small>${data.job_name }</small>
	                                        </td>
	                                       
	                                        <td class="project-completion" align="center">
	                                            <a href="${pageScope.basePath }message/jobInfo.do?id=${data.id}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>编辑 </a>
	                                        </td>
                                   	    </tr>
                                	</c:forEach>
                                	
                                	
                                    </tbody>
                                </table>
									<div id="pageToolbar"></div>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
    <script type="text/javascript" src="${pageScope.basePath }app/js/query.js"></script>
    <script src="${pageScope.basePath }app/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageScope.basePath }app/js/content.min.js?v=1.0.0"></script>
   	
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>