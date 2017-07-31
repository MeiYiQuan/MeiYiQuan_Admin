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
    <script type="text/javascript" src="${pageScope.basePath }app/js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/app/UEditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/app/UEditor/ueditor.all.min.js"> </script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/app/UEditor/lang/zh-cn/zh-cn.js"></script>
  
    <script>
    
    // ------------------------------------------------通用部分(开始)--------------------------------------------------------
    // 初始化方法
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
      height:80px;
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
                        <h5>参数详情</h5>
                        <div class="ibox-tools">
                            <button id="fanhui" onclick="JavaScript:history.back(-1);" type="button" class="btn btn-w-m btn-white btn-xs"><i class="fa fa-reply"></i>返回</button>
                        </div>
                    </div>
                    <div class="ibox-content">
                   
                        <form enctype="multipart/form-data" class="form-horizontal m-t" id="commentForm" 
							method = 'post'  action = '${pageScope.basePath }channel/updateChannel.do'>
                           <input id="id" name="id" type="hidden" value="${id }" >
                           
                             <div class="form-group">
                                <label class="col-sm-3 control-label">频道名称：</label>
                                <div class="col-sm-8">
                                    <input id="name" name="name" minlength="2" value="${name }" placeholder="频道名称" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            
                          
                              <div class="form-group">
                                <label class="col-sm-3 control-label">图标：</label>
                                <div class="col-sm-8" style="position: relative;" id="imgs">
                                <input type="file" id="file0" name="file0"  class="filess" onchange="preImg(this.id,'imgShow_WU_FILE_0');"  />
                                   <c:if test="${logo_url==null }"> <img style="width:200px; heihgt:150px;" id="imgShow_WU_FILE_0"   class="feed-photo" src="${pageScope.basePath }app/img/head_add.png"></c:if>
                                   <c:if test="${logo_url!=null }"> <img style="width:200px; heihgt:150px;" id="imgShow_WU_FILE_0"   class="feed-photo" src="${logo_url }"></c:if>
                                	<input id="logo_url" value="${logo_url }" type="hidden" name="logo_url"/>
                                </div>
                            </div>
							
							
							
							
							
							<div class="form-group">
                                <label class="col-sm-3 control-label">父频道id：</label>
                                <div class="col-sm-8">
                                    <input id="pid" name="pid"  value="${pid }" placeholder="输入频道id" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            
                         
                           
                            
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3">
                               		 <button type="submit" class="btn btn-sm btn-primary">保存</button>
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
    $("#logo_url").attr("value",url);
    } 
	
	 //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>联动查询>>>>>>>>>>>>>>>>>>>>>>
        function getSecondCate(){
   			var select1=$("#firstCate option:selected").val();
   			$("#secondCate").empty().append("<option value='na'>请选择类别</option>");
   			if(select1=="na"){
   				$("#thirdCate").empty().append("<option value='na'>请选择类别</option>");
   			}else{
   				$.ajax({
   					type:"POST",
   					url:"${pageScope.basePath }/channel/channellian.do",
   					async:false, 
   					data:{"type":2,"upId":select1},
   					dataType:"json",
   					success:function(data){
   						if(data!=null){
   							for(var i=0;i<data.data.data.length;i++){
   								$("#secondCate").append("<option value='"+data.data.data[i].id+"'>"+data.data.data[i].na+"</option>");
   							}
   						}
   					}
   					
   				});
   			}
   		}
        function getThirdCate(){
       		var select2=$("#secondCate option:selected").val();
       		$("#thirdCate").empty().append("<option value='na'>请选择类别</option>");
       		if(select2!="na"){
       			$.ajax({
       				type:"POST",
       				url:"${pageScope.basePath }/channel/channellian.do",
       				async:false, 
       				data:{"type":3,"upId":select2},
       				dataType:"json",
       				success:function(data){
       					if(data!=null){
       						for(var i=0;i<data.data.data.length;i++){
       							$("#thirdCate").append("<option value='"+data.data.data[i].id+"'>"+data.data.data[i].na+"</option>");
       						}
       					}
       				}
       				
       			});
       		}
       	}
      //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>联动查询结束>>>>>>>>>>>>>>>>>>>>>>
    
</script>  
     </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>