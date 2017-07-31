<!--反馈列表-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <title>讲师列表</title>
    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <link rel="shortcut icon" href="favicon.ico"> <link href="${pageScope.basePath }app/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/animate.min.css" rel="stylesheet">
    <link href="${pageScope.basePath }app/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <link href="${pageScope.basePath }app/js/My97DatePicker/skin/default/datepicker.css" rel="stylesheet">
    <link href="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${pageScope.basePath }app/css/paging.css">
	<script src="/mei_yi_quan_admin/app/js/jquery.min.js?v=2.1.4"></script>
	<script src="${pageScope.basePath }app/js/plugins/fancybox/jquery.fancybox.js"></script>
    <script type="text/javascript" src="${pageScope.basePath }app/js/My97DatePicker/WdatePicker.js"></script>
    <script src="${pageScope.basePath }app/js/layer/layer.js"></script>

    <script>
    // ------------------------------------------------通用部分(开始)--------------------------------------------------------
    
    	// 每页的行数,EL表达式取回的是默认值，这个值可以用js修改的
    
    	// 初始化方法
    	$(function(){
    		if("${requestScope.message}".trim()!=""){layer.alert("${requestScope.message}");}
    	});
    	
    	// 新增页面
    	function toadd(pid){
        	layer.prompt({title: '输入名称，并确认', formType: 2}, function(name, index){
    		    layer.close(index);
    			  $.ajax({
    					url:"${pageScope.basePath }teacher/add",
    					type:"POST",
    					data:{"pid":"0","name":name},
    					dataType:"json",
    					success:function (data){
    							  $("#dataForm").submit();
    					},error:function (XMLHttpRequest, textStatus, errorThrown){
    							  $("#dataForm").submit();
    					}
    				});
    		    
        	});
    	}
    	
    	// 点击删除
    	function delAll(id){
    		if(layer.confirm("删除会删除所有子集及所有有关的关联关系？",{
        		btn: ['确定','取消'] //按钮
        	}, function(){
      		  $.ajax({
				url:"${pageScope.basePath }teacher/delAll",
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
    	}
    	
    	
    // ------------------------------------------------通用部分(结束)--------------------------------------------------------
    
    
    
    // ------------------------------------------------AJAX(开始)--------------------------------------------------------
    
    // 对数据的启用和禁用
    function upStatus(id,status){
    	if(layer.confirm("确定要执行此操作吗？",{
    		btn: ['确定','取消'] //按钮
    	}, function(){
  		  $.ajax({
			url:"${pageScope.basePath }teacher/upStatus.do",
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
    function updateWorth(id){
    	layer.prompt({title: '输入要修改的数字，并确认', formType: 2}, function(worth, index){
		    layer.close(index);
			  $.ajax({
					url:"${pageScope.basePath }teacher/updateWorth",
					type:"POST",
					data:{"id":id,"worth":worth},
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
    
    
    // 对数据的启用和禁用
    function updateName(id){
    	layer.prompt({title: '输入要修改的名称，并确认', formType: 2}, function(name, index){
		    layer.close(index);
			  $.ajax({
					url:"${pageScope.basePath }teacher/update",
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
    
    function submitForm(){
  	  $("#dataForm").submit();
   }
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
    // ------------------------------------------------AJAX(结束)--------------------------------------------------------
    </script>

</head>

<body class="gray-bg" style="padding:0px;margin:0px">

    <div class="wrapper wrapper-content animated fadeInUp" style="padding:0px;margin:0px">
        <div class="row">
            <div class="col-sm-12">

                <div class="ibox">
                    <div class="ibox-title">
                        <h5>反馈列表</h5>
                    
                        <div class="ibox-tools">
                            <!-- <a href="projects.html" class="btn btn-primary btn-xs">创建新项目</a> -->
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm">
                            <form id="dataForm" action="/mei_yi_quan_admin/suggestion/suggestionlist.do"  method="post">  
                            <input type="hidden" name="page" value="${requestScope.pageIndex }">
                        	<input type="hidden" name="size" value="${requestScope.pageSize }"> 
                      	 	<div class="col-md-12">
                                <div class="input-group" align="left">
                                
                                
                            <span class="col-sm-12" style="margin-top: 10px;">
                    		 用  户  名  称  ：<input type="text" id="kaytxt" name="kaytxt" value="${requestScope.kaytxt }" />
                            </span>
	        				
                            
                                   
			                <span class="col-sm-12" style="margin-top: 10px;">
			                                    选择反馈状态： <select id="type" name="type" style="width:170px;">
			                         				<option value="" <c:if test="${(requestScope.dateType eq null) or (requestScope.dateType eq '') }">selected</c:if>>全部</option>
			                          				<option value="1" <c:if test="${requestScope.dateType eq '1' }">selected</c:if>>已反馈</option>
			                          				<option value="0" <c:if test="${requestScope.dateType eq '0' }">selected</c:if>>未反馈</option>
			                         		   	 </select>
			                </span>
                            <span class="col-sm-12" style="margin-top: 10px;">             
			                                    反  馈  类  型  ：<select id="status" name="status" style="width:170px;">
			                         				<option value="" <c:if test="${(requestScope.dateType eq null) or (requestScope.dateType eq '') }">selected</c:if>>全部</option>
			                          				<option value="1" <c:if test="${requestScope.dateType eq '1' }">selected</c:if>>视频相关</option>
			                          				<option value="2" <c:if test="${requestScope.dateType eq '2' }">selected</c:if>>活动相关</option>
			                          				<option value="3" <c:if test="${requestScope.dateType eq '2' }">selected</c:if>>软件bug相关</option>
			                          				<option value="4" <c:if test="${requestScope.dateType eq '2' }">selected</c:if>>讲师相关</option>
			                          				<option value="5" <c:if test="${requestScope.dateType eq '2' }">selected</c:if>>普通用户相关</option>
			                          				<option value="6" <c:if test="${requestScope.dateType eq '2' }">selected</c:if>>支付相关</option>
			                          				<option value="7" <c:if test="${requestScope.dateType eq '2' }">selected</c:if>>其他</option>
			                         		   	 </select>
                            </span>
                           
                           
                            <span class="col-sm-12" style="margin-top: 10px;">
                                  	
	                                        <input type="hidden" name="tree" value="${requestScope.tree }">
	                                    	选  择  地  区  ：<select id="tree" style="width:170px;"  onchange="getValue(this)">
	                                    	<option >请选择</option>
	                                    	<option value="0">北京</option>
	                                    	</select>
	                         </span>           	
                           	 <span  class="col-sm-12" style="margin-top: 10px;" id="treeName" ></span>
                             <span  class="col-sm-12" style="margin-top: 10px;"><a   href="javascript:resetValue();" class="btn btn-primary ">地址重置</a></span>
                           
                             
                               </div>
                            </div>
                             
                             <div class="col-md-11">
                                <div class="input-group" align="left">
	                                <span class="col-sm-3">
                             </span>
	                                    <span class="input-group-btn" style="font-size:15px">
		                                    	 <a   href="javascript:submitForm();" class="btn btn-primary ">搜索</a>
				                             	
	                           </span>
                                </div>
                            </div>
                              
                        </form>
                        </div>
                        <div class="project-list">
                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
                                			<td class="project-title" align="center">用户姓名</td>
                                			<td class="project-title" align="center">用户手机号</td>
                                			<td class="project-title" align="center">用户职业</td>
                                			<td class="project-title" align="center">用户身份</td>
                                			<td class="project-title" align="center">用户区域</td>
                                        	<td class="project-title" align="center">反馈类型</td>
                                        	<td class="project-title" align="center">反馈内容</td>
                                        	<td class="project-title" align="center">处理状态</td>
                                        	<td class="project-title" align="center">操作</td>
                                   	    </tr>
                                	<c:forEach items="${requestScope.code.data.list }" var="data">
                                		<tr id="tr${data.id }">
                                		 <td class="project-title" align="center">
												<small>${data.username }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.phone_num }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.job }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<c:if test="${data.user_type eq '0' }">
												<small>学员</small>
												</c:if>
												<c:if test="${data.user_type eq '1' }">
												<small>讲师</small>
												</c:if>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.district }</small>
	                                        </td>
	                                        
	                                          <td class="project-title" align="center">
	                                           <c:if test="${data.genre eq '1' }">
												<small>视频相关</small>
												</c:if>
												<c:if test="${data.genre eq '2' }">
												<small>活动相关</small>
												</c:if>
												<c:if test="${data.genre eq '3' }">
												<small>软件bug相关</small>
												</c:if>
												<c:if test="${data.genre eq '4' }">
												<small>讲师相关</small>
												</c:if>
												<c:if test="${data.genre eq '5' }">
												<small>普通用户相关</small>
												</c:if>
												<c:if test="${data.genre eq '6' }">
												<small>支付相关</small>
												</c:if>
												<c:if test="${data.genre eq '7' }">
												<small>其他</small>
												</c:if>
	                                        </td>
                                           <td class="project-title" align="center">
												<small>${data.content }</small>
	                                        </td>
	                                         <td class="project-title" align="center">
	                                          <c:if test="${data.status eq '0' }">
												<small>未处理</small>
												</c:if>
												<c:if test="${data.status eq '1' }">
												<small>已处理</small>
												</c:if>
	                                        </td>
	                                        
	                                         <td class="project-completion" align="center">
	                                         <c:if test="${data.status eq '0' }">
	                                            <a href="${pageScope.basePath }suggestion/suggestionInfo.do?id=${data.id}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>回访 </a>
	                                       </c:if>
	                                         <c:if test="${data.status eq '1' }">
	                                            <a href="${pageScope.basePath }suggestion/suggestionInfo.do?id=${data.id}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>查看 </a>
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
    $("#dataForm  [name='status']").val("${requestScope.status}");
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
    
    
    
    
       $(document).ready(function(){$("#loading-example-btn").click(function(){btn=$(this);simpleLoad(btn,true);simpleLoad(btn,false)})});function simpleLoad(btn,state){if(state){btn.children().addClass("fa-spin");btn.contents().last().replaceWith(" Loading")}else{setTimeout(function(){btn.children().removeClass("fa-spin");btn.contents().last().replaceWith(" Refresh")},2000)}};
       $(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>