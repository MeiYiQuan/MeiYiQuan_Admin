<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <title>课程列表</title>
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
    
    
    	// 初始化方法
    	/* $(function(){
    		if("${requestScope.message}".trim()!=""){layer.alert("${requestScope.message}");}
    		$("#eachPages").val(pageEach);
    	}); */
    	
    	// 新增页面
    	 function toadd(){
    		window.location.href = "${pageScope.basePath }active/toadd.do";
    	} 
    	
    	// 点击删除
    	/* function delAll(id){
    		if(layer.confirm("删除会删除所有子集及所有有关的关联关系？",{
        		btn: ['确定','取消'] //按钮
        	}, function(){
      		  $.ajax({
				url:"${pageScope.basePath }district/delAll",
				type:"GET",
				data:{"id":id},
				async:false,
				dataType:"json",
				success:function (resp){
					$("#dataForm").submit();
				},error:function (XMLHttpRequest, textStatus, errorThrown){
					  $("#dataForm").submit();
				}
			});
        	}
      		  ))
    		return false;
    	} */
    	
    	
    // ------------------------------------------------通用部分(结束)--------------------------------------------------------
    
    
    
    // ------------------------------------------------AJAX(开始)--------------------------------------------------------
    
    // 对数据的启用和禁用
    function upStatus(id,status){
    	status=	status=="0"? 1:0;
    	if(layer.confirm("确定要执行此操作吗？",{
    		btn: ['确定','取消'] //按钮
    	}, function(){
  		  $.ajax({
			url:"${pageScope.basePath }district/upStatus.do",
			type:"POST",
			data:{"id":id,"status":status},
			async:false,
			dataType:"json",
			success:function (resp){
				if(resp.code==0){
					layer.alert(resp.message);
					  $("#dataForm").submit();
				} 
			}
		});
		}
    	))
    	return false;
    }
    
    
    // 对数据的启用和禁用
    function updateName(id){
    	layer.prompt({title: '输入要修改的名称，并确认', formType: 2}, function(name, index){
		    layer.close(index);
			  $.ajax({
					url:"${pageScope.basePath }district/update",
					type:"POST",
					data:{"id":id,"name":name},
					dataType:"json",
					success:function (data){
							  $("#dataForm").submit();
					},error:function (XMLHttpRequest, textStatus, errorThrown){
							  $("#dataForm").submit();
					}
				});
		    
    	});
    	return false;
    }
    //点击排序
    function submitFormOrder(orderBy){
    	
    		collation='DESC';
    	
    	$("#dataForm input[name='orderBy'] ").val(orderBy);
    	  $("#dataForm").submit();
     }
    
    function getDataId(e){
    	layer.alert(e);
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
                        <h5>用户统计列表</h5>
                    
                        <div class="ibox-tools">
                            <!-- <a href="projects.html" class="btn btn-primary btn-xs">创建新项目</a> -->
                        </div>
                    </div>
                    <div id="tab-1" class="ibox-content">
                        <div  class="row m-b-sm m-t-sm">
                            <form id="dataForm" action="/mei_yi_quan_admin/user/userCount.do">  
                        	<input type="hidden" name="orderBy" value="${requestScope.orderBy }">
                        	<input type="hidden" name="collation" value="${requestScope.collation }">
                         	
                             </span> 
                      	 	<div class="col-md-11">
                                <div class="input-group" align="left">
	                                <span class="col-sm-3">
                           
                                </div>
                            </div>
                        </form>
                        </div>
                        <div class="project-list">
                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
	                                        <td class="project-title" align="center">用户名称</td>
	                                        <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('ask')" style="font-size: 12px;]">求教程量</a></td>
	                                        <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('comm')" style="font-size: 12px;]">评论量</a> </td>
	                                       <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('shar')" style="font-size: 12px;]">分享量</a> </td>
	                                        <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('coll')" style="font-size: 12px;]">收藏量</a></td>
	                                        <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('poi')" style="font-size: 12px;]">积分量</a></td>
	                                        <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('cou')" style="font-size: 12px;]">优惠劵使用量</a></td>
	                                       <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('act')" style="font-size: 12px;]">活动参与量</a> </td>
                                   	    </tr>
                                	<c:forEach items="${requestScope.code.data.list }" var="data">
                                		<tr >
	                                        <td class="project-title" align="center">
												<small>${data.username }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.ask }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.comm}</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.shar }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.coll }
													</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.poi }
													</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.cou }
													</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
												${data.act }
													</small>
	                                        </td>
	                                       
                                   	    </tr>
                                	</c:forEach>
                                    </tbody>
                                </table>
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    
    <script type="text/javascript" src="${pageScope.basePath }app/js/query.js"></script>
    <script src="${pageScope.basePath }app/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageScope.basePath }app/js/content.min.js?v=1.0.0"></script>
   	
	<script type="text/javascript" src="${pageScope.basePath }app/js/paging.js"></script>
    <script>
    
     //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>地址查询开始>>>>>>>>>>>>>>>>>>.
    
       
       
       
          //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>地址查询结束>>>>>>>>>>>>>>>>>>.
       
       </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>