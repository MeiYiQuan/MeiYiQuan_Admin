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
			"&applyLoginName=" + (request.getAttribute("applyLoginName")==null?"":request.getAttribute("applyLoginName").toString()) + 
			"&aproveLoginName=" + (request.getAttribute("aproveLoginName")==null?"":request.getAttribute("aproveLoginName").toString()) + 
			"&couponName=" + (request.getAttribute("couponName")==null?"":request.getAttribute("couponName").toString()) + 
			"&applyTimeBegin=" + (request.getAttribute("applyTimeBegin")==null?"":request.getAttribute("applyTimeBegin").toString()) + 
			"&applyTimeEnd=" + (request.getAttribute("applyTimeEnd")==null?"":request.getAttribute("applyTimeEnd").toString()) + 
			"&verifyTimeEnd=" + (request.getAttribute("verifyTimeEnd")==null?"":request.getAttribute("verifyTimeEnd").toString()) + 
			"&verifyTimeBegin=" + (request.getAttribute("verifyTimeBegin")==null?"":request.getAttribute("verifyTimeBegin").toString());
pageContext.setAttribute("basePath", basePath);
pageContext.setAttribute("conditions", conditions);
%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>优惠券审核管理</title>
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
    
    
    	
    	
    	
    // ------------------------------------------------通用部分(结束)--------------------------------------------------------
    
    
    
    // ------------------------------------------------AJAX(开始)--------------------------------------------------------
    
    // 对审核的通过和拒绝
    function chuli(id,index){
    	var confStr = index==2?"通过":"驳回";
    	layer.confirm("确定要" + confStr + "该优惠券申请吗？",{
	   		 btn: ['是的','我再想想']
	   	},function(){
	   		$.ajax({
				url:"${pageScope.basePath }money/updateShengqin.do",
				type:"POST",
				data:{id:id,status:index},
				async:false,
				dataType:"json",
				success:function (resp){
					layer.alert(resp.message);
					if(resp.code==0){
						// 成功
						$("#caozuo"+id).html("已处理");
						$("#upTime"+id).html(resp.data.upTime);
						$("#admin"+id).html(resp.data.admin);
						var sta = resp.data.status;
						if(sta==2){
							$("#statu"+id).html("通过");
						}else if(sta==3){
							$("#statu"+id).html("未通过");
						}else{
							layer.alert("返回了非法参数！");
						}
					}else{
						// 失败
						
					}
				}
			});
	   	},function(){
	   		
	   	});
    }
    
    
    function findall(){
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
                        <h5>优惠券审核列表</h5>
                        <div class="ibox-tools">
                            <!-- <a href="projects.html" class="btn btn-primary btn-xs">创建新项目</a> -->
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div  class="row m-b-sm m-t-sm">
                            <form id="dataForm" action="/mei_yi_quan_admin/message/tmList.do"  method="post">  
                        	
                         	
                         	<span class="col-sm-12" style="margin-top: 10px;">
                    		输入讲师名称：<input type="text" id="name" name="name" value="${requestScope.name }" />
                         	<br/><br/>
                            </span>
                         
                      	 	<div class="col-md-11">
                                <div class="input-group" align="left">
	                                <span class="col-sm-3">
                             </span>
	                                    <span class="input-group-btn" style="font-size:15px">
		                                    	<button type="button" class="btn btn-sm btn-primary" onclick="findall()">搜索</button>
				                             
	                           </span>
                                </div>
                            </div>
                        </form>
                        
                        </div>
                        <div class="project-list">

                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
                                		<td class="project-title" align="center">申请教师名称</td>
	                                        <td class="project-title" align="center">提交人id</td>
	                                        <td class="project-title" align="center">提现金额</td>
	                                        <td class="project-title" align="center">提现后剩余金额</td>
	                                        <td class="project-title" align="center">提现类型</td>
	                                        <td class="project-title" align="center">提现账号</td>
	                                        <td class="project-title" align="center" >申请时间</td>
	                                        <td class="project-title" align="center" >审核人id</td>
	                                        <td class="project-title" align="center" >审核时间</td>
	                                        <td class="project-title" align="center" >状态</td>
	                                        <td class="project-title" align="center" >操作</td>
                                   	    </tr>
                                	
                                		<c:forEach items="${requestScope.code.data.list }" var="data">
                                		<tr>
                                			<td class="project-title" align="center">
	                                            <small>${data.name}</small>
	                                        </td>
	                                        
	                                        <td class="project-title" align="center">
	                                            <small>${data.apply_admin }</small>
	                                        </td>
	                                        
	                                        <td class="project-title" align="center">
	                                            <small>${data.send_money }</small>
	                                        </td>
	                                        
	                                        <td class="project-title" align="center">
	                                            <small>${data.shengyu }</small>
	                                        </td>
	                                        
	                                        <td class="project-title" align="center">
	                                            <small>${data.make_type }</small>
	                                        </td>
	                                        
	                                        <td class="project-title" align="center">
	                                            <small>${data.make_account }</small>
	                                        </td>
	                                        
	                                        <td class="project-title" align="center">
	                                            <small>${data.apply_time }</small>
	                                        </td>
	                                        
	                                        <td class="project-title" align="center">
	                                            <small>${data.verify_admin }</small>
	                                        </td>
	                                        
	                                        <td class="project-title" align="center">
	                                            <small>${data.verify_time }</small>
	                                        </td>
	                                        
	                                        <td class="project-title" align="center">
	                                           <c:if test="${data.status eq 1 }">
	                                            <small>审核中</small>
	                                            </c:if>
	                                            <c:if test="${data.status eq 2 }">
	                                            <small>通过</small>
	                                            </c:if>
	                                            <c:if test="${data.status eq 3 }">
	                                            <small>驳回</small>
	                                            </c:if>
	                                        </td>
	                                        
	                                        
	                                        
	                                        <td class="project-completion" align="center" >
	                                        	
	                                        	<c:if test="${data.status eq '1' }">
	                                        		<a href="${pageScope.basePath }message/tmupdate.do?id=${data.id}&status=2" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>通过</a>
	                                        		<a href="${pageScope.basePath }message/tmupdate.do?id=${data.id}&status=3" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>驳回</a>
	                                        	</c:if>
	                                        	
	                                        	<c:if test="${data.status eq '2' }">
	                                        		已处理
	                                        	</c:if>
	                                        	<c:if test="${data.status eq '3' }">
	                                        		已处理
	                                        	</c:if>
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
    
    <script src="${pageScope.basePath }app/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageScope.basePath }app/js/content.min.js?v=1.0.0"></script>
   	<script type="text/javascript" src="${pageScope.basePath }app/js/paging.js"></script>
    <script>
    $('#pageToolbar').Paging({current:'${requestScope.page}',pagesize:'${requestScope.size}',count:'${requestScope.code.data.count}',toolbar:true,
		callback:function(page,size,count){
			$("#dataForm input[name='page']").val(page);
			console.log(page)
			$("#dataForm input[name='size']").val(size);
			$("#dataForm").submit();
    	}});
       $(document).ready(function(){$("#loading-example-btn").click(function(){btn=$(this);simpleLoad(btn,true);simpleLoad(btn,false)})});function simpleLoad(btn,state){if(state){btn.children().addClass("fa-spin");btn.contents().last().replaceWith(" Loading")}else{setTimeout(function(){btn.children().removeClass("fa-spin");btn.contents().last().replaceWith(" Refresh")},2000)}};
       $(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});
    
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>

