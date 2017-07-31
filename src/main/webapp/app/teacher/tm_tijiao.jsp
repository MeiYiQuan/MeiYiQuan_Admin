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
    <title>讲师提交表</title>
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
      <script src="${pageScope.basePath }app/js/layer/layer.js"></script>
  
    <script>
    
    // ------------------------------------------------通用部分(开始)--------------------------------------------------------
    // 初始化方法
    	function lahei(){
    		window.location.href = "${pageScope.basePath }askcourse/blackComent.do?id=${requestScope.topid}&topid=${requestScope.topid}";
    	}
    	function huifu(){
        	var zong=$("#zong").val()	;
        	var money=$("#money").val()	;
    		if(zong<money){
    			alert("提现金额不能大于剩余金额！");
    			
        		}
    		
    		else{
    			layer.confirm("是否确定提交此申请吗？",{
           		 btn: ['确定','取消']
           	},function(){
           		$("#commentForm").submit();
           	},function(){
           		
           	})
       			
           	}
    		}
            	
    	
    	
  
   function getnum(){
    	 
      	 	  $.ajax({
				url:"${pageScope.basePath }push/count.do",
				type:"POST",
				data:$("#commentForm").serialize(),
				async:false,
				dataType:"json",
				success:function (resp){
					console.log(resp)
				    $("#count").html(resp.data); 
				    
				}
			}); 
        	
    		
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
	
    <div class="wrapper wrapper-content animated fadeInRight " style="padding:0px;margin:0px">
        
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
							method = 'post'  action ='${pageScope.basePath }teachermoney/tmsend.do'>
						    
						    <input id="tid" name="tid" type="hidden" value="${requestScope.tid}">
						    
						    <div class="form-group">
                                <label class="col-sm-3 control-label">讲师名称：</label>
                                <div class="col-sm-9">
                                    <input id="name" name="name"  value="${requestScope.name}"  type="text" class="form-control" readOnly="true">
                                </div>
                            </div>
                            
                             <div class="form-group">
                                <label class="col-sm-3 control-label">现拥有总金额：</label>
                                <div class="col-sm-9">
                                    <input id="zong" name="zong"  value="${requestScope.zong}"  type="text" class="form-control" readOnly="true">
                                </div>
                            </div>
                            
                             <div class="form-group">
                                <label class="col-sm-3 control-label">提取金额：</label>
                                <div class="col-sm-9">
                                    <input id="money" name="money"  value="" placeholder="输入提现金额" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
							
							
							
                           
                         
                            
                            
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3">
                               		 <button   type="button" class="btn btn-sm btn-primary" onclick="huifu()">提交</button>
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
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>