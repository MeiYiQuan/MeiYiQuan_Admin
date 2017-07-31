<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="qc" uri="http://qc/qcTag" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	pageContext.setAttribute("basePath", basePath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>推荐添加</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="${pageScope.basePath }app/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="${pageScope.basePath }app/css/animate.min.css" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/style.min862f.css?v=4.1.0" rel="stylesheet">
	<script src="${pageScope.basePath }app/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageScope.basePath }app/js/layer/layer.js"></script>
	<script type="text/javascript">
		
		var imgUrl = null;
		
		$(function(){
			if("${requestScope.message}".trim()!=""){alert("${requestScope.message}");}
		});
	
		function change(){
			var fdata = new FormData($("#upPic")[0]);
			$.ajax({
				url:"coupon/upImage.do",
				type:"POST",
				data:fdata,
				async:false,
				cache:false,
				contentType:false,
				processData: false,
				success:function (resp){
					if(resp.code==0){
						// 成功
						imgUrl = resp.data;
						$("#imgid").attr("src",imgUrl);
						$("#imginid").attr("value",imgUrl);
					}else{
						// 失败
						alert("图片上传失败！请稍后再试！");
					}
				}
			});
		}
	
	</script>
</head>

<body class="gray-bg" style="padding:0px;margin:0px">
    <div class="row">
        <div >
            <div class="wrapper wrapper-content animated fadeInUp" style="padding:0px;margin:0px">
                <div class="ibox" style="padding-left:20px;margin:0px">
                    <div class="ibox-content">
                    	<form id="upPic" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="m-b-md">
                                    <h2>推荐添加</h2>
                                </div>
                                <dl class="dl-horizontal">
                                    <dt>上传图片：</dt>
                                    <dd><input type="file" name="file" value="" onchange="change()"/></dd>
                                </dl>
                                
                            </div>
                        </div>
                        </form>
                        <form action="homepage/add.do" method="post">
                        <div class="row">
                            <div class="col-sm-5">
                                <dl class="dl-horizontal">
                                	<%-- <dt>首页名称：</dt>
                                    <dd><input type="text" name="name" value="${requestScope.object.name }"/></dd>
                                    <dt></dt>
                                    <dd>
	                                   <br/>
                                    </dd> --%>
                                	<dt>类型：</dt>
                                    <dd>
                                    	<input type="radio" name="type" value="1" <c:if test="${requestScope.object.type eq 1 or requestScope.object.type eq null }">checked</c:if>>课程
                                    	&nbsp;&nbsp;&nbsp;
                                    	<input type="radio" name="type" value="2" <c:if test="${requestScope.object.type eq 2 }">checked</c:if>>名人大佬
                                    	&nbsp;&nbsp;&nbsp;
                                    	<input type="radio" name="type" value="3" <c:if test="${requestScope.object.type eq 3 }">checked</c:if>>开店创业
                                    	&nbsp;&nbsp;&nbsp;
                                    	<input type="radio" name="type" value="4" <c:if test="${requestScope.object.type eq 4 }">checked</c:if>>潮流技术
                                    </dd>
                                    <dt></dt>
                                    <dd>
	                                   <br/>
                                    </dd>
                                	<dt>关联ID：</dt>
                                    <dd><input type="text" name="relation_id" value="${requestScope.object.relation_id }"/></dd>
                                    <dt></dt>
                                    <dd>
	                                   <br/>
                                    </dd>
                                    <dt>是否置顶：</dt>
                                    <dd>
		                               <div class="col-sm-8">
		                                     <div class="radio checkbox-inline">
		                                         <input type="radio" name="isTop" value="1" <c:if test="${requestScope.object.isTop eq 1 }">checked</c:if> >
		                                         <label for="radio1">置顶</label>
		                                    </div>
		                                    <div class="radio checkbox-inline">
		                                         <input type="radio" name="isTop" value="2" <c:if test="${requestScope.object.isTop eq 2 }">checked</c:if> >
		                                         <label for="radio1">不置顶</label>
		                                    </div>
		                                </div>
                                    </dd>
                                    
                                </dl>
                            </div>
                            <div class="col-sm-7">
                                <img id="imgid" src="${requestScope.object.pic_url }" style="width:400px;height:150px" />
                                <input id="imginid" type="hidden" name="pic_url" value="${requestScope.object.pic_url }"/>
                                <input type="hidden" name="id" value="${requestScope.object.id }"/>
                            </div>
                        </div>
                        <div class="row">
                        	<br/>
                        	<div align="center">
                        		<span >
	                                  <button type="submit" class="btn btn-sm btn-primary" >提交</button> 
	                                  <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
	                                  <button type="button" class="btn btn-sm btn-primary" onclick="javascript:history.go(-1);">返回</button> 
	                            </span>
                        	</div>
                        </div>
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="${pageScope.basePath }app/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageScope.basePath }app/js/content.min.js?v=1.0.0"></script>
    <script>
        $(document).ready(function(){$("#loading-example-btn").click(function(){btn=$(this);simpleLoad(btn,true);simpleLoad(btn,false)})});function simpleLoad(btn,state){if(state){btn.children().addClass("fa-spin");btn.contents().last().replaceWith(" Loading")}else{setTimeout(function(){btn.children().removeClass("fa-spin");btn.contents().last().replaceWith(" Refresh")},2000)}};
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>

</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/project_detail.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>
