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
    <title>讲师添加</title>
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
                        <h5>讲师添加</h5>
                        <div class="ibox-tools">
                            <button onclick="JavaScript:history.back(-1);" class="btn btn-w-m btn-white btn-xs"   type="button" ><i class="fa fa-reply"></i>返回</button>
                        </div>
                    </div>
                    <div class="ibox-content">
                    	<form  class="form-horizontal m-t"   id="index" 
							method = 'post'  action = 'getTeacherList'>
							</form>
                        <form  class="form-horizontal m-t" id="addForm" 
							method = 'post'  action = '../banner/add'>
							<div class="form-group">
                                <label class="col-sm-3 control-label">讲师登陆手机号：</label>
                                <div class="col-sm-6">
                                    <input id="name" name="phone" minlength="2" value="" placeholder="请输入讲师手机号" type="text" class="form-control" required="" aria-required="true">
                                </div>
                                  <div class="col-sm-2">
                                     <a href="javascript:verPhone()" class="btn btn-primary"  >验证手机号</a>
                                </div>
                            </div>
							<div class="form-group">
                                <label class="col-sm-3 control-label">讲师登陆密码：</label>
                                <div class="col-sm-8">
                                    <input id="password" name="password" minlength="2" value="" placeholder="请输入讲师登陆密码" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">讲师名称：</label>
                                <div class="col-sm-8">
                                    <input id="username" name="username" minlength="2" value="" placeholder="请输入讲师名称" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                                 <div class="form-group">
                                <label class="col-sm-3 control-label">名称首字母：</label>
                                <div class="col-sm-8">
                                    <input id="first_word" name="first_word"   value="" placeholder="请输入首字母" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">讲师介绍：</label>
                                <div class="col-sm-8">
                                    <input id="remark" name="remark" minlength="2" value="" placeholder="请输入介绍" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">讲师提成比例：</label>
                                <div class="col-sm-8">
                                    <input id="percent" name="percent" minlength="2" value="" placeholder="请输入提成比例" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">讲师提现类型：</label>
                                <div class="col-sm-8">
                                   <select id="make_type" name="make_type">
                          				<option value="1"> 微信</option>
                          				<option value="2" >QQ</option>
                          				<option value="3"  selected>支付宝</option>
                          				<option value="4" >银行卡</option>
                          				<option value="5" >京东钱包</option>
                         		   	 </select>
                         		 </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">讲师提现账号：</label>
                                <div class="col-sm-8">
                                    <input id="make_account" name="make_account" minlength="2" value="" placeholder="请输入提现账号" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                               <div class="form-group">
                                <label class="col-sm-3 control-label">地区选择：</label>
                                <div class="col-sm-8">
                                      <input type="hidden" name="tree" value="${requestScope.tree }">
	                               <span  class="col-sm-2">
	                                    	<select id="tree" class="form-control m-b" onchange="getValue(this)" style="width: 100px;">
	                                    	<option >请选择</option>
	                                    	<option value="0">北京</option>
	                                    	</select>
	                                    	</span>
	                               	<span  class="col-sm-3" style="line-height: 32px; " id="treeName"></span>
	                                <span  class="col-sm-1"><a   href="javascript:resetValue();" class="btn btn-primary ">地址重置</a></span>
	                                
                                </div>
                            </div>
                            
                              <div class="form-group">
                                <label class="col-sm-3 control-label">等级：</label>
                                <div class="col-sm-8">
	                                    	<select id="tree" class="form-control m-b"   name="job">
	                                    	<option value="">请选择</option>
	                                    		<c:forEach items="${requestScope.job.data.list}" var="data" >
	                                    		<option value="${data.id}">${data.job_name}</option>
	                                    	</c:forEach>
	                                    	</select>
                                </div>
                            </div>
                            
                              	<div class="form-group">
                                <label class="col-sm-3 control-label">上传头像：</label>
                                 <div class="col-sm-8" style="position: relative;" id="imgs">
                                      <input type="file" id="file" name="file" style="display: none;"   class="filess" onchange="fileUpload(this);"  />
                                	<a  href="javascript:upFlile()" class="btn btn-primary " >上传</a>
                                	<input id="head_url" value="" type="hidden" name="head_url"/>
                                	<div id="pic1"></div>
                                  </div>
                            </div>
                            
                             <div class="form-group">
							       <label class="col-sm-3 control-label">宣传封面类型：</label>
							       <div class="col-sm-9">
							           <select class="form-control" name="top_type" id="top_type"  >
									        <option value= "0" >图片</option>
									        <option value= "1">视频</option>
								        </select>
							      </div>
						      </div>
						      
						       	<div class="form-group">
                                <label class="col-sm-3 control-label">讲师宣传图片：</label>
                                 <div class="col-sm-8" style="position: relative;" id="imgs">
                                      <input type="file" id="file1" name="file" style="display: none;"   class="filess" onchange="fileUpload1(this);"  />
                                	<a  href="javascript:upFlile1()" class="btn btn-primary " >上传</a>
                                	<input id="top_pic_url" value="" type="hidden" name="top_pic_url"/>
                                	<div id="pic2"></div>
                                  </div>
                            </div>
						      
						       <div class="form-group video">
                                <label class="col-sm-3 control-label">宣传视频：</label>
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
       
 $(document).ready(function(){
 	$(".fancybox").fancybox({openEffect:"none",closeEffect:"none"})
 	$(".picture").show();
 	$(".video").hide();
 
 });

