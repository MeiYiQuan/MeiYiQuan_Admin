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
	<script src="${pageScope.basePath }app/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script type="text/javascript" src="${pageScope.basePath }app/js/My97DatePicker/WdatePicker.js"></script>
    <script src="${pageScope.basePath }app/js/layer/layer.js"></script>
    <script>
    
    // ------------------------------------------------通用部分(开始)--------------------------------------------------------
    
    	// 每页的行数,EL表达式取回的是默认值，这个值可以用js修改的
    	var pageEach = "${requestScope.page.eachRows}";
    
    	// 初始化方法
    	$(function(){
    		if("${requestScope.message}".trim()!=""){layer.alert("${requestScope.message}");}
    		$("#eachPages").val(pageEach);
    	});
    	
    	// 修改每页显示的行数
    	function upEachPages(){
    		var input = $("#eachPages").val();
    		if(!isNaN(input)){
    			if(input<=0){
    				layer.alert("每页显示行数要大于0！");
    			}else{
    				$(".qcpagestyle").each(function(){
    					var h = $(this).attr("href");
        	    		var newH = h.replace("&size=" + pageEach,"&size=" + input);
        	    		$(this).attr("href",newH);
    				});
    				pageEach = input;
    				layer.alert("修改成功！每页显示" + pageEach + "行！请刷新页面！");
    			}
    		}else{
    			layer.alert("请输入数字！");
    		}
    		return false;
    	}
    	
    	// 点击搜索
    	function sousuo(){
    		var orderNum = $("#orderNum").val();
    		var createTime = $("#createTime").val();
    		var updateTime = $("#updateTime").val();
    		var bannerType = $("#bannerType").val();
    		var status = $("#status").val();
    		var createBegin = $("#createBegin").val();
    		var createEnd = $("#createEnd").val();
    		var updateBegin = $("#updateBegin").val();
    		var updateEnd = $("#updateEnd").val();
    		window.location.href = "${pageScope.basePath }banner/banners.do?page=1&size=" + pageEach + 
    				"&orderNum="+orderNum+"&createTime="+createTime+"&updateTime="+updateTime+
    				"&bannerType="+bannerType+"&status="+status+
    				"&createBegin="+createBegin+"&createEnd="+createEnd+
    				"&updateBegin="+updateBegin+"&updateEnd="+updateEnd;
    	}
    	
    	// 刷新
    	function refresh(){
    		window.location.href = "${pageScope.basePath }rdcelebrity/toadd.do?page=${requestScope.page.page }&size=" + pageEach + 
    				"&" + "${pageScope.conditions }";
    	}
    	//添加
    	function toadd(){
    		window.location.href = "${pageScope.basePath }rdcelebrity/toadd.do";
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
    	var n = nowStatus=="启用"?0:1;
    	if(layer.confirm("确定要" + confStr + "名为" + name + "的讲师至首页吗？",{
    		btn: ['确定','取消'] //按钮
    	}, function(){
  		  layer.msg('的确很重要', {icon: 1});
  		
  		  $.ajax({
			url:"${pageScope.basePath }banner/upStatus.do",
			type:"POST",
			data:{id:id,status:n},
			async:false,
			dataType:"json",
			success:function (resp){
				layer.alert(resp.message);
				if(resp.code==1){
					// 成功
					$("#upTime"+id).html(resp.data);
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
	                        <span class="input-group-btn" style="font-size:15px">
	                           	<button type="button" class="btn btn-sm btn-primary" onclick="refresh()">刷新</button>
	                           	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                           	<button type="button" class="btn btn-sm btn-primary" onclick="sousuo()">搜索</button>
	                           	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                           	<button type="button" class="btn btn-sm btn-primary" onclick="${pageScope.basePath }rdcelebrity/toadd.do">添加</button>
	                         </span>
                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
                                			<td class="project-title" align="center">讲师姓名</td>
	                                        <td class="project-title" align="center">讲师等级</td>
	                                        <td class="project-title" align="center">是否已添加至首页</td>
	                                        <td class="project-title" align="center">创建时间</td>
	                                        <td class="project-completion" align="center" >最后修改时间</td>
	                                        <td class="project-completion" align="center" >状态</td>
	                                        <td class="project-completion" align="center" >操作</td>
                                   	    </tr>
                                	<c:forEach items="${requestScope.result }" var="teacher">
                                		<tr id="tr${teacher.id }">
	                                        <td class="project-title" align="center">
												<small>${teacher.name }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${teacher.level }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${teacher.status }</small>
													<c:if test="${teacher.status eq '0' }">否</c:if>
													<c:if test="${teacher.status eq '1' }">是</c:if>
												</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${teacher.create_time }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${teacher.update_time }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small id="status${banner.id}">
													<c:if test="${teacher.status eq '0' }">禁用</c:if>
													<c:if test="${teacher.status eq '1' }">启用</c:if>
												</small>
	                                        </td>
	                                        <td class="project-completion" align="center">
	                                        	<a href="#" class="btn btn-white btn-sm" onclick="return upStatus('${banner.id}','${banner.name }')" id="ahref${banner.id}"><i class="fa fa-folder"></i>
	                                        		<c:if test="${teacher.status eq '1' }">禁用</c:if>
													<c:if test="${teacher.status eq '0' }">启用</c:if>
	                                        	</a>
	                                            <a href="${pageScope.basePath }banner/toEditBanner.do?id=${banner.id }" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>编辑 </a>
	                                            <a href="#" class="btn btn-white btn-sm" onclick="return isDelete('${banner.name }','${banner.id }')"><i class="fa fa-pencil"></i>删除 </a>
	                                        </td>
                                   	    </tr>
                                	</c:forEach>
                                		<tr>
                                			<td colspan="9" align="center" >
                                				<qc:qcPage sumPage="${requestScope.page.sumPages }" url="${pageScope.basePath }rdcelebrity/toadd.do" 
	                                				conditions="size=${requestScope.page.eachRows }&${pageScope.conditions }" 
	                                				style="class=\"qcpagestyle\"" type="${requestScope.pageType }" nowPage="${requestScope.page.page }"/>
                                				&nbsp;&nbsp;&nbsp;&nbsp;共计<font style="color:red;">${requestScope.page.sumRows }</font>条符合条件的信息
                                				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                				每页显示&nbsp;<span><input type="text" id="eachPages" value="" style="width:40px" /></span>&nbsp;条
                                				&nbsp;&nbsp;&nbsp;&nbsp;
                                				<a href="#" class="btn btn-white btn-sm" onclick="return upEachPages()"><i class="fa fa-folder"></i>修改</a>
                                			</td>
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
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>