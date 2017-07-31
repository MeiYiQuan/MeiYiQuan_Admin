<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="qc" uri="http://qc/qcTag" %>
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
    <title>评论详情</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="${pageScope.basePath }app/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="${pageScope.basePath }app/css/animate.min.css" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageScope.basePath }app/js/My97DatePicker/skin/default/datepicker.css" rel="stylesheet">
    <link href="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
	<script src="${pageScope.basePath }app/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script type="text/javascript" src="${pageScope.basePath }app/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/app/UEditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/app/UEditor/ueditor.all.min.js"> </script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/app/UEditor/lang/zh-cn/zh-cn.js"></script>
  
    <script>
    
    // ------------------------------------------------通用部分(开始)--------------------------------------------------------
    // 初始化方法
    	function lahei(){
    		window.location.href = "${pageScope.basePath }course/blackComent.do?id=${requestScope.topid}&topid=${requestScope.topid}";
    	}
    	function huifu(){
    		window.location.href = "${pageScope.basePath }course/reginaComent.do?id=${requestScope.topid}&topid=${requestScope.topid}";
    	}
    // ------------------------------------------------通用部分(结束)--------------------------------------------------------
    
    
    // ------------------------------------------------AJAX(开始)--------------------------------------------------------
    
    // ------------------------------------------------AJAX(结束)--------------------------------------------------------
    </script>

  
	
    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
    <style>
    #file0{
      width:200px;
      height:120px;
      position: absolute;
      top:0;
      opacity:0;
    }
    </style>
</head>

<body class="gray-bg">
	
    <div class="wrapper wrapper-content animated fadeInRight ">
        
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>评论详情</h5>
                        <div class="ibox-tools">
                            <button id="fanhui" onclick="JavaScript:history.back(-1);" type="button" class="btn btn-w-m btn-white btn-xs"><i class="fa fa-reply"></i>返回</button>
                        </div>
                    </div>
                    <div class="ibox-content">
                   
                        <form enctype="multipart/form-data" class="form-horizontal m-t" id="commentForm" 
							method = 'post'  action = '${pageScope.basePath }askcourse/updateInfo.do'>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-8">
                                    <input id="course_name" name="course_name" minlength="2" value="${requestScope.video.title} " placeholder="请输入活动标题" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">评论人名称：</label>
                                <div class="col-sm-8">
                                    <input id="user_name" name="user_name" minlength="2" value="${requestScope.user.username }" placeholder="请输入讲师id" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            
                          
                            <div class="form-group">
                                <label class="col-sm-3 control-label">评论内容：</label>
                                <div class="col-sm-8">
                                    <input id="comm_content" name="comm_content" minlength="2" value="${requestScope.content.comm_content }" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            
                             <div class="form-group">
                                <label class="col-sm-3 control-label">评论状态：</label>
                                <small>
													<c:if test="${requestScope.content.status==2 }">正常</c:if>
													<c:if test="${requestScope.content.status==1 }">已被拉黑</c:if>
								</small>
                            </div>
                            
                            <div class="project-list">
                            	二级评论
                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
	                                       	<td class="project-title" align="center">评论人名称</td>
	                                       	<td class="project-title" align="center">评论内容</td>
	                                       	<td class="project-title" align="center">评论状态</td>
	                                        <td class="project-completion" align="center" >操作</td>
                                   	    </tr>
                                	<c:forEach items="${requestScope.list }" var="data">
                                		<tr>
	                                        <td class="project-title" align="center">
													<small>
													${data.username }
													</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.comm_content }
													</small>
	                                        </td>
	                                       <td class="project-title" align="center">
												<small>
													<c:if test="${data.status eq '2' }">正常</c:if>
													<c:if test="${data.status eq '1' }">已被拉黑</c:if>
												</small>
	                                        </td>
	                                        <td class="project-completion" align="center">
	                                            <a href="${pageScope.basePath }course/blackComent.do?id=${data.id}&topid=${requestScope.topid}" class="btn btn-white btn-sm" ><i class="fa fa-pencil"></i>恢复 </a>
	                                            <a href="${pageScope.basePath }course/reginaComent.do?id=${data.id}&topid=${requestScope.topid}" class="btn btn-white btn-sm" ><i class="fa fa-pencil"></i>拉黑 </a>
	                                        </td>
                                   	    </tr>
                                	</c:forEach>
                                    </tbody>
                                </table>
									<div id="pageToolbar"></div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3">
                               		 <button type="button" class="btn btn-sm btn-primary" onclick="lahei()">恢复</button>
                                     <button class="btn btn-sm btn-primary" type="button" onclick="huifu()">拉黑</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageScope.basePath }app/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageScope.basePath }app/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageScope.basePath }app/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${pageScope.basePath }app/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${pageScope.basePath }app/js/demo/form-validate-demo.min.js"></script>
    <script src="${pageScope.basePath }app/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script>
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>联动查询结束>>>>>>>>>>>>>>>>>>>>>>  
    </script>  </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>