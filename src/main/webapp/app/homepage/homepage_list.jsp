<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="qc" uri="http://qc/qcTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/" ;
String conditions = 
			"type=" + (request.getAttribute("type")==null?"":request.getAttribute("type").toString()) + 
			"&status=" + (request.getAttribute("status")==null?"":request.getAttribute("status").toString()) + 
			"&order=" + (request.getAttribute("order")==null?"":request.getAttribute("order").toString());
pageContext.setAttribute("basePath", basePath);
pageContext.setAttribute("conditions", conditions);
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>推荐管理</title>
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
    		var type = $("#type").val();
    		var status = $("#status").val();
    		var order = $("#order").val();
    		window.location.href = "${pageScope.basePath }homepage/getList.do?page=1&size=" + pageEach + 
    				"&type="+type+"&status="+status+"&order="+order;
    	}
    	
    	// 刷新
    	function refresh(){
    		window.location.href = "${pageScope.basePath }homepage/getList.do?page=${requestScope.page.page }&size=" + pageEach + 
    				"&" + "${pageScope.conditions }";
    	}
    	
    // ------------------------------------------------通用部分(结束)--------------------------------------------------------
    
    // 删除
    function isDelete(name,id){
    	layer.confirm("确定要删除名为 " + name + " 的推荐信息吗？",{
	   		 btn: ['是的','我再想想']
	   	},function(){
	   		$.ajax({
				url:"${pageScope.basePath }homepage/delete.do",
				type:"POST",
				data:{id:id},
				async:false,
				dataType:"json",
				success:function (resp){
					layer.alert(resp.message);
					if(resp.code==0){
						// 成功
						$("#tr" + id)[0].style.display = "none";
					}else{
						// 失败
						
					}
				}
			});
	   	},function(){
	   		
	   	});
    	return false;
    }

    
    // ------------------------------------------------AJAX(开始)--------------------------------------------------------
    
    // 启用和禁用
    function upStatus(id,name){
    	var confStr = $("#statu"+id).text().trim()=="不启用"?"启用":"禁用";
    	var index = $("#statu"+id).text().trim()=="不启用"?1:2;
    	layer.confirm("确定要" + confStr + "该推荐信息吗？",{
	   		 btn: ['是的','我再想想']
	   	},function(){
	   		$.ajax({
				url:"${pageScope.basePath }homepage/updateStatus.do",
				type:"POST",
				data:{id:id,status:index},
				async:false,
				dataType:"json",
				success:function (resp){
					layer.alert(resp.message);
					if(resp.code==0){
						// 成功
						$("#upTime"+id).html(resp.data);
						if(index==1){
							$("#statu"+id).html("启用");
							$("#ahref"+id).html("<i class=\"fa fa-folder\"></i>" + "禁用");
						}else if(index==2){
							$("#statu"+id).html("不启用");
							$("#ahref"+id).html("<i class=\"fa fa-folder\"></i>" + "启用");
						}
					}else{
						// 失败
						
					}
				}
			});
	   	},function(){
	   		
	   	});
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
                        <h5>推荐管理列表</h5>
                        <div class="ibox-tools">
                            <!-- <a href="projects.html" class="btn btn-primary btn-xs">创建新项目</a> -->
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm">
                            <div class="col-md-11">
                                <div class="input-group" align="left">
	                                    	推荐类型：<select id="type">
	                                    					<option value="" <c:if test="${(requestScope.type eq null) or (requestScope.type eq '') }">selected</c:if>>全部</option>
		                                    				<option value="1" <c:if test="${requestScope.type eq '1' }">selected</c:if>>课程</option>
		                                    				<option value="2" <c:if test="${requestScope.type eq '2' }">selected</c:if>>名人大佬</option>
		                                    				<option value="3" <c:if test="${requestScope.type eq '3' }">selected</c:if>>开店创业</option>
		                                    				<option value="4" <c:if test="${requestScope.type eq '4' }">selected</c:if>>潮流技术</option>
	                                    		   	 </select>
	                                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                    	首页是否启用：<select id="status">
	                                    					<option value="" <c:if test="${(requestScope.status eq null) or (requestScope.status eq '') }">selected</c:if>>全部</option>
		                                    				<option value="1" <c:if test="${requestScope.status eq '1' }">selected</c:if>>启用</option>
		                                    				<option value="2" <c:if test="${requestScope.status eq '2' }">selected</c:if>>不启用</option>
	                                    		   	 </select>
	                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                                                                                          置顶排序：<select id="order">
	                                    					<option value="" <c:if test="${(requestScope.order eq null) or (requestScope.order eq '') }">selected</c:if>>不排序</option>
		                                    				<option value="1" <c:if test="${requestScope.order eq '1' }">selected</c:if>>升序</option>
		                                    				<option value="2" <c:if test="${requestScope.order eq '2' }">selected</c:if>>降序</option>
	                                    		   	 </select>
	                                     	<span class="input-group-btn" style="font-size:15px">
	                                        	<button type="button" class="btn btn-sm btn-primary" onclick="refresh()">刷新</button>
	                                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                        	<button type="button" class="btn btn-sm btn-primary" onclick="sousuo()">搜索</button>
	                                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                        	<button type="button" class="btn btn-sm btn-primary" onclick="javascript:window.location.href='${pageScope.basePath }homepage/toadd.do';">添加</button>
	                                        </span>
                                </div>
                                <br/><br/>
                            </div>
                        </div>

                        <div class="project-list">

                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
                                			<td class="project-title" align="center">首页图片</td>
	                                        <!-- <td class="project-title" align="center">名称</td> -->
	                                        <td class="project-title" align="center">推荐类型</td>
	                                        <td class="project-title" align="center">是否启用</td>
	                                        <td class="project-title" align="center">置顶参数</td>
	                                        <td class="project-completion" align="center" >创建时间</td>
	                                        <td class="project-completion" align="center" >最后修<br/>改时间</td>
	                                        <td class="project-completion" align="center" >操作</td>
                                   	    </tr>
                                	
                                	<c:forEach items="${requestScope.result }" var="object">
                                		<tr id="tr${object.id }">
                                			<td class="project-title" align="center">
	                                            <a class="fancybox" href="${object.pic_url }" title="展示图"><img src="${object.pic_url }" style="width:60px;height:40px" /></a>
	                                        </td>
	                                       <%--  <td class="project-title" align="center">
												<small>${object.name }</small>
	                                        </td> --%>
	                                        <td class="project-title" align="center">
												<small>
													<c:if test="${object.type eq '1' }">推荐课程</c:if>
													<c:if test="${object.type eq '2' }">推荐名人大佬</c:if>
													<c:if test="${object.type eq '3' }">推荐开店创业</c:if>
													<c:if test="${object.type eq '4' }">推荐潮流技术</c:if>
												</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small id="statu${object.id }">
													<c:if test="${object.status eq '1' }">启用</c:if>
													<c:if test="${object.status eq '2' }">不启用</c:if>
												</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${object.top_num }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${object.creatTime }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small id="upTime${object.id }">${object.updateTime }</small>
	                                        </td>
	                                        <td class="project-completion" align="center" id="caozuo${verify.id }">
	                                        	<a href="#" class="btn btn-white btn-sm" onclick="return upStatus('${object.id}','${object.name }')" id="ahref${object.id}"><i class="fa fa-folder"></i>
	                                        		<c:if test="${object.status eq '1' }">禁用</c:if>
													<c:if test="${object.status eq '2' }">启用</c:if>
	                                        	</a>
	                                        	<a href="${pageScope.basePath }homepage/willUpdate.do?id=${object.id}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>编辑 </a>
	                                        	<a href="#" class="btn btn-white btn-sm" onclick="return isDelete('${object.name }','${object.id }')"><i class="fa fa-pencil"></i>删除 </a>
	                                        </td>
                                   	    </tr>
                                	</c:forEach>
                                		<tr>
                                			<td colspan="8" align="center" >
                                				<qc:qcPage sumPage="${requestScope.page.sumPages }" url="${pageScope.basePath }homepage/getList.do" 
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