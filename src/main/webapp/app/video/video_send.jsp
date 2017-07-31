<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*"%>    
<%@ page import="java.text.*"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>视频添加</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="../app/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="../app/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="../app/css/animate.min.css" rel="stylesheet">
    <link href="../app/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="../app/js/My97DatePicker/skin/default/datepicker.css" rel="stylesheet">
    <link href="../app/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
	<script src="../app/js/jquery.min.js?v=2.1.4"></script>
	<script src="../app/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script type="text/javascript" src="../app/js/My97DatePicker/WdatePicker.js"></script>
    <script>
    </script>
    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="../app/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="../app/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="../app/css/animate.min.css" rel="stylesheet">
    <link href="../app/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="../app/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="../app/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../app/oss/style.css"/>
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
                        <h5>视频添加</h5>
                        <div class="ibox-tools">
                            <button onclick="JavaScript:history.back(-1);" class="btn btn-w-m btn-white btn-xs"   type="button" ><i class="fa fa-reply"></i>返回</button>
                        </div>
                    </div>
                    <div class="ibox-content">
                    	
                        <form  class="form-horizontal m-t" id="addForm" 
							method = 'post'  action = '../banner/add'>
                           
                            
                            
                            
                           <div class="form-group">
                                <label class="col-sm-3 control-label">视频：</label>
                                <div class="col-sm-8">
                                  <h4>您所选择的文件列表：</h4>
								  <div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>
                                  <input type="radio" name="myradio" style="display: none;" value="random_name" checked=true />
                                  <input type="hidden" id="fileData" name="fileData"  value=""   />
                                  <input type="hidden" id="fileSize" name="fileSize"  value=""   />
                                  <input type="hidden" id="fileTolTime" name="fileTolTime"  value=""   />
                                   <!-- 上传到指定目录:如果不填，默认是上传到根目录 -->
                                   <% String datetime=new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()); //获取系统时间  %>
                                   <input class="form-control" type="hidden" id='dirname'   size=50 value="video/<%=datetime%>">
	                                   <div id="container">
											<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
											<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
									    </div>
							      <div id="pic">
											 <!-- <video id="myVideo" controls preload="auto"  
		                        				src="http://meifaxuetang.oss-cn-beijing.aliyuncs.com/video/20170210/DfmN4wZiZd.mp4">
		                        			</video> -->
								   </div>
                                   <!-- <pre id="console"></pre> -->
									<p>&nbsp;</p>
									
									</div>
									<!-- <button id="capture">Capture</button>
   									<div id="output"></div> -->
							</div>
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">视频：</label>
                                <div class="col-sm-8">
                                  <h4>您所选择的文件列表：</h4>
								  <div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>
                                  <input type="radio" name="myradio" style="display: none;" value="random_name" checked=true />
                                  <input type="hidden" id="fileData" name="fileData"  value=""   />
                                  <input type="hidden" id="fileSize" name="fileSize"  value=""   />
                                  <input type="hidden" id="fileTolTime" name="fileTolTime"  value=""   />
                                   <!-- 上传到指定目录:如果不填，默认是上传到根目录 -->
                                   <% String datetime1=new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()); //获取系统时间  %>
                                   <input class="form-control" type="hidden" id='dirname'   size=50 value="video/<%=datetime%>">
	                                   <div id="container">
											<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
											<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
									    </div>
							      <div id="pic">
											 <!-- <video id="myVideo" controls preload="auto"  
		                        				src="http://meifaxuetang.oss-cn-beijing.aliyuncs.com/video/20170210/DfmN4wZiZd.mp4">
		                        			</video> -->
								   </div>
                                   <!-- <pre id="console"></pre> -->
									<p>&nbsp;</p>
									
									</div>
									<!-- <button id="capture">Capture</button>
   									<div id="output"></div> -->
							</div>
							
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">视频：</label>
                                <div class="col-sm-8">
                                  <h4>您所选择的文件列表：</h4>
								  <div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>
                                  <input type="radio" name="myradio" style="display: none;" value="random_name" checked=true />
                                  <input type="hidden" id="fileData" name="fileData"  value=""   />
                                  <input type="hidden" id="fileSize" name="fileSize"  value=""   />
                                  <input type="hidden" id="fileTolTime" name="fileTolTime"  value=""   />
                                   <!-- 上传到指定目录:如果不填，默认是上传到根目录 -->
                                   <% String datetime2=new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()); //获取系统时间  %>
                                   <input class="form-control" type="hidden" id='dirname'   size=50 value="video/<%=datetime%>">
	                                   <div id="container">
											<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
											<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
									    </div>
							      <div id="pic">
											 <!-- <video id="myVideo" controls preload="auto"  
		                        				src="http://meifaxuetang.oss-cn-beijing.aliyuncs.com/video/20170210/DfmN4wZiZd.mp4">
		                        			</video> -->
								   </div>
                                   <!-- <pre id="console"></pre> -->
									<p>&nbsp;</p>
									
									</div>
									<!-- <button id="capture">Capture</button>
   									<div id="output"></div> -->
							</div>
							
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">视频：</label>
                                <div class="col-sm-8">
                                  <h4>您所选择的文件列表：</h4>
								  <div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>
                                  <input type="radio" name="myradio" style="display: none;" value="random_name" checked=true />
                                  <input type="hidden" id="fileData" name="fileData"  value=""   />
                                  <input type="hidden" id="fileSize" name="fileSize"  value=""   />
                                  <input type="hidden" id="fileTolTime" name="fileTolTime"  value=""   />
                                   <!-- 上传到指定目录:如果不填，默认是上传到根目录 -->
                                   <% String datetime3=new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()); //获取系统时间  %>
                                   <input class="form-control" type="hidden" id='dirname'   size=50 value="video/<%=datetime%>">
	                                   <div id="container">
											<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
											<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
									    </div>
							      <div id="pic">
											 <!-- <video id="myVideo" controls preload="auto"  
		                        				src="http://meifaxuetang.oss-cn-beijing.aliyuncs.com/video/20170210/DfmN4wZiZd.mp4">
		                        			</video> -->
								   </div>
                                   <!-- <pre id="console"></pre> -->
									<p>&nbsp;</p>
									
									</div>
									<!-- <button id="capture">Capture</button>
   									<div id="output"></div> -->
							</div>
									
									
									
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3">
                                    <a href="javascript:dosubmit()" class="btn btn-primary"  >提交</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
               
            </div>
            
        </div>
    </div>
    <script src="../app/js/jquery.min.js?v=2.1.4"></script>
    <script src="../app/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="../app/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="../app/js/plugins/validate/messages_zh.min.js"></script>
    <script src="../app/js/demo/form-validate-demo.min.js"></script>
    <script src="../app/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="../app/js/plugins/fancybox/jquery.fancybox.js"></script>
     <script src="../app/js/layer/layer.js"></script>
     <script type="text/javascript" charset="utf-8" src="../app/js/ajaxfileupload.js"></script>
 <script>
        $(document).ready(function(){$(".fancybox").fancybox({openEffect:"none",closeEffect:"none"})});
 
        var inputArr = document.getElementsByName("fileData");//取得页面标签元素数组
        for(var i=0;i<fileData.length;i++){//循环赋值
            inputArr[i].value = arr[i];
        }
</script>  
<script type="text/javascript" src="../app/oss/lib/crypto1/crypto/crypto.js"></script>
<script type="text/javascript" src="../app/oss/lib/crypto1/hmac/hmac.js"></script>
<script type="text/javascript" src="../app/oss/lib/crypto1/sha1/sha1.js"></script>
<script type="text/javascript" src="../app/oss/lib/base64.js"></script>
<script type="text/javascript" src="../app/oss/lib/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="../app/oss/upload.js"></script>
        </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>