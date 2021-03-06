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
    	 $(function(){
    		 if("${requestScope.status}"!="1"){
    			 if("${requestScope.message}".trim()!=""){layer.alert("${requestScope.message}");}
    		 }
    		
    	}); 
    	
    
    
    
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
    function delect(id){
   	layer.confirm("确定删除此用户的求教程吗？",{
   		 btn: ['确定','取消']
   	},function(){
   		window.location.href ="${pageScope.basePath }askcourse/delect.do?id="+id+"";
   	},function(){
   		
   	})
			
   	}
    	
	 // 推送
    function push(id){
   	layer.confirm("确定要推送此求教程吗？",{
   		 btn: ['确定','取消']
   	},function(){
   		window.location.href ="${pageScope.basePath }askcourse/push.do?id="+id+"";
   	},function(){
   		
   	})
			
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
                        <h5>即将上映列表</h5>
                    
                        <div class="ibox-tools">
                            <!-- <a href="projects.html" class="btn btn-primary btn-xs">创建新项目</a> -->
                        </div>
                    </div>
                    <div id="tab-1" class="ibox-content">
                        <div  class="row m-b-sm m-t-sm">
                            <form id="dataForm" action="/mei_yi_quan_admin/askcourse/getAskList.do"  method="post">  
                            <input type="hidden" name="page" value="${requestScope.pageIndex }">
                        	<input type="hidden" name="size" value="${requestScope.pageSize }">
                         	<span class="col-sm-12" style="margin-top: 10px;">
                         	关键字类型：<select id="kaystatus" name="kaystatus">
                         				<option value="" <c:if test="${(requestScope.status eq null) or (requestScope.status eq '') }">selected</c:if>>全部</option>
                          				<option value="1" <c:if test="${requestScope.status eq '1' }">selected</c:if>>用户名称</option>
                          				<option value="2" <c:if test="${requestScope.status eq '2' }">selected</c:if>>讲师名称</option>
                          				<option value="3" <c:if test="${requestScope.status eq '3' }">selected</c:if>>标题名称</option>
                         		   	 </select>
                    		输入关键字：<input type="text" id="kaytxt" name="kaytxt" value="${requestScope.channelName }" />
                            </span>
                         	<span class="col-sm-12" style="margin-top: 10px;">
                         	求教程日期：
                         		<input id="createBegin" class="Wdate" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${requestScope.createBegin }" style="width:100px;"/> - 
                             	<input id="createEnd" class="Wdate" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${requestScope.createEnd }" style="width:100px;"/>
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
	                                        <td class="project-title" align="center">求教程名称</td>
	                                        <td class="project-title" align="center">用户名称</td>
	                                        <td class="project-title" align="center">讲师名称</td>
	                                       	<td class="project-title" align="center">求教程时间</td>
	                                       	<td class="project-title" align="center">求教程状态</td>
	                                       	<td class="project-title" align="center">求教程票数</td>
	                                        <td class="project-completion" align="center" >操作</td>
                                   	    </tr>
                                	<c:forEach items="${requestScope.code.data.list }" var="data">
                                		<tr id="tr${data.id }">
	                                        <td class="project-title" align="center">
												<small>${data.course_name }</small>
	                                        </td>
	                                         <td class="project-title" align="center">
	                                         <c:if test="${data.username eq null }">平台添加</c:if>
												<small>${data.username }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.name }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.request_time }
													</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													<c:if test="${data.feedback_status eq '1' }">审核中</c:if>
													</small>
													<small>
													<c:if test="${data.feedback_status eq '2' }">审核失败</c:if>
													</small>
													<small>
													<c:if test="${data.feedback_status eq '3' }">制作中</c:if>
													</small>
													<small>
													<c:if test="${data.feedback_status eq '4' }">制作失败</c:if>
													</small>
													<small>
													<c:if test="${data.feedback_status eq '5' }">制作完成</c:if>
													</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.vote }
													</small>
	                                        </td>
	                                        <td class="project-completion" align="center">
	                                            <a href="${pageScope.basePath }askcourse/getInfo.do?id=${data.id}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>编辑 </a>
	                                             <a href="#"  onclick="push('${data.id}')" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>推送 </a>
	                                            <a href="#" class="btn btn-white btn-sm" onclick="delect('${data.id}')"><i class="fa fa-pencil"></i>删除 </a>
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
			$("#dataForm input[name='pageIndex']").val(page);
			$("#dataForm input[name='pageSize']").val(size);
			 $("#dataForm").submit();
    	}});
       $(document).ready(function(){$("#loading-example-btn").click(function(){btn=$(this);simpleLoad(btn,true);simpleLoad(btn,false)})});function simpleLoad(btn,state){if(state){btn.children().addClass("fa-spin");btn.contents().last().replaceWith(" Loading")}else{setTimeout(function(){btn.children().removeClass("fa-spin");btn.contents().last().replaceWith(" Refresh")},2000)}};
       $(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});
     //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>地址查询开始>>>>>>>>>>>>>>>>>>.
       
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
   			url:"${pageScope.basePath }district/getDistrictList",
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
   				$("#dataForm input[name='tree']").val(value);
   			},error:function (XMLHttpRequest, textStatus, errorThrown){
   					  alert(111);
   			}
   		});
       	
       }
       
       
       
       
          //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>地址查询结束>>>>>>>>>>>>>>>>>>.
          //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>联动查询>>>>>>>>>>>>>>>>>>>>>>
     function getSecondCate(){
			var select1=$("#firstCate option:selected").val();
			$("#secondCate").empty().append("<option value='na'>请选择类别</option>");
			if(select1=="na"){
				$("#thirdCate").empty().append("<option value='na'>请选择类别</option>");
			}else{
				$.ajax({
					type:"POST",
					url:"${pageScope.basePath }/channel/channellian.do",
					async:false, 
					data:{"type":2,"upId":select1},
					dataType:"json",
					success:function(data){
						if(data!=null){
							for(var i=0;i<data.data.data.length;i++){
								$("#secondCate").append("<option value='"+data.data.data[i].id+"'>"+data.data.data[i].na+"</option>");
							}
						}
					}
					
				});
			}
		}
     function getThirdCate(){
    		var select2=$("#secondCate option:selected").val();
    		$("#thirdCate").empty().append("<option value='na'>请选择类别</option>");
    		if(select2!="na"){
    			$.ajax({
    				type:"POST",
    				url:"${pageScope.basePath }/channel/channellian.do",
    				async:false, 
    				data:{"type":3,"upId":select2},
    				dataType:"json",
    				success:function(data){
    					if(data!=null){
    						for(var i=0;i<data.data.data.length;i++){
    							$("#thirdCate").append("<option value='"+data.data.data[i].id+"'>"+data.data.data[i].na+"</option>");
    						}
    					}
    				}
    				
    			});
    		}
    	}
       
          </script>
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>