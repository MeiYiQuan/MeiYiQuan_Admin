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
    	
    	$(function(){
        	if( "${genre}".trim()=="1" ){
        		$("#genre").html("用户名称");
        	}
        	if( "${genre}".trim()=="2" ){
        		$("#genre").html("讲师名称");
        	}
        	if( "${genre}".trim()=="3" ){
        		$("#genre").html("标题名称");
        	}
        	
        	
        } )
    	
    	
    	
    	
    // ------------------------------------------------通用部分(结束)--------------------------------------------------------
    
    
    
    // ------------------------------------------------AJAX(开始)--------------------------------------------------------
    
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
    	
  
    //点击排序
    function submitFormOrder(orderBy,collation){
    	if(collation=='ASC'){
    		collation='DESC';
    	}else{
    		collation='ASC';
    	}
    	$("#dataForm input[name='orderBy'] ").val(orderBy);
    	$("#dataForm input[name='collation'] ").val(collation);
    	  $("#dataForm").submit();
     }
    function   findall(){
    	$("#dataForm").submit();
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
                        <h5>订单列表</h5>
                    
                        <div class="ibox-tools">
                            <!-- <a href="projects.html" class="btn btn-primary btn-xs">创建新项目</a> -->
                        </div>
                    </div>
                    <div id="tab-1" class="ibox-content">
                        <div  class="row m-b-sm m-t-sm">
                            <form id="dataForm" action="/mei_yi_quan_admin/money/orderList.do"  method="post">  
                            <input type="hidden" name="page" value="${requestScope.pageIndex }">
                        	<input type="hidden" name="size" value="${requestScope.pageSize }">
                         	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         	<span class="col-sm-12" style="margin-top: 10px;">
                    		输入关键字：<input type="text" id="textt" name="textt" value="${requestScope.channelName }" />
                         	<br/><br/>
                            </span>
                              <span class="col-sm-12  " style="margin-top: 10px;" align="right">
	                                    <a   href="javascript:findall();" class="btn btn-primary ">搜索</a>
	                           </span>
                         	
                        </form>
                        </div>
                        <div class="project-list">
                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
	                                        <td class="project-title" align="center">订单号</td>
	                                       	<td class="project-title" align="center">下单时间</td>
	                                       	<td class="project-title" align="center">支付时间</td>
	                                       	<td class="project-title" align="center">订单类型 </td>
	                                       	<td class="project-title" align="center">用户名称</td>
	                                       	<td class="project-title" align="center">商品价格</td>
	                                       	<td class="project-title" align="center">订单支付价格</td>
	                                       	<td class="project-title" align="center">平台实际获得价格</td>
	                                       	<td class="project-title" align="center">支付类型</td>
	                                       	<td class="project-title" align="center">第三方税率</td>
	                                       	<td class="project-title" align="center">ios内购扣除比例</td>
                                   	    </tr>
                                	<c:forEach items="${requestScope.code.data.list }" var="data">
                                		<tr id="tr${data.id }">
	                                        <td class="project-title" align="center">
												<small>${data.order_num }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.create_time }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.pay_time }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													<c:if test="${data.type eq '1' }">视频订单</c:if>
													</small>
													<small>
													<c:if test="${data.type eq '2' }">活动订单</c:if>
													</small>
													
	                                        </td>
	                                       
	                                        <td class="project-title" align="center">
												<small>${data.username }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.oldPrice }
													</small>
	                                        </td>
	                                         <td class="project-title" align="center">
												<small>${data.price }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.achieve }</small>
	                                        </td>
	                                         <td class="project-title" align="center">
													<small>
													<c:if test="${data.pay_type eq '1' }">支付宝</c:if>
													</small>
													<small>
													<c:if test="${data.pay_type eq '2' }">微信</c:if>
													</small>
													<small>
													<c:if test="${data.pay_type eq '4' }">ios内购</c:if>
													</small>
													
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													<c:if test="${data.pay_type eq '1' }">${data.shuilv}</c:if>
													</small>
													<small>
													<c:if test="${data.pay_type eq '2' }">${data.shuilv}</c:if>
													</small>
													<small>
													<c:if test="${data.pay_type eq '4' }">${data.tax}</c:if>
													</small>
													
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.ios_deduct }</small>
	                                        </td>
                                   	    </tr>
                                	</c:forEach>
                                    </tbody>
                                </table>
									<div id="pageToolbar"></div>
                                
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
    $('#pageToolbar').Paging({current:'${requestScope.page}',pagesize:'${requestScope.size}',count:'${requestScope.code.data.count}',toolbar:true,
		callback:function(page,size,count){
			$("#dataForm input[name='page']").val(page);
			$("#dataForm input[name='size']").val(size);
			 $("#dataForm").submit();
    	}});
       $(document).ready(function(){$("#loading-example-btn").click(function(){btn=$(this);simpleLoad(btn,true);simpleLoad(btn,false)})});function simpleLoad(btn,state){if(state){btn.children().addClass("fa-spin");btn.contents().last().replaceWith(" Loading")}else{setTimeout(function(){btn.children().removeClass("fa-spin");btn.contents().last().replaceWith(" Refresh")},2000)}};
       $(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});
     
       
          </script>
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>