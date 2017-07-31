<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="qc" uri="http://qc/qcTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/" ;
String conditions = 
			"orderNum=" + (request.getAttribute("orderNum")==null?"":request.getAttribute("orderNum").toString()) + 
			"&createTime=" + (request.getAttribute("createTime")==null?"":request.getAttribute("createTime").toString()) + 
			"&updateTime=" + (request.getAttribute("updateTime")==null?"":request.getAttribute("updateTime").toString()) + 
			"&bannerType=" + (request.getAttribute("bannerType")==null?"":request.getAttribute("bannerType").toString()) + 
			"&status=" + (request.getAttribute("status")==null?"":request.getAttribute("status").toString()) + 
			"&createBegin=" + (request.getAttribute("createBegin")==null?"":request.getAttribute("createBegin").toString()) + 
			"&createEnd=" + (request.getAttribute("createEnd")==null?"":request.getAttribute("createEnd").toString()) + 
			"&updateBegin=" + (request.getAttribute("updateBegin")==null?"":request.getAttribute("updateBegin").toString()) + 
			"&updateEnd=" + (request.getAttribute("updateEnd")==null?"":request.getAttribute("updateEnd").toString());
pageContext.setAttribute("basePath", basePath);
pageContext.setAttribute("conditions", conditions);
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>名人大佬推荐列表</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="${pageScope.basePath }app/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">

    <link href="${pageScope.basePath }app/css/animate.min.css" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageScope.basePath }app/js/My97DatePicker/skin/default/datepicker.css" rel="stylesheet">
    <link href="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
     <link rel="stylesheet" type="text/css" href="${pageScope.basePath }app/css/paging.css">
	<script src="${pageScope.basePath }app/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script type="text/javascript" src="${pageScope.basePath }app/js/My97DatePicker/WdatePicker.js"></script>
    <script src="${pageScope.basePath }app/js/layer/layer.js"></script>
    <script>
    
    // ------------------------------------------------通用部分(开始)--------------------------------------------------------
    
    
    	
    
    	
    	
    	
    	// 刷新
    	function   findall(){
    	$("#dataForm").submit();
    }
    	//添加
    	function toadd(){
    		window.location.href = "${pageScope.basePath }rdcelebrity/tosave.do";
    	}
    	
    	// 点击删除
    	function isDelete(name,id){
    		if(layer.confirm("确定要删除名为 " + name + " 的banner吗？",{
        		btn: ['确定','取消'] //按钮
        	}, function(){
      		  layer.msg('的确很重要', {icon: 1});
      		
      		  $.ajax({
				url:"${pageScope.basePath }banner/deleteBanner.do",
				type:"POST",
				data:{id:id},
				async:false,
				dataType:"json",
				success:function (resp){
					layer.alert(resp.message);
					if(resp.code==1){
						// 成功
						$("#tr" + id)[0].style.display = "none";
					}else{
						// 失败
						
					}
				}
			});
        	}
      		  ))
    		return false;
    	}
    	
    	
    // ------------------------------------------------通用部分(结束)--------------------------------------------------------
    
    
    
    // ------------------------------------------------AJAX(开始)--------------------------------------------------------
    
    // 对banner的启用和禁用
    function upStatus(id,name){
    	var nowStatus = $("#status" + id).text().trim();
    	var confStr = nowStatus=="启用"?"禁用":"启用";
    	var n = nowStatus=="启用"?2:1;
    	if(layer.confirm("确定要" + confStr + "名为" + name + "的讲师至首页吗？",{
    		btn: ['确定','取消'] //按钮
    	}, function(){
  		  layer.msg('的确很重要', {icon: 1});
  		
  		  $.ajax({
			url:"${pageScope.basePath }rdcelebrity/upStatus.do",
			type:"POST",
			data:{id:id,status:n},
			async:false,
			dataType:"json",
			success:function (resp){
				layer.alert(resp.message);
				if(resp.code==1){
					// 成功
					//$("#upTime"+id).html(resp.data);
					$("#status"+id).html(confStr);
					$("#ahref"+id).html("<i class=\"fa fa-folder\"></i>" + nowStatus);
				}else{
					// 失败
					
				}
			}
		});
  		  
		}
    	
    	))
    	return false;
    }
    
    // ------------------------------------------------AJAX(结束)--------------------------------------------------------
    </script>

</head>

