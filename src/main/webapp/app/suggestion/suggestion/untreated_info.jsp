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
    <title>banner图片修改</title>
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
  
    <script>
    
    // ------------------------------------------------通用部分(开始)--------------------------------------------------------
    // 初始化方法
     $(function(){
    	if( "${genre}".trim()=="1" ){
    		$("#genre").html("视频相关");
    	}
    	if( "${genre}".trim()=="2" ){
    		$("#genre").html("活动相关");
    	}
    	if( "${genre}".trim()=="3" ){
    		$("#genre").html("软件bug相关");
    	}
    	if( "${genre}".trim()=="4" ){
    		$("#genre").html("讲师相关");
    	}
    	if( "${genre}".trim()=="5" ){
    		$("#genre").html("普通用户相关");
    	}
    	if( "${genre}".trim()=="6" ){
    		$("#genre").html("支付相关");
    	}
    	if( "${genre}".trim()=="7" ){
    		$("#genre").html("其他相关");
    	}
    	
    } )
    
    	
    	
    // ------------------------------------------------通用部分(结束)--------------------------------------------------------
    
    
    // ------------------------------------------------AJAX(开始)--------------------------------------------------------
    
    // ------------------------------------------------AJAX(结束)--------------------------------------------------------
    </script>

  
	
  
</head>

<body class="gray-bg">
	
    <div class="wrapper wrapper-content animated fadeInRight ">
        
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>回访反馈信息</h5>
                        <div class="ibox-tools">
                            <button onclick="JavaScript:history.back(-1);" type="button" class="btn btn-w-m btn-white btn-xs"><i class="fa fa-reply"></i>返回</button>
                        </div>
                    </div>
                    <div class="ibox-content">
                   <c:forEach items="${requestScope.code.data.list }" var="data">
                        <form enctype="multipart/form-data" class="form-horizontal m-t" id="commentForm" 
							method = 'post'  action = '${pageScope.basePath }suggestion/updateCount.do'>
							<input type="hidden" name="user_id" value="${data.user_id}">
							<input type="hidden" name="id" value="${data.id}">
							
                    <div class="ibox-content">
                          <table class="table table-bordered">
                            <tbody>
                               <tr>
                                    <td style="background-color:#f3f3f4; width:15%;">手机号：</td>
                                    <td colspan="3">
                                        ${data.phone_num}
                                    </td>
                                </tr>
                                <tr>
                                    <td style="background-color:#f3f3f4; width:15%;">内容：</td>
                                    <td colspan="3">
                                        ${data.content }
                                    </td>
                                </tr>
                               
                                <tr>
                                    <td style="background-color:#f3f3f4; width:15%;">反馈类型</td>
                                    <td colspan="3">
                                      <c:if test="${data.genre eq '1' }">
												<small>视频相关</small>
												</c:if>
												<c:if test="${data.genre eq '2' }">
												<small>活动相关</small>
												</c:if>
												<c:if test="${data.genre eq '3' }">
												<small>软件bug相关</small>
												</c:if>
												<c:if test="${data.genre eq '4' }">
												<small>讲师相关</small>
												</c:if>
												<c:if test="${data.genre eq '5' }">
												<small>普通用户相关</small>
												</c:if>
												<c:if test="${data.genre eq '6' }">
												<small>支付相关</small>
												</c:if>
												<c:if test="${data.genre eq '7' }">
												<small>其他</small>
												</c:if>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                       </div>
                        
                       <div class="form-group">
                                <label class="col-sm-3 control-label">回复反馈：</label>
                                <div class="col-sm-8">
                                    <input id="recount" name="recount" minlength="2" value="${data.recount_id} " placeholder="请输入活动标题" type="textarea" class="form-control" required="" aria-required="true">
                                     
                                </div>
                            </div> 
                  
                  
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3">
                                <c:if test="${data.status eq '0' }">
                                    <button class="btn btn-primary" type="submit">提交</button>
                                    </c:if>
                                    <c:if test="${data.status eq '1' }">
                                    </c:if>
                                </div>
                            </div>
                            
                        </form>
                        </c:forEach>
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
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>