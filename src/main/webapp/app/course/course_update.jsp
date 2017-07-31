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
    <title>课程修改</title>
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
                        <h5>课程修改</h5>
                        <div class="ibox-tools">
                            <button onclick="JavaScript:history.back(-1);" class="btn btn-primary " type="button" ><i class="fa fa-reply"></i>返回</button>
                        </div>
                    </div>
                    <div class="ibox-content">
                    	<form  class="form-horizontal m-t"   id="index" 
							method = 'post'  action = '../course/getCourseList'>
							</form>
                        <form  class="form-horizontal m-t" id="addForm" 
							method = 'post' >
                            <div class="form-group">
                                <label class="col-sm-3 control-label">名称：</label>
                                <div class="col-sm-8">
                                    <input  name="name" minlength="2" value="${requestScope.data.data.title }" placeholder="请输入名称" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                             <%-- <div class="form-group">
                                <label class="col-sm-3 control-label">	选择频道：</label>
                            	<span class="col-sm-8" style="margin-top: 10px;">
                            	<input type="hidden" name="channel_id" id="channel_id">
                         	<select id="firstCate" name="firstCate" class="form-control m-b"  onchange="channellian(this,'#secondCate')">
                              	<option value="">请选择类别</option>
                              	 <c:forEach items="${requestScope.list }" var="data">
                              	 	<option value="${data.id }">${data.name }</option>
                              	 </c:forEach>
                            </select>
                            <select id="secondCate" name="secondCate"   class="form-control m-b"  onchange="channellian(this,'#thirdCate')">
                            		<option value="${requestScope.channel2.id }">${requestScope.channel2.name }</option>
                              </select>
                            <select id="thirdCate"  class="form-control m-b"  name="thirdCate"  onchange="addthirdCate(this,'#thirdCate')">
                            		<option value="${requestScope.channel3.id }">${requestScope.channel3.name }</option>
                             </select>
                            </span>
                             </div> --%>
                             <div class="form-group">
                                <label class="col-sm-3 control-label">	填写讲师：</label>
                            	<span class="col-sm-8" style="margin-top: 10px;">
                              	<input  id="teacher_id" name="teacher_id" minlength="2" value="${requestScope.data.data.teacher_id}" placeholder="请输入老师ID" type="text" class="form-control" required="" aria-required="true">
                            </span>
                             </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">一句话描述：</label>
                                <div class="col-sm-8">
                                    <input name="description" minlength="2" value="${requestScope.data.data.description }" placeholder="请输入描述" type="text" class="form-control" required="" aria-required="true">
                                </div>
                            </div>
                              <div class="form-group">
                                <label class="col-sm-3 control-label">完整描述：</label>
                                <div class="col-sm-8">
                                <textarea name="remark" style="width: 1047px;" minlength="2"   rows="" cols="" required="" aria-required="true">${requestScope.data.data.remark }</textarea>
                                </div>
                            </div>
                              <div class="form-group">
                                <label class="col-sm-3 control-label">封面类型(不选择默认为图片)：</label>
                                <div class="col-sm-8">
                                		<select class="form-control m-b" name="course_compaign_type" id="course_compaign_type" onchange="pic_type();" >
                                			<option value="0">图片</option>
                                			<option value="1">视频</option>
                                				<!-- <option value="3">动图</option> -->
                                		</select>
                                </div>
                            </div>
                                 <div class="form-group"  id="videoData" >
                                <label class="col-sm-3 control-label">视频：</label>
                                <div class="col-sm-5">
                                 <h4>您所选择的文件列表：</h4>
								<div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>
								<br/>
                                  <input type="radio" name="myradio" style="display: none;" value="random_name" checked=true />
                                  <input type="hidden" id="fileData" name="fileData"  value="${requestScope.data.data.course_compaign_video_url }"   />
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
		                        				src="${requestScope.data.data.course_compaign_video_url }">
		                        			</video> 
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
                                	<input id="pic_big_url" value="${requestScope.data.data.pic_big_url }" type="hidden" name="pic_big_url"/>
                                	<div id="pic1">
                                	<img style="max-width: 100%;margin-bottom: 10px;" src="${requestScope.data.data.pic_big_url } ">
                                	</div>
                                  </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-4 col-sm-offset-3">
                                    <a href="javascript:toadd()" class="btn btn-primary"  >提交</a>
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
                	  $("input[name='pic_big_url']").val(data.response);
                },
                error: function(data, status, e){ 
               	 	 layer.close(indexlayer);
              		 layer.msg('上传失败', {icon: 1});
                }
            });
   	
	}
        
        
        function pic_type(){
			var course_compaign_type=	$("#course_compaign_type").val();
			if(course_compaign_type==0){
				$("#videoData").css("display","none");
			}else{
				$("#videoData").css("display","block");
			}
		
	}
        
     	function upFlile(){
     		$("#file").click();
    	}        

        
     // 修改
    	function toadd(){
    			  $.ajax({
    					url:"../course/update.do",
    					type:"POST",
    					data:{
    						"id":"${requestScope.data.data.id }",
    						"title":$("input[name='name']").val(),
    						"description":$("input[name='description']").val(),
    						"remark":$("textarea[name='remark']").val(),
    						"fileData":$("input[name='fileData']").val(),
    						"course_compaign_type":$("input[name='course_compaign_type']").val(),
    						"course_compaign_video_url":$("#fileData").val(),
    						"teacher_id":$("input[name='teacher_id']").val(),
    						"pic_big_url":$("input[name='pic_big_url']").val(),
    						"course_compaign_type":$("#course_compaign_type").val()
    					},
    					dataType:"json",
    					success:function (data){
    						layer.alert(data.message);
    	    				if(data.code==0){
    	    					$("#index").submit();
    	    				}
    					},error:function (XMLHttpRequest, textStatus, errorThrown){
    						$("#index").submit();
    					}
    				});
    	}
      /*   $(function(){
        	channellian($("#firstCate"),"#firstCate")
        }) */
        
    	 //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>联动查询>>>>>>>>>>>>>>>>>>>>>>
        
        function	 addthirdCate(){
        	$("input[name='channel_id']").val($("#thirdCate").val());
        }
    	 
    	 function channellian(d,e){
        	var dataId="0";
        	if($(d).val()!=""){
        		dataId=$(d).val();
        	}
        	$("#thirdCate").html('<option value="">请选择类别</option>');
   				$.ajax({
   					type:"POST",
   					url:"${pageScope.basePath }/mei_yi_quan_admin/channel/channellian.do",
   					async:false, 
   					data:{"upId":dataId},
   					dataType:"json",
   					success:function(data){
   						if(data!=null){
   							var div="<option value=''>请选择类别</option>";
   							for(var i=0;i<data.data.data.length;i++){
   								div+="<option value='"+data.data.data[i].id+"'>"+data.data.data[i].na+"</option>";
   							}
   							$(e).html(div);
   						}
   					}
   					
   				});
        }
    	 
    	 
    	  $("#firstCate").val("${requestScope.channel1.id }");
    	  $("#course_compaign_type").val("${requestScope.data.data.course_compaign_type}");
    	  $(function(){
    		  if("${requestScope.data.data.course_compaign_type}"=="0"){
    			  $("#videoData").css("display","none");
    		  }
    	  })
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