/*  $("#imgs img").click(function(){
 	$("#file0").click(){
 		
 	}
 }); */
 $("#top_type").change(function(){
 	if($(this).val()==0){
 		$(".video").hide();
 		$(".picture").show();
 	}else{
 		$(".video").show();
 		$(".picture").show();
 	}
 })
 
 
 $(document).ready(function(){$(".fancybox").fancybox({openEffect:"none",closeEffect:"none"})});
     	function dosubmit(){
        	$.ajax({
    			type: "POST",
    			url: '../teacher/add.do',
    			data: { "tm":new Date().getTime(),
    				"phone":$("input[name='phone']").val(),//手机号
    				"password":$("input[name='password']").val(),
    				"username":$("input[name='username']").val(),
    				"remark":$("input[name='remark']").val(),
    				"tree":$("input[name='tree']").val(),
    				"first_word":$("input[name='first_word']").val(),
    				"head_url":$("input[name='head_url']").val(),
    				"job":$("select[name='job']").val(),
    				"percent":$("input[name='percent']").val(),
    				"make_type":$("select[name='make_type']").val(),
    				"make_account":$("input[name='make_account']").val(),
    				"top_type":$("select[name='top_type']").val(),
    				"top_pic_url":$("input[name='top_pic_url']").val(),
    				"fileData":$("input[name='fileData']").val(),
    				},
    			dataType:'json',
    			cache: false,
    			success: function(data){
    				layer.alert(data.message);

    			}
    		});
        }
     	
     	
   	 function verPhone(){
   		var phone= $("input[name='phone']").val();
				$.ajax({
					type:"POST",
					url:"${pageScope.basePath }/mei_yi_quan_admin/user/verPhone",
					async:false, 
					data:{"phone":phone},
					dataType:"json",
					success:function(data){
						console.log(data);
						layer.alert(data.message);
				}});
     }
     	
   	 
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
 			url:"../district/getDistrictList",
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
 				$("input[name='tree']").val(value);
 			},error:function (XMLHttpRequest, textStatus, errorThrown){
 					  alert(111);
 			}
 		});
     	
     }
     
     
     
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
             	  $("input[name='head_url']").val(data.response);
             },
             error: function(data, status, e){ 
            	 	 layer.close(indexlayer);
           		 layer.msg('上传失败', {icon: 1});
             }
         });
	
	}
     
     function fileUpload1() {
   
		 var filepath=$("#file1").val();
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
   	  fileElementId:"file1",//文件选择框的id属性  
   	  dataType: 'json',   //json  
   	  success: function (data) { 
   		layer.close(indexlayer);
         	 var divpic='<img style="max-width: 100%;margin-bottom: 10px;" src="'+ data.response+'">';
         	 $("#pic2").html(divpic);
         	 layer.close(indexlayer);
         	 layer.msg('上传成功', {icon: 1});
         	  $("input[name='top_pic_url']").val(data.response);
         },
         error: function(data, status, e){ 
        	 	 layer.close(indexlayer);
       		 layer.msg('上传失败', {icon: 1});
         }
     });

}
     function upFlile1(){
   		$("#file1").click();
  	}    
     
  	function upFlile(){
  		$("#file").click();
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