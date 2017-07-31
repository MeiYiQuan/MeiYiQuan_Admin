<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="qc" uri="http://qc/qcTag" %>
 <%@ page import="java.util.*"%>    
<%@ page import="java.text.*"%>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %> 
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
    <script type="text/javascript" src="${pageScope.basePath }app/js/My97DatePicker/WdatePicker.js"></script>
  	 
    <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${pageScope.basePath }app/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="${pageScope.basePath }app/oss/style.css"/>
     <link rel="shortcut icon" href="favicon.ico"> 
    <link href="${pageScope.basePath }app/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/animate.min.css" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
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
                        <h5>新增即将上映课程</h5>
                        <div class="ibox-tools">
                            <button onclick="JavaScript:history.back(-1);" class="btn btn-primary " type="button" ><i class="fa fa-reply"></i>返回</button>
                        </div>
                    </div>
                    <div class="ibox-content">
                    <c:forEach items="${requestScope.code.data.list }" var="data">
                        <form enctype="multipart/form-data" class="form-horizontal m-t" id="commentForm" 
							method = 'post'  action = '${pageScope.basePath }sooncourse/upoateSoon.do'>
							  <input id="id" name="id"  value="${data.id }" type="hidden" >
                            <div class="form-group">
                                <label class="col-sm-3 control-label">即将上映名称：</label>
                                <div class="col-sm-8">
                                    <input id="title" name="title"  value="${data.title }" placeholder="请输入名称" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">即将上映时间：</label>
                                <div class="col-sm-8">
                                    	
                            		<input id="playing_time" name="playing_time" class="Wdate" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${data.playing_time} "  style="width:100px;"/>
                            		
                            		<%--  <jsp:useBean id="dtCreat" class="java.util.Date"></jsp:useBean>
                                    	<jsp:setProperty property="time" name="dtCreat" value="${data.playing_time }"/>
                                    	<fmt:formatDate value="${dtCreat }" pattern="yyyy-MM-dd"/> --%>
                             	</div>
                            </div>
                            
                             <div class="form-group">
                                <label class="col-sm-3 control-label">视频详情介绍：</label>
                                <div class="col-sm-8">
                                <textarea id="descriptionn" name="descriptionn" style="width: 500px;" value="${data.remark }">${data.remark }</textarea>
                                </div>
                            </div>
                             
                              <div class="form-group">
							       <label class="col-sm-3 control-label">宣传封面类型：</label>
							       <div class="col-sm-9">
							         <c:if test="${data.course_compaign_type eq '0' }">
							           <select class="form-control" name="course_compaign_type" id="course_compaign_type"  >
									        <option value= "0" selected="selected" >图片</option>
									        <option value= "1">视频</option>
								        </select>
								       </c:if> 
								       
								       <c:if test="${data.course_compaign_type eq '1' }">
							           <select class="form-control" name="course_compaign_type" id="course_compaign_type"  >
									        <option value= "0" >图片</option>
									        <option value= "1" selected="selected">视频</option>
								        </select>
								       </c:if> 
							      </div>
						      </div>
                             
                             <div class="form-group">
                                <label class="col-sm-3 control-label">课程宣传小图：</label>
                                <div class="col-sm-8" style="position: relative;" id="imgs">
                                <input type="file" id="file0" name="file0"  class="filess" onchange="preImg(this.id,'imgShow_WU_FILE_0');"  />
                                    <img style="width:200px; heihgt:150px;" id="imgShow_WU_FILE_0"   class="feed-photo" src="${data.pic_small_url }">
                                	<input id="pic_small_url" value="${data.pic_small_url }" type="hidden" name="pic_small_url"/>
                                </div>
                            </div> 
                            
                               <%-- <div class="form-group">
                                <label class="col-sm-3 control-label">课程宣传大图：</label>
                                <div class="col-sm-8" style="position: relative;" id="imgs">
                                <input type="file" id="file1" name="file1"  class="filess" onchange="preImg1(this.id,'imgShow_WU_FILE_1');"  />
                                    <img style="width:200px; heihgt:150px;" id="imgShow_WU_FILE_1"   class="feed-photo" src="${data.pic_big_url }">
                                	<input id="pic_big_url" value="${data.pic_big_url }" type="hidden" name="pic_big_url"/>
                                </div>
                            </div> --%>
                            
                            
                             <div class="form-group video">
                                <label class="col-sm-3 control-label">封面视频：</label>
                                <div class="col-sm-5">
                                 <h4>您所选择的文件列表：</h4>
							<div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>
								<br/>
                                  <input type="radio" name="myradio" style="display: none;" value="random_name" checked=true />
                                  <input type="hidden" id="fileData" name="fileData"  value="${data.course_compaign_video_url}"   />
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
											  <video id="myVideo" controls preload="auto"  
		                        				src="${data.course_compaign_video_url}">
		                        			</video> 
									 </div>
                                   <!-- <pre id="console"></pre> -->
									<p>&nbsp;</p>
									
									</div>
									<!-- <button id="capture">Capture</button>
   									<div id="output"></div> -->
									</div>
                            
                            
                            
                            
                           <%--  <div class="form-group">
                                <label class="col-sm-3 control-label">关联频道id：</label>
                                <div class="col-sm-8">
                                    <input id="channel_id" name="channel_id"  value="${data.channel_id} " placeholder="请输入关联频道id" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div> --%>
                            
                             <div class="form-group">
                                <label class="col-sm-3 control-label">关联讲师id：</label>
                                <div class="col-sm-8">
                                    <input id="teacher_id" name="teacher_id"   value="${data.teacher_id}" placeholder="请输入关联讲师id" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">课程成本：</label>
                                <div class="col-sm-8">
                                    <input id="cost" name="cost"   value="${data.cost}"  placeholder="课程成本" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">提交</button>
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
    <script src="${pageScope.basePath }app/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script>
        $(document).ready(function(){
        	$(".fancybox").fancybox({openEffect:"none",closeEffect:"none"})
        	$(".picture").hide();
        	$(".video").hide();
        	alert($("#course_compaign_type").val());
        	if($("#course_compaign_type").val()==0){
        		
        		$(".video").hide();
        		$(".picture").show();
        	}else{
        		$(".video").show();
        		$(".picture").show();
        	}
        });
       
     /*  $("#imgs img").click(function(){
        	$("#file0").click(){
        		
        	}
        }); */
        $("#course_compaign_type").change(function(){
        	if($(this).val()==0){
        		$(".video").hide();
        		$(".picture").show();
        	}else{
        		$(".video").show();
        		$(".picture").show();
        	}
        })
       
       
        
        var ue = UE.getEditor('editor');
        var s=ue.getContent();
  	 	var a=document.getElementById("details").value=UE.getEditor('editor').getContent();
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
        $("#pic_small_url").attr("value",url);
        } 
        
        function preImg1(sourceId, targetId) { 
        var url = getFileUrl(sourceId); 
        var imgPre = document.getElementById(targetId); 
        imgPre.src = url;
        $("#pic_big_url").attr("value",url);
        } 
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>联动查询>>>>>>>>>>>>>>>>>>>>>>
     
        //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>联动查询结束>>>>>>>>>>>>>>>>>>>>>>  
        
    </script>  
    <script type="text/javascript" src="${pageScope.basePath }app/oss/lib/crypto1/crypto/crypto.js"></script>
	<script type="text/javascript" src="${pageScope.basePath }app/oss/lib/crypto1/hmac/hmac.js"></script>
	<script type="text/javascript" src="${pageScope.basePath }app/oss/lib/crypto1/sha1/sha1.js"></script>
	<script type="text/javascript" src="${pageScope.basePath }app/oss/lib/base64.js"></script>
	<script type="text/javascript" src="${pageScope.basePath }app/oss/lib/plupload-2.1.2/js/plupload.full.min.js"></script>
	<script type="text/javascript" src="${pageScope.basePath }app/oss/upload.js"></script>
</body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>