<body class="gray-bg" style="padding:0px;margin:0px">

    <div class="wrapper wrapper-content animated fadeInUp" style="padding:0px;margin:0px">
        <div class="row">
            <div class="col-sm-12">

                <div class="ibox">
                    <div class="ibox-title">
                        <h5>banner图片列表</h5>
                        <div class="ibox-tools">
                            <!-- <a href="projects.html" class="btn btn-primary btn-xs">创建新项目</a> -->
                        </div>
                    </div>
                    <div class="ibox-content">

                        <div class="project-list">
	                        <div  class="row m-b-sm m-t-sm">
                             <form id="dataForm" action="/mei_yi_quan_admin/rdcelebrity/homepageList.do"  method="post">  
                            <input type="hidden" name="page" value="${requestScope.pageIndex }">
                        	<input type="hidden" name="size" value="${requestScope.pageSize }">
                         	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         	<span class="col-sm-12" style="margin-top: 10px;">
                         	推荐类型：<select id="type" name="type">
                         				<option value="" <c:if test="${(requestScope.status eq null) or (requestScope.status eq '') }">selected</c:if>>全部</option>
                          				<option value="1" <c:if test="${requestScope.status eq '1' }">selected</c:if>>视频</option>
                          				<option value="2" <c:if test="${requestScope.status eq '2' }">selected</c:if>>名人大佬</option>
                          				<option value="3" <c:if test="${requestScope.status eq '3' }">selected</c:if>>开店创业</option>
                          				<option value="4" <c:if test="${requestScope.status eq '3' }">selected</c:if>>潮流技术</option>
                         		   	 </select>
                            </span>
                         
                      	 	<div class="col-md-11">
                                <div class="input-group" align="left">
	                                <span class="col-sm-3">
                             </span>
                                 <span class="input-group-btn" style="font-size:15px">
                                   	<button type="button" class="btn btn-sm btn-primary" onclick="findall()">搜索</button>
                                   	<button type="button" class="btn btn-sm btn-primary" onclick="toadd()">添加</button>
	                            </span>
                               </div>
                            </div>
                        </form>
                        </div>
                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
                                			<td class="project-title" align="center">首页图片</td>
	                                        <td class="project-title" align="center">推荐类型</td>
	                                        <td class="project-title" align="center">名称</td>
	                                        <td class="project-completion" align="center" >状态</td>
	                                        <td class="project-completion" align="center" >操作</td>
                                   	    </tr>
                                	<c:forEach items="${requestScope.code.data.list }" var="homepage">
                                		<tr id="tr${homepage.id }">
                                			<td class="project-title" align="center">
	                                            <a class="fancybox" href="${homepage.pic_url }" title="展示图"><img src="${homepage.pic_url }" style="width:50px;height:50px" /></a>
	                                        </td>
	                                        <td class="project-title" align="center">
												<c:if test="${homepage.type eq 1 }">视频</c:if>
												<c:if test="${homepage.type eq 2 }">名人大佬</c:if>
												<c:if test="${homepage.type eq 3 }">开店创业</c:if>
												<c:if test="${homepage.type eq 4 }">潮流技术</c:if>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${homepage.name}</small>
	                                        </td>
	                                        <td class="project-title" align="center" id="status${homepage.id}">
	                                        	<small>
													<c:if test="${homepage.status eq 1 }">启用</c:if>
													<c:if test="${homepage.status eq 2 }">禁用</c:if>
												</small>
	                                        </td>
	                                        <td class="project-completion" align="center">
	                                        	<a href="#" class="btn btn-white btn-sm" onclick="return upStatus('${homepage.id}','${homepage.name }')" id="ahref${homepage.id}"><i class="fa fa-folder"></i>
	                                        		<c:if test="${homepage.status eq 2 }">启用</c:if>
													<c:if test="${homepage.status eq 1 }">禁用</c:if>
	                                        	</a>
	                                            <a href="${pageScope.basePath }rdcelebrity/homeInfo.do?id=${homepage.id }" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>编辑 </a>
	                                            <a href="#" class="btn btn-white btn-sm" onclick="return isDelete('${banner.name }','${banner.id }')"><i class="fa fa-pencil"></i>删除 </a>
	                                        </td>
                                   	    </tr>
                                	</c:forEach>
                                		<tr>
                                			<td colspan="9" align="center" >
                                            <div id="pageToolbar"></div></td>
                                		</tr>
                                    </tbody>
                                    
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
    <script src="${pageScope.basePath }app/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageScope.basePath }app/js/content.min.js?v=1.0.0"></script>
    <script>
       $(document).ready(function(){$("#loading-example-btn").click(function(){btn=$(this);simpleLoad(btn,true);simpleLoad(btn,false)})});function simpleLoad(btn,state){if(state){btn.children().addClass("fa-spin");btn.contents().last().replaceWith(" Loading")}else{setTimeout(function(){btn.children().removeClass("fa-spin");btn.contents().last().replaceWith(" Refresh")},2000)}};
       $(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    <script type="text/javascript" src="${pageScope.basePath }app/js/jquery-1.11.2.js"></script>
	<script type="text/javascript" src="${pageScope.basePath }app/js/query.js"></script>
	<script type="text/javascript" src="${pageScope.basePath }app/js/paging.js"></script>
	<script>
	 $('#pageToolbar').Paging({current:'${requestScope.page}',pagesize:'${requestScope.size}',count:'${requestScope.code.data.count}',toolbar:true,
			callback:function(page,size,count){
				$("#dataForm input[name='page']").val(page);
				console.log(page)
				$("#dataForm input[name='size']").val(size);
				
				$("#dataForm").submit();
	    	}});
	
	
	</script>
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>