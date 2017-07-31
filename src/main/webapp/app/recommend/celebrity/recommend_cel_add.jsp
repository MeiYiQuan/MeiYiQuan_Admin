<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="qc" uri="http://qc/qcTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/" ;
String conditions = 
			"orderNum=" + (request.getAttribute("orderNum")==null?"":request.getAttribute("orderNum").toString()) + 
			"&createTime=" + (request.getAttribute("createTime")==null?"":request.getAttribute("createTime").toString()) + 
			"&updateTime=" + (request.getAttribute("updateTime")==null?"":request.getAttribute("updateTime").toString()) + 
			"&bannerType=" + (request.getAttribute("bannerType")==null?"":request.getAttribute("bannerType").toString()) + 
			"&status=" + (request.getAttribute("status")==null?"":request.getAttribute("status").toString()) + 
			"&createBegin=" + (request.getAttribute("createBegin")==null?"":request.getAttribute("createBegin").toString()) + 
			"&createEnd=" + (request.getAttribute("createEnd")==null?"":request.getAttribute("createEnd").toString()) + 
			"&updateBegin=" + (request.getAttribute("updateBegin")==null?"":request.getAttribute("updateBegin").toString()) + 
			"&updateEnd=" + (request.getAttribute("updateEnd")==null?"":request.getAttribute("updateEnd").toString());
pageContext.setAttribute("basePath", basePath);
pageContext.setAttribute("conditions", conditions);
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
    <script type="text/javascript" src="${pageScope.basePath }app/js/My97DatePicker/WdatePicker.js"></script>
    <script>
    
    // ------------------------------------------------通用部分(开始)--------------------------------------------------------
    // 初始化方法
    	/* $(function(){
    		if("${requestScope.message}".trim()!=""){layer.alert("${requestScope.message}");}
    		$("#eachPages").val(pageEach);
    	}); */
    	
    	
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
                        <h5>新增首页推荐</h5>
                        <div class="ibox-tools">
                            <button onclick="JavaScript:history.back(-1);" type="button" class="btn btn-w-m btn-white btn-xs"><i class="fa fa-reply"></i>返回</button>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form enctype="multipart/form-data" class="form-horizontal m-t" id="commentForm" 
							method = 'post'  action = '${pageScope.basePath }rdcelebrity/saveRecommend.do'>
							<div class="form-group">
                                <label class="col-sm-3 control-label">推荐名称：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" value="" placeholder="推荐至首页的名称" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">上传推荐首页图片：</label>
                                <div class="col-sm-8" style="position: relative;" id="imgs">
                                <input type="file" id="file0" name="file0"  class="filess" onchange="preImg(this.id,'imgShow_WU_FILE_0');"  />
                                    <img style="width:200px; heihgt:150px;" id="imgShow_WU_FILE_0"   class="feed-photo" src="">
                                	<input id="pic_url" value="" type="hidden" name="pic_url"/>
                                </div>
                            </div>
	                                   <div class="form-group">
									       <label class="col-sm-3 control-label">推荐首页类型列表：</label>
									       <div class="col-sm-9">
									           <select class="form-control" name="type" id="type"  >
									        	<option value="1">视频</option>
										        <option value="2">名人大佬</option>
										        <option value="3">开店创业</option>
										        <option value="4">潮流技术</option>
										        </select>
									       </div>
									   </div>
                                    <div>
                                    <label class="col-sm-3 control-label">关联id：</label>
                                		<div class="col-sm-8">
                                         <input id="relation_id" name="relation_id" value="" minlength="2" placeholder="请输入关联id" type="text" class="form-control" >
                                    	</div>
                                    </div>
								<div class="form-group">
								</div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">是否置顶：</label>
                                <div class="col-sm-8">
                                     <div class="radio checkbox-inline">
                                         <input type="radio" name="top_num" id="top_num" value="1" checked="">
                                         <label for="radio1">置顶</label>
                                    </div>
                                    <div class="radio checkbox-inline">
                                         <input type="radio" name="top_num" id="top_num" value="-1" checked="">
                                         <label for="radio1">不置顶</label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">提交</button>
                                </div>
                            </div>
                            
                        </form>
                    </div>
                </div>
               
            </div>
            
        </div>
    </div>
    <script src="${pageScope.basePath }js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageScope.basePath }js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageScope.basePath }js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${pageScope.basePath }js/plugins/validate/messages_zh.min.js"></script>
    <script src="${pageScope.basePath }js/demo/form-validate-demo.min.js"></script>
    <script src="${pageScope.basePath }js/plugins/peity/jquery.peity.min.js"></script>
    <script src="${pageScope.basePath }js/plugins/fancybox/jquery.fancybox.js"></script>
    <script>
        $(document).ready(function(){$(".fancybox").fancybox({openEffect:"none",closeEffect:"none"})});
       
     /*  $("#imgs img").click(function(){
        	$("#file0").click(){
        		
        	}
        }); */
        

        /** 
        * 从 file 域获取 本地图片 url 
        */ 
        function getFileUrl(sourceId) { 
        var url; 
        if (navigator.userAgent.indexOf("MSIE")>=1) { // IE 
        url = document.getElementById(sourceId).value; 
        } else if(navigator.userAgent.indexOf("Firefox")>0) { // Firefox 
        url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0)); 
        } else if(navigator.userAgent.indexOf("Chrome")>0) { // Chrome 
        url = window.URL.createObjectURL(document.getElementById(sourceId).files.item(0)); 
        } 
        return url; 
        } 

        /** 
        * 将本地图片 显示到浏览器上 
        */ 
        function preImg(sourceId, targetId) { 
        var url = getFileUrl(sourceId); 
        var imgPre = document.getElementById(targetId); 
        imgPre.src = url;
        $("#pic_url").attr("value",url);
        } 
    	
        
    </script>  </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>