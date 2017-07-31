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
    <link href="${pageScope.basePath }/app/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="${pageScope.basePath }/app/css/animate.min.css" rel="stylesheet">
    <link href="${pageScope.basePath }/app/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageScope.basePath }/app/js/My97DatePicker/skin/default/datepicker.css" rel="stylesheet">
    <link href="${pageScope.basePath }/app/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
	<script src="${pageScope.basePath }/app/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageScope.basePath }/app/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script type="text/javascript" src="${pageScope.basePath }/app/js/My97DatePicker/WdatePicker.js"></script>
  
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
    <link href="${pageContext.request.contextPath}/app/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/app/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/app/css/animate.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/app/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/app/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/app/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
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
	${pageScope.basePath }
    <div class="wrapper wrapper-content animated fadeInRight ">
        
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>新增线下活动</h5>
                        <div class="ibox-tools">
                            <button onclick="JavaScript:history.back(-1);" type="button" class="btn btn-w-m btn-white btn-xs"><i class="fa fa-reply"></i>返回</button>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <c:forEach items="${requestScope.code.data.list }" var="data">
                        <form  class="form-horizontal m-t" id="commentForm" 
							method = 'post'  action = '${pageScope.basePath }/active/saveActive.do'>
                             <input id="id" name="aid" type="hidden" value="${requestScope.id }"> 
                            <div class="form-group">
                                <label class="col-sm-3 control-label">嘉宾id：</label>
                                <div class="col-sm-8">
                                    <input id="uid" name="uid"  value="${data.user_id }" placeholder="请输入嘉宾id" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label">嘉宾出场费：</label>
                                <div class="col-sm-8">
                                    <input id="appearance" name="appearance"  value="${data.appearance }" placeholder="请输入嘉宾出场费" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            
                            
                            
                            
                        </form>
                        </c:forEach>
                    </div>
                </div>
               
            </div>
            
        </div>
    </div>
    <script src="${pageScope.basePath }/app/js/jquery.min.js?v=2.1.4"></script>
    <script src="${pageScope.basePath }/app/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageScope.basePath }/app/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${pageScope.basePath }/app/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${pageScope.basePath }/app/js/demo/form-validate-demo.min.js"></script>
    <script src="${pageScope.basePath }/app/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="${pageScope.basePath }/app/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script src="${pageScope.basePath }/app/js/layer/layer.js"></script>
    
    <script>
        $(document).ready(function(){$(".fancybox").fancybox({openEffect:"none",closeEffect:"none"})});
       
     /*  $("#imgs img").click(function(){
        	$("#file0").click(){
        		
        	}
        }); */

    	// 点击删除
    	function doSave(){
    		if(layer.confirm("删除会删除所有子集及所有有关的关联关系？",{
        		btn: ['确定','取消'] //按钮
        	}, function(){
      		  $.ajax({
				url:"${pageScope.basePath }/active/saveActive.do",
				type:"GET",
				data:$("#commentForm").serialize(),
				async:false,
				dataType:"json",
				success:function (resp){
					alert(resp.code)
					if(resp.code==1){
						$("#dataForm").attr("action","${pageScope.basePath }/active/activelist.do");
						//$("#dataForm").submit();	
					}else{
						alert(resp.message)
					}
					//$("#dataForm").submit();
				},error:function (XMLHttpRequest, textStatus, errorThrown){
					alert(11111);
					  //$("#dataForm").submit();
				}
			});
        	}
      		  ))
    		return false;
    	}
        
        
        
        
        
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
        $("#show_pic_url").attr("value",url);
        } 
    	
        
    </script>  </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>