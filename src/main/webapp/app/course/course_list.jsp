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
	<script src="/mei_yi_quan_admin/app/js/jquery.min.js?v=2.1.4"></script>
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
    	
    	// 新增页面
    	function toadd(pid){
        	layer.prompt({title: '输入名称，并确认', formType: 2}, function(name, index){
    		    layer.close(index);
    			  $.ajax({
    					url:"${pageScope.basePath }district/add",
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
				url:"${pageScope.basePath }course/del",
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
			url:"${pageScope.basePath }course/upStatus.do",
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
                        <h5>课程列表</h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm">
                            <form id="dataForm" action="/mei_yi_quan_admin/course/getCourseList">  
                            <input type="hidden" name="pageIndex" value="${requestScope.pageIndex }">
                        	<input type="hidden" name="pageSize" value="${requestScope.pageSize }"> 
                        	<input type="hidden" name="orderBy" value="${requestScope.orderBy }"> 
                        	<input type="hidden" name="collation" value="${requestScope.collation }"> 
                      	 	<div class="col-md-11">
                                <div class="input-group" align="left">
	                                <span class="col-sm-3">
	                                 <input type="text" placeholder="请输入名称" class="form-control" name="keywords" value="${requestScope.keywords }">
                             </span>
                               <span class="col-sm-3">
                               <select class="form-control m-b" name="status" id="status" >
                                  <option value="">请选择状态</option>
                                  <option value="1">启用</option>
                                  <option value="2">禁用</option>
                               </select>
                             </span>
                                  <span class="col-sm-11">
                            <select id="firstCate" name="firstCate"   onchange="channellian(this,'#secondCate')" style="margin: 5px ;height: 34px;">
                              	<option value="">请选择类别</option>
                            </select>
                            <select id="secondCate" name="thirdCate"   onchange="channellian(this,'#thirdCate')" style="margin: 5px ;height: 34px;">
	                            	<option value="">请选择类别</option>
	                              </select>
	                                   <select id="thirdCate"   name="channel"  onchange="addthirdCate(this,'#thirdCate')" style="margin: 5px ;height: 34px;">
	                            	<option value="">请选择类别</option>
	                             </select>
                             </span>
                                <span class="col-sm-4" style="line-height: 32px;font-size: 20px;">
                                  创建日期：
                                </span>
                             <span class="col-sm-4">
	                                    		<input   name="timeStar" class="Wdate" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${requestScope.timeStar }" style="width:200px;height: 32px;"/> - 
	                                        	<input    name="timeEnd" class="Wdate" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${requestScope.timeEnd }" style="width:200px;height: 32px;"/>
                             </span>
                               <span class="input-group-btn" style="font-size:15px">
	                                    <a   href="javascript:submitForm();" class="btn btn-primary ">搜索</a>
	                                       <a   href="${pageScope.basePath }course/addPage" class="btn btn-primary ">添加</a>
	                           </span>
                                </div>
                            </div>
                        </form>
                        </div>
                        <div class="project-list">
                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
	                                        <td class="project-title" align="center"><a href="javascript:submitFormOrder('tb_course.title','${requestScope.collation }')" style="font-size: 12px;]">名称</a></td>
	                                        <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('tb_teacher.name','${requestScope.collation }')" style="font-size: 12px;]">讲师名称</a></td>
	                                        <td class="project-title" align="center"> 
	                                        <a href="javascript:submitFormOrder('tb_course.create_time','${requestScope.collation }')" style="font-size: 12px;]">创建时间</a></td>
	                                        <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('s.click_count','${requestScope.collation }')" style="font-size: 12px;]">点击量</a> </td>
	                                        <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('videonum','${requestScope.collation }')" style="font-size: 12px;]">视频量</a> </td>
	                                           <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('orderNum','${requestScope.collation }')" style="font-size: 12px;]">购买量</a> </td>
	                                             <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('orderPrice','${requestScope.collation }')" style="font-size: 12px;]">购买金额</a> </td>
	                                              <td class="project-title" align="center">
	                                        状态 </td>
	                                        <td class="project-completion" align="center" >操作</td>
                                   	    </tr>
                                	<c:forEach items="${requestScope.code.data.list }" var="data">
                                		<tr id="tr${data.id }">
	                                        <td class="project-title" align="center">
												<small>${data.title }</small>
	                                        </td>
	                                          <td class="project-title" align="center">
												<small>${data.name }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>
												<jsp:useBean id="dtCreat" class="java.util.Date"/>
												<jsp:setProperty name="dtCreat" property="time" value="${data.create_time }"/>
												<fmt:formatDate value="${dtCreat}" pattern="yyyy年MM月dd日  HH:mm:ss"/>
												</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.click_count }
													</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.videonum }
													</small>
	                                        </td>
	                                          <td class="project-title" align="center">
													<small>
													${data.orderNum }
													</small>
	                                        </td>
	                                          <td class="project-title" align="center">
													<small>
													${data.orderPrice }
													</small>
	                                        </td>
	                                             <td class="project-title" align="center">
													<small>
													<c:if test="${data.status eq '1' }">启用</c:if>
													<c:if test="${data.status eq '2' }">禁用</c:if>
												</small>
	                                        </td>
	                                        <td class="project-completion" align="center">
	                                        <c:if test="${data.status eq '1' }">
	                                        	<a href="#" class="btn btn-white btn-sm" onclick="return upStatus('${data.id}','2')" > 
	                                        		禁用
	                                        	</a>
	                                        </c:if>
	                                        	<c:if test="${data.status eq '2' }">
		                                        	<a href="#" class="btn btn-white btn-sm" onclick="return upStatus('${data.id}','1')" > 
		                                        		启用
		                                        	</a>
	                                        	</c:if>
	                                            <a href="${pageScope.basePath }course/updatePage?id=${data.id}" class="btn btn-white btn-sm">编辑 </a>
	                                            <%-- <a href="#" class="btn btn-white btn-sm" onclick="return delAll('${data.id }')"><i class="fa fa-pencil"></i>删除 </a> --%>
	                                       		  <a href="${pageScope.basePath }order/getListByVideoId?id=${data.id}" class="btn btn-white btn-sm">查看订单 </a>
	                                       		  <a href="${pageScope.basePath }video/getVideoList?id=${data.id}" class="btn btn-white btn-sm" >编辑上传视频 </a>
	                                       		  <a href="${pageScope.basePath }course/recommendList.do?id=${data.id}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>相关推荐列表 </a>
	                                         	<a href="#" class="btn btn-white btn-sm" onclick="getDataId('${data.id}')">获得ID</a>
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
    $('#pageToolbar').Paging({current:'${requestScope.pageIndex}',pagesize:'${requestScope.code.data.pageSize}',count:'${requestScope.code.data.count}',toolbar:true,
		callback:function(page,size,count){
			$("#dataForm input[name='pageIndex']").val(page);
			$("#dataForm input[name='pageSize']").val(size);
			 $("#dataForm").submit();
    	}});
     
    $("#dataForm  [name='status']").val("${requestScope.status}");
    	  
       $(document).ready(function(){$("#loading-example-btn").click(function(){btn=$(this);simpleLoad(btn,true);simpleLoad(btn,false)})});function simpleLoad(btn,state){if(state){btn.children().addClass("fa-spin");btn.contents().last().replaceWith(" Loading")}else{setTimeout(function(){btn.children().removeClass("fa-spin");btn.contents().last().replaceWith(" Refresh")},2000)}};
       $(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});
    
       //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>联动查询>>>>>>>>>>>>>>>>>>>>>>
       $(function(){
    	channellian($("#firstCate"),"#firstCate")
    })
    function	 addthirdCate(){
    	$("input[name='channel_id']").val($("#thirdCate").val());
    }
	 
	 function channellian(d,e){
    	var dataId="0";
    	if($(d).val()!=""){
    		dataId=$(d).val();
    	}
    	$("#thirdCate").html('<option value="">请选择类别</option>');
				$.ajax({
					type:"POST",
					url:"${pageScope.basePath }channel/channellian.do",
					async:false, 
					data:{"upId":dataId},
					dataType:"json",
					success:function(data){
						if(data!=null){
							var div="<option value=''>请选择类别</option>";
							for(var i=0;i<data.data.data.length;i++){
								div+="<option value='"+data.data.data[i].id+"'>"+data.data.data[i].na+"</option>";
							}
							$(e).html(div);
						}
					}
					
				});
    }
       
       </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>