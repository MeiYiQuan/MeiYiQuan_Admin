<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="qc" uri="http://qc/qcTag" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/" ;
String conditions = "";
pageContext.setAttribute("basePath", basePath);
pageContext.setAttribute("conditions", conditions);
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>角色管理</title>
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
    	
    	// 刷新
    	function refresh(){
    		window.location.href = "${pageScope.basePath }role/getRolesList.do?page=${requestScope.page.page }&size=" + pageEach;
    	}
    	
    	// 添加角色
    	function addrole(){
    		var roleName = $("#addRole").val();
    		$.ajax({
				url:"${pageScope.basePath }role/addRole.do",
				type:"POST",
				data:{name:roleName},
				async:false,
				dataType:"json",
				success:function (resp){
					layer.alert(resp.message);
					
				}
			});
    	}
    	
    	// 点击删除
    	function isDelete(name,id){
    		layer.confirm(("确定要删除名为 " + name + " 的角色吗？"),
    						{btn: ['确定','取消']},
    						function(){
    							$.ajax({
    			    				url:"${pageScope.basePath }role/deleteRole.do",
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
    						},
    						function(){});
			    			
    		return false;
    	}
    	
    // ------------------------------------------------通用部分(结束)--------------------------------------------------------
    
    
    
    // ------------------------------------------------AJAX(开始)--------------------------------------------------------
    // ------------------------------------------------AJAX(结束)--------------------------------------------------------
    </script>

</head>

<body class="gray-bg" style="padding:0px;margin:0px">

    <div class="wrapper wrapper-content animated fadeInUp" style="padding:0px;margin:0px">
        <div class="row">
            <div class="col-sm-12">

                <div class="ibox">
                    <div class="ibox-title">
                        <h5>优惠券列表</h5>
                        <div class="ibox-tools">
                            <!-- <a href="projects.html" class="btn btn-primary btn-xs">创建新项目</a> -->
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm" align="right" style="padding-right: 50px">
                        
	                        <input id="addRole" type="text" size="30" name="name" value="" placeholder="新增角色" />
	                        &nbsp;&nbsp;&nbsp;
	                        <button type="button" class="btn btn-sm btn-primary" onclick="addrole()">添加</button>
                            
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <button type="button" class="btn btn-sm btn-primary" onclick="refresh()">刷新</button>
                            
                        </div>

                        <div class="project-list">

                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
	                                        <td class="project-completion" align="center" >序号</td>
	                                        <td class="project-completion" align="center" >角色名称</td>
	                                        <td class="project-completion" align="center" >操作</td>
                                   	    </tr>
                                	
                                	<c:forEach items="${requestScope.result }" var="role" varStatus="index">
                                		<tr id="tr${role.id }">
	                                        <td class="project-title" align="center">
												<small>${index.index + 1 }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${role.roleName }</small>
	                                        </td>
	                                        <td class="project-completion" align="center">
	                                            <a href="${pageScope.basePath }role/sendRoleMenus.do?id=${role.id}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>赋予菜单</a>
	                                            <a href="#" class="btn btn-white btn-sm" onclick="return isDelete('${role.roleName }','${role.id }')"><i class="fa fa-pencil"></i>删除 </a>
	                                        </td>
                                   	    </tr>
                                	</c:forEach>
                                		<tr>
                                			<td colspan="3" align="center" >
                                				<qc:qcPage sumPage="${requestScope.page.sumPages }" url="${pageScope.basePath }role/getRolesList.do" 
	                                				conditions="size=${requestScope.page.eachRows }" 
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