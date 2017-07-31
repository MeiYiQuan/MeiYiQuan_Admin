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
                    	<form  class="form-horizontal m-t"   id="index" 
							method = 'post'  action = 'getVideoList'>
							<input type="hidden" name="id" value="${requestScope.id}">
							</form>
                        <form  class="form-horizontal m-t" id="addForm" 
							method = 'post'  action = '../banner/add'>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="title" minlength="2" value="" placeholder="请输入名称" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                               <div class="form-group">
                                <label class="col-sm-3 control-label">是否免费：</label>
                                <div class="col-sm-8">
                                	  <select class="form-control m-b" name="free" id="free" onchange="upisfree()">
                                  		<option value="1">免费</option>
                                  		<option value="2"  selected="selected">收费</option>
                               </select>
                                </div>
                                </div>
                             <div class="form-group isfree">
                                <label class="col-sm-3 control-label">价格：</label>
                                <div class="col-sm-8" >
                                	   <input  name="per_cost"   value="" placeholder="请输入价格" type="text" class="form-control" >
                                </div>
                            </div>
                            <div class="form-group isfree">
                                <label class="col-sm-3 control-label">免费时长：</label>
                                <div class="col-sm-8" >
                                	   <input  name="freeTime"   value="" placeholder="请输入免费时长" type="text" class="form-control" >
                                </div>
                            </div>
                              <div class="form-group isfree">
                                <label class="col-sm-3 control-label">能否使用优惠券：</label>
                                <div class="col-sm-8" >
                                	  <input type="radio" name="canUseCoupon" value="1" checked="checked">是
                                	  <input type="radio" name="canUseCoupon" value="2">否
                                </div>
                            </div>
                            
                             <div class="form-group">
                                <label class="col-sm-3 control-label">选择上传视频方法：</label>
                                <div class="col-sm-8">
                                	  <select class="form-control m-b" name="type" id="type" >
                                  		<option value="1">链接上传</option>
                                  		<option value="0" selected="selected">即时上传</option>
                           			  </select>
                                </div>
                             </div>
                            
                            
                            <div class="form-group 1">
                                <label class="col-sm-3 control-label">链接地址：</label>
                                <div class="col-sm-8">
                                    <input id="video_url" name="video_url" minlength="2" value="" placeholder="请输入链接地址" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            <div class="form-group 2">
                                <label class="col-sm-3 control-label">视频时长:</label>
                                <div class="col-sm-8">
                                    <input id="video_tl" name="video_tl" minlength="2" value="" placeholder="请输入视频时长,例如  1时20分35秒" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            <div class="form-group 3">
                                <label class="col-sm-3 control-label">视频大小：</label>
                                <div class="col-sm-8">
                                    <input id="video_sz" name="video_sz" minlength="2" value="" placeholder="请输入视频大小,例如  1.5G或503M" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            
                            
                            
                            
                                <div class="form-group 4">
                                <label class="col-sm-3 control-label">视频：</label>
                                <div class="col-sm-5">
                                 <h4>您所选择的文件列表：</h4>
							<div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>
								<br/>
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
                                <label class="col-sm-3 control-label">上传封面：</label>
                                 <div class="col-sm-8" style="position: relative;" id="imgs">
                                      <input type="file" id="file" name="file" style="display: none;"   class="filess" onchange="fileUpload(this);"  />
                                	<a  href="javascript:upFlile()" class="btn btn-primary " >上传</a>
                                	<input id="video_pic_url" value="" type="hidden" name="video_pic_url"/>
                                	<div id="pic1"></div>
                                  </div>
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
        //选择上传视频方法
        $(document).ready(function(){
        	$(".1").hide();
        	$(".2").hide();
        	$(".3").hide();
        });
       
     /*  $("#imgs img").click(function(){
        	$("#file0").click(){
        		
        	}
        }); */
        $("#type").change(function(){
        	if($(this).val()==0){
        		$(".1").hide();
        		$(".2").hide();
        		$(".3").hide();
        		$(".4").show();
        	}else{
        		$(".1").show();
        		$(".2").show();
        		$(".3").show();
        		$(".4").hide();
        	}
        })
        
        
      var  indexlayer;
        function fileUpload() {
			 var filepath=$("#file").val();
				if(filepath==""){
					  alert('没有添加图片无法上传');
           		return;
           	}
				   var extname = filepath.substring(filepath.lastIndexOf(".")+1,filepath.length);
				   extname = extname.toLowerCase();//处理了大小写
				    if(extname!= "png"&&extname!= "bmp"&&extname!= "jpg"&&extname!= "gif"){
				     alert("只能上传png,bmp,jpg,gif格式的图片！");
				     return ;
				    }
				    indexlayer = layer.load(1, {
				        shade: [0.1,'#fff'] //0.1透明度的白色背景
				      });
            $.ajaxFileUpload({
                url: '../alOss/aliUploader.do', 
          	  secureuri:false,  
          	  fileElementId:"file",//文件选择框的id属性  
          	  dataType: 'json',   //json  
          	  success: function (data) { 
          		layer.close(indexlayer);
                	 var divpic='<img style="max-width: 100%;margin-bottom: 10px;" src="'+ data.response+'">';
                	 $("#pic1").html(divpic);
                	 layer.close(indexlayer);
                	 layer.msg('上传成功', {icon: 1});
                	  $("input[name='video_pic_url']").val(data.response);
                },
                error: function(data, status, e){ 
               	 	 layer.close(indexlayer);
              		 layer.msg('上传失败', {icon: 1});
                }
            });
   	
	}
     	function upFlile(){
     		$("#file").click();
    	}        

     	function dosubmit(){
        	if($("input[name='title']").val()==""){
        	    alert("标题必填");
        	    return;
        	}
        	$.ajax({
    			type: "POST",
    			url: '../video/add.do',
    			data: { "tm":new Date().getTime(),
    				"title":$("input[name='title']").val(),
    				"remark":$("textarea[name='remark']").val(),
    				"course_id":"${requestScope.id}",
    				"description":$("input[name='description']").val(),
    				"video_save_url":$("input[name='fileData']").val(),
    				"video_pic_url":$("input[name='video_pic_url']").val(),
    				"per_cost":$("input[name='per_cost']").val(),//todo
    				"video_size":$("input[name='fileSize']").val(),
    				"time_long":$("input[name='fileTolTime']").val(),
    				"free":$("select[name='free']").val(),//todo
    				"freeTime":$("input[name='freeTime']").val(),
    				"canUseCoupon":$("input[name='canUseCoupon']").val(),
    				"type":$("#type").val(),
    				"video_url":$("input[name='video_url']").val(),
    				"video_tl":$("input[name='video_tl']").val(),
    				"video_sz":$("input[name='video_sz']").val(),
    				},
    			dataType:'json',
    			cache: false,
    			success: function(data){
    				layer.alert(data.message);
    				if(data.code==0){
    					$("#index").submit();
    				}
    			}
    		});
        }
     	function upisfree(){
     		var free=$("#free").val();
     		if(free=='1'){
     			$(".isfree").css("display","none");
     		}else{
     			$(".isfree").css("display","block");
     		}
     	}
    /* 	(function() {
    	    "use strict";
    	  
    	    var video, $output;
    	    var scale = 0.25;
    	  
    	    var initialize = function() {
    	    	$output = $("#output");
    	        video = $("#myVideo").get(0);
    	        $("#capture").click(captureImage);               
    	    };
    	  
    	    var captureImage = function() {
    	    	video.crossOrigin = "Anonymous";
    	        var canvas = document.createElement("canvas");
    	        canvas.getContext('2d')
    	              .drawImage(video, 0, 0, canvas.width, canvas.height);
    	        var img = document.createElement("img");
    	        img.src = canvas.toDataURL();
    	        $("#output").html(img);
    	    };
    	    $(initialize);     
    	}()); */
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