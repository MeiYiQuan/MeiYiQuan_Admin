<!--反馈列表-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <title>专题统计</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="${pageScope.basePath }app/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/animate.min.css" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageScope.basePath }app/js/My97DatePicker/skin/default/datepicker.css" rel="stylesheet">
    <link href="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${pageScope.basePath }app/css/paging.css">
	<script src="/mei_yi_quan_admin/app/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script type="text/javascript" src="${pageScope.basePath }app/js/My97DatePicker/WdatePicker.js"></script>
    <script src="${pageScope.basePath }app/js/layer/layer.js"></script>

    <script>
    // ------------------------------------------------通用部分(开始)--------------------------------------------------------
    
    	// 每页的行数,EL表达式取回的是默认值，这个值可以用js修改的
    
    	
    // ------------------------------------------------通用部分(结束)--------------------------------------------------------
      function submitForm(){
  	  $("#dataForm").submit();
  	  
   }
      function order(name){
    	  window.location.href = "${pageScope.basePath }special/tongji.do?stutas="+name+"";
      }
    
    
    // ------------------------------------------------AJAX(开始)--------------------------------------------------------
    // ------------------------------------------------AJAX(结束)--------------------------------------------------------
    </script>

</head>

<body class="gray-bg" style="padding:0px;margin:0px">

    <div class="wrapper wrapper-content animated fadeInUp" style="padding:0px;margin:0px">
        <div class="row">
            <div class="col-sm-12">

                <div class="ibox">
                    <div class="ibox-title">
                        <h5>统计列表</h5>
                        
                        <div class="input-group" align="left" style="float:left;background:white;width:100%;margin-left:0%;">
                           <span class="input-group-btn" style="font-size:15px">
                            	
                           </span>
                        </div>
                        
                        <div class="ibox-tools">
                            <!-- <a href="projects.html" class="btn btn-primary btn-xs">创建新项目</a> -->
                        </div>
                    </div>
                    <div class="ibox-content">
                    <div class="ibox float-e-margins">
                    <div class="ibox-content">
                       
                        <table class="table table-bordered">
                            <tbody>
                            
	                             	<tr>
                                			<td class="project-title" align="center">专题名称</td>
	                                        <td class="project-title" align="center">数量</td>
                                   	    </tr>
   									<c:forEach items="${requestScope.code.data.list }" var="data">                             	
                                	<tr id="tr${data.name }">
                                		<td class="project-title" align="center">
												<small>${data.name }</small>
                                        </td>
                               			
                               			<c:if test="${requestScope.stutas  eq 'click_count' }">
                               			<td class="project-title" align="center">
	                                           <small>${data.click_count }</small> 
                                         </td>
                                        </c:if>
                                        <c:if test="${requestScope.stutas  eq 'like_count' }">
                               			<td class="project-title" align="center">
	                                           <small>${data.like_count }</small> 
                                         </td>
                                        </c:if>
                                        <c:if test="${requestScope.stutas  eq 'share_count' }">
                               			<td class="project-title" align="center">
	                                           <small>${data.share_count }</small> 
                                         </td>
                                        </c:if>
                                        <c:if test="${requestScope.stutas  eq 'collect_count' }">
                               			<td class="project-title" align="center">
	                                           <small>${data.collect_count }</small> 
                                         </td>
                                        </c:if>
                                        <c:if test="${requestScope.stutas  eq 'play_count' }">
                               			<td class="project-title" align="center">
	                                           <small>${data.play_count }</small> 
                                         </td>
                                        </c:if>
                                        <c:if test="${requestScope.stutas  eq 'follow_count' }">
                               			<td class="project-title" align="center">
	                                           <small>${data.follow_count }</small> 
                                         </td>
                                        </c:if>
                                        <c:if test="${requestScope.stutas  eq 'comment_count' }">
                               			<td class="project-title" align="center">
	                                           <small>${data.comment_count }</small> 
                                         </td>
                                        </c:if>
                                        
                                	</tr>
                                
                                </c:forEach>
                            </tbody>
                        </table>
                        
                    </div>
              </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
    <script src="${pageScope.basePath }app/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageScope.basePath }app/js/content.min.js?v=1.0.0"></script>
    <script type="text/javascript" src="${pageScope.basePath }app/js/query.js"></script>
	<script type="text/javascript" src="${pageScope.basePath }app/js/paging.js"></script>
    <script>
   
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>地址查询开始>>>>>>>>>>>>>>>>>>.
    
    
    
       //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>地址查询结束>>>>>>>>>>>>>>>>>>.
    
    
    
    
       $(document).ready(function(){$("#loading-example-btn").click(function(){btn=$(this);simpleLoad(btn,true);simpleLoad(btn,false)})});function simpleLoad(btn,state){if(state){btn.children().addClass("fa-spin");btn.contents().last().replaceWith(" Loading")}else{setTimeout(function(){btn.children().removeClass("fa-spin");btn.contents().last().replaceWith(" Refresh")},2000)}};
       $(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>