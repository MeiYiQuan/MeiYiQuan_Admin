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
      <script src="${pageScope.basePath }app/js/layer/layer.js"></script>
  
    <script>
    
    // ------------------------------------------------通用部分(开始)--------------------------------------------------------
    // 初始化方法
   function lahei(){
    		window.location.href = "${pageScope.basePath }askcourse/blackComent.do?id=${requestScope.topid}&topid=${requestScope.topid}";
   }
    
// 初始化方法
	$(function(){
		if("${requestScope.message}".trim()!=""){alert("${requestScope.message}");}
	});
    
    
   function huifu(){
		 var title =document.getElementById('title').value; 
		/* if(layer.confirm("确定要fasong名为  title 的banner吗？")){
  		btn: ['确定','取消'] //按钮
	  		
	  	} */
	  	layer.confirm("是否确定发送此推送吗？",{
	  		 btn: ['确定','取消']
	  	},function(){
	  		$("#commentForm").submit();
	  	},function(){
	  		
	  	})
			
	}
   
   
   function select(){
	   var str = JSON.stringify($("#commentForm").serialize());
 	   var strs = str.substring(1,str.length - 1);
	 	  $.ajax({
				url:"${pageScope.basePath }coupon/select.do",
				type:"POST",
				data:strs,
				async:false,
				dataType:"json",
				success:function (resp){
					layer.alert("符合条件的共计" + resp.data + "人");
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
                        <h5>推送</h5>
                        <div class="ibox-tools">
                            <button id="fanhui" onclick="JavaScript:history.back(-1);" type="button" class="btn btn-w-m btn-white btn-xs"><i class="fa fa-reply"></i>返回</button>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <form enctype="multipart/form-data" class="form-horizontal m-t" id="commentForm" 
							method = 'post'  action ='${pageScope.basePath }push/send.do'>
							
							<div class="form-group">
								       <label class="col-sm-3 control-label">选择发送人群：</label>
								       <div class="col-sm-9">
								           <select class="form-control" id="type" name="type" >
								           		<option value="">请选择类别</option>
				                              	<option value="0">学员</option>
				                              	<option value="1">讲师</option>
				                              	<!-- <option value="2">区域合作伙伴</option> -->
									       </select>
								       </div>
						    </div>
							
							
							<div class="form-group">
								       <label class="col-sm-3 control-label">选择职业：</label>
								       <div class="col-sm-9">
								           <select class="form-control" id="jobid" name="jobid"  >
								           		<option value="">请选择职业</option>
				                            	<c:forEach items="${requestScope.jobCode.data.list}" var="cate">
				                              	<option value="${cate.id}">${cate.job_name}</option>
				                            	</c:forEach>
									       </select>
								       </div>
						    </div>
							
							
							
                           <div class="form-group">
								       <label class="col-sm-3 control-label">选择年龄段：</label>
								       <div class="col-sm-9">
								           <select class="form-control" id="ageid" name="ageid" >
								           		<option value="">请选择年龄段</option>
				                            	<c:forEach items="${requestScope.ageCode.data.list}" var="cate">
				                              	<option value="${cate.id}">${cate.job_name}</option>
				                            	</c:forEach>
									       </select>
								       </div>
						    </div>
						    
						    <div class="form-group">
								       <label class="col-sm-3 control-label">选择性别：</label>
								       <div class="col-sm-9">
								           <select class="form-control" id="sexid" name="sexid"   >
								           		<option value="">请选择性别</option>
				                              	<option value="1">女</option>
				                              	<option value="2">男</option>
									       </select>
								       </div>
						    </div>
						    
						    <div class="form-group">
								       <label class="col-sm-3 control-label">选择星座：</label>
								       <div class="col-sm-9">
								           <select class="form-control" id="zodicid" name="zodicid" >
								           		<option value="">请选择星座</option>
				                              	<option value="1">摩羯座</option>
				                              	<option value="2">水瓶座</option>
				                              	<option value="3">双鱼座</option>
				                              	<option value="4">白羊座</option>
				                              	<option value="5">金牛座</option>
				                              	<option value="6">双子座</option>
				                              	<option value="7">巨蟹座</option>
				                              	<option value="8">狮子座</option>
				                              	<option value="9">处女座</option>
				                              	<option value="10">天平座</option>
				                              	<option value="11">天蝎座</option>
				                              	<option value="12">射手座</option>
									       </select>
								       </div>
						    </div>
						    
						    
						     <div class="form-group">
								       <label class="col-sm-3 control-label">选择地区：</label>
								       <div class="col-sm-9">
								        	<input type="hidden" name="tree" value="${requestScope.tree }">
								        	<select id="tree" class="form-control m-b" onchange="getValue(this)"  >
		                                    	<option value="">请选择地区</option>
	                                    	</select>
									        
									        
									        <span  class="col-sm-3" style="line-height: 32px; " id="treeName"></span>
	                                		<span  class="col-sm-1"><a   href="javascript:resetValue();" class="btn btn-primary ">地址重置</a></span>
								       </div>
						    </div>
						    
                             <div class="form-group">
                                <label class="col-sm-3 control-label">推送标题：</label>
                                <div class="col-sm-9">
                                    <input id="title" name="title" minlength="2" value="" placeholder="推送标题" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            
                          
                            <div class="form-group">
                                <label class="col-sm-3 control-label">推送内容：</label>
                                <div class="col-sm-9">
                                    <textarea id="content" name="content" placeholder="请输入推送内容" class="form-control"></textarea>
                                    </textare>
                                </div>
                            </div>
                            
                           
                         
                            
                            
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3">
                                	 <input type="hidden" value="" placeholder="用户电话"  name="phone" value=""  class="form-control" />
                                	 <button type="button" class="btn btn-sm btn-primary" onclick="select()">查询人数</button>
                               		 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                               		 <button   type="button" class="btn btn-sm btn-primary" onclick="huifu()">发送</button>
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
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>地址查询开始>>>>>>>>>>>>>>>>>>.
    $(function(){
    	doInit("0");
    })
    function getValue(e){
    	doInit($(e).val());
    }
    function resetValue(e){
    	doInit("0");
    	$("#treeName").html("");
    }
    function doInit(pid){
  	  $.ajax({
			url:"${pageScope.basePath }district/getDistrictList",
			type:"POST",
			data:{"pid":pid},
			dataType:"json",
			success:function (data){
				var dataSelect='<option value="">请选择</option>';
				for(var i=0;i<data.data.data.length;i++){
					dataSelect+='<option value="'+data.data.data[i].id+'">'+data.data.data[i].name+'</option>'
				}
				$("#tree").html(dataSelect);
				var value=$("#treeName").html();
				if(value==""){
					value=data.data.clickName;
					
				}else{
					value+="-"+data.data.clickName;
				}
				$("#treeName").html(value);
				$("#dataForm input[name='tree']").val(value);
			},error:function (XMLHttpRequest, textStatus, errorThrown){
					  alert(111);
			}
		});
    	
    }
       //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>地址查询结束>>>>>>>>>>>>>>>>>>.  
    </script>  </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>