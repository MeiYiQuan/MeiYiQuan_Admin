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
    <title>用户列表</title>
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
			url:"${pageScope.basePath }user/upStatus.do",
			type:"POST",
			data:{"id":id,"status":status},
			async:false,
			dataType:"json",
			success:function (resp){
				if(resp.code==0){
					//layer.alert(resp.message);
					  $("#dataForm").submit();
				} 
			}
		});
		}
    	))
    	return false;
    }
    // 对用户的禁言操作
    function upComment(id,status){
    	if(layer.confirm("确定要执行此操作吗？",{
    		btn: ['确定','取消'] //按钮
    	}, function(){
  		  $.ajax({
			url:"${pageScope.basePath }user/upComment.do",
			type:"POST",
			data:{"id":id,"status":status},
			async:false,
			dataType:"json",
			success:function (resp){
				if(resp.code==0){
				//	layer.alert(resp.message);
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
                        <h5>用户列表</h5>
                        <div class="ibox-tools">
                            <!-- <a href="projects.html" class="btn btn-primary btn-xs">创建新项目</a> -->
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm">
                            <form id="dataForm" action="/mei_yi_quan_admin/user/getList">  
                            <input type="hidden" name="pageIndex" value="${requestScope.pageIndex }">
                        	<input type="hidden" name="pageSize" value="${requestScope.pageSize }"> 
                        	<input type="hidden" name="orderBy" value="${requestScope.orderBy }"> 
                        	<input type="hidden" name="collation" value="${requestScope.collation }"> 
                      	 	<div class="col-md-11">
                                <div class="input-group" align="left">
	                                <span class="col-sm-3" style="margin-top: 10px;">
	                                 <input type="text" placeholder="请输入名称，手机号" class="form-control" name="keywords" value="${requestScope.keywords }">
                             </span>
                               <span class="col-sm-3"  style="margin-top: 10px;">
                               <select class="form-control m-b" name="user_state" id="user_state">
                                  <option value="">请选择状态</option>
                                  <option value="1">启用</option>
                                  <option value="2">禁用</option>
                               </select>
                             </span>
                                <span class="col-sm-3" style="margin-top: 10px;">
                               <select class="form-control m-b" name="gender" id="gender">
                                  <option value="">请选择性别</option>
                                  <option value="1">女</option>
                                  <option value="2">男</option>
                               </select>
                             </span>
                                 <span class="col-sm-3" style="margin-top: 10px;">
                               <select class="form-control m-b" name="job" id="job">
                                  <option value="">请选择职业</option>
                                  <option value="1">女</option>
                                  <option value="2">男</option>
                               </select>
                             </span>
                               <span class="col-sm-3" style="margin-top: 10px;">
                               <select class="form-control m-b" name="user_type" id="user_type">
                                  <option value="">请选择用户类型</option>
                                  <option value="0">普通用户</option>
                                  <option value="1">讲师用户</option>
                               </select>
                             </span>
                                 <span class="col-sm-3" style="margin-top: 10px;">
                               <select class="form-control m-b" name="zodiac" id="zodiac">
                                  <option value="">请选择星座</option>
                                  <option value="白羊座">白羊座</option>
                                  <option value="金牛座">金牛座</option>
                                  <option value="双子座">双子座</option>
                                  <option value="巨蟹座">巨蟹座</option>
                                  <option value="狮子座">狮子座</option>
                                  <option value="处女座">处女座</option>
                                  <option value="天秤座">天秤座</option>
                                  <option value="天蝎座">天蝎座</option>
                                  <option value="射手座">射手座</option>
                                  <option value="摩羯座">摩羯座</option>
                                  <option value="水瓶座">水瓶座</option>
                                  <option value="双鱼座">双鱼座</option>
                               </select>
                             </span>
                                   <span class="col-sm-2" style="line-height: 32px;font-size: 14px;margin-top: 10px;">
                                  创建日期：
                                </span>
                             <span class="col-sm-4" style="margin-top: 10px;">
	                                    <input   name="timeStar" class="Wdate" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${requestScope.timeStar }" style="width:100px;height: 32px;"/> - 
	                                    <input    name="timeEnd" class="Wdate" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${requestScope.timeEnd }" style="width:100px;height: 32px;"/>
	                           </span>
                                <span class="col-sm-12" style="margin-top: 10px;">
	                               <input type="hidden" name="tree" value="${requestScope.tree }">
	                               <span  class="col-sm-2">
	                                    	<select id="tree" class="form-control m-b" onchange="getValue(this)">
	                                    	<option >请选择</option>
	                                    	<option value="0">北京</option>
	                                    	</select>
	                                    	</span>
	                               	<span  class="col-sm-3" style="line-height: 32px; " id="treeName"></span>
	                                <span  class="col-sm-1"><a   href="javascript:resetValue();" class="btn btn-primary ">地址重置</a></span>
	                                    </span>
	                                   <span class="input-group-btn" style="font-size:15px"> 
	                                    <a   href="javascript:submitForm();" class="btn btn-primary ">搜索</a>
	                                       <a   href="javascript:add(1);" class="btn btn-primary ">添加</a>
	                           </span>
                                </div>
                            </div>
                        </form>
                        </div>
                        <div class="project-list">
                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
	                                        <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('tb_user.phone','${requestScope.collation }')" style="font-size: 12px;]">用户手机号</a></td>
	                                         <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('tb_user.username','${requestScope.collation }')" style="font-size: 12px;]">用户名</a></td>
	                                        <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('tb_user.create_time','${requestScope.collation }')" style="font-size: 12px;]">注册时间</a></td>
	                                          <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('tb_user.gender','${requestScope.collation }')" style="font-size: 12px;]">性别</a></td>
	                                        <td class="project-title" align="center"> 
	                                        <a href="javascript:submitFormOrder('tb_user.birthday','${requestScope.collation }')" style="font-size: 12px;]">生日</a></td>
	                                        <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('tb_user.zodiac','${requestScope.collation }')" style="font-size: 12px;]">星座</a> </td>
	                                        <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('tb_user.district','${requestScope.collation }')" style="font-size: 12px;]">区域</a> </td>
	                                           <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('tb_user.job','${requestScope.collation }')" style="font-size: 12px;]">职业</a> </td>
	                                           <td class="project-title" align="center">状态</td>
	                                        <td class="project-completion" align="center" >操作</td>
                                   	    </tr>
                                	<c:forEach items="${requestScope.code.data.list }" var="data">
                                		<tr id="tr${data.id }">
                                		 <td class="project-title" align="center">
												<small>${data.phone }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.username }</small>
	                                        </td>
	                                          <td class="project-title" align="center">
												<small>	<jsp:useBean id="dtCreat" class="java.util.Date"/>
												<jsp:setProperty name="dtCreat" property="time" value="${data.create_time }"/>
												<fmt:formatDate value="${dtCreat}" pattern="yyyy年MM月dd日  HH:mm:ss"/>
												</small>
	                                        </td>
	                                           <td class="project-title" align="center">
												<small>
													<c:if test="${data.gender eq '1' }">女</c:if>
													<c:if test="${data.gender eq '2' }">男</c:if>
													</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>
													<jsp:useBean id="birthday" class="java.util.Date"/>
												<jsp:setProperty name="birthday" property="time" value="${data.birthday }"/>
												<fmt:formatDate value="${birthday}" pattern="yyyy年MM月dd日  HH:mm:ss"/>
											</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.zodiac }
													</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.district }
													</small>
	                                        </td>
	                                           <td class="project-title" align="center">
													<small>
													${data.job }
													</small>
	                                        </td>
	                                             <td class="project-title" align="center">
													<small>
													<c:if test="${data.user_state eq '1' }">可用</c:if>
													<c:if test="${data.user_state eq '2' }">拉黑</c:if>
												</small>
	                                        </td>
	                                        <td class="project-completion" align="center">
	                                        <a href="/mei_yi_quan_admin/user/getDetails?id=${data.id}" class="btn btn-white btn-sm" ><i class="fa fa-folder"></i>
	                                        		查看详情
	                                        	</a>
	                                        <c:if test="${data.user_state eq '1' }">
	                                        	<a href="#" class="btn btn-white btn-sm" onclick="return upStatus('${data.id}','2')" ><i class="fa fa-folder"></i>
	                                        		拉黑
	                                        	</a>
	                                        </c:if>
	                                        	<c:if test="${data.user_state eq '2' }">
		                                        	<a href="#" class="btn btn-white btn-sm" onclick="return upStatus('${data.id}','1')" ><i class="fa fa-folder"></i>
		                                        		正常
		                                        	</a>
	                                        	</c:if>
	                                        	  <c:if test="${data.isComment eq '1' }">
	                                        	<a href="#" class="btn btn-white btn-sm" onclick="return upComment('${data.id}','2')" ><i class="fa fa-folder"></i>
	                                        		禁言
	                                        	</a>
	                                        </c:if>
	                                        	<c:if test="${data.isComment eq '2' }">
		                                        	<a href="#" class="btn btn-white btn-sm" onclick="return upComment('${data.id}','1')" ><i class="fa fa-folder"></i>
		                                        		正常
		                                        	</a>
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
    $('#pageToolbar').Paging({current:'${requestScope.pageIndex}',pagesize:'${requestScope.code.data.pageSize}',count:'${requestScope.code.data.count}',toolbar:true,
		callback:function(page,size,count){
			$("#dataForm input[name='pageIndex']").val(page);
			$("#dataForm input[name='pageSize']").val(size);
			 $("#dataForm").submit();
    	}});
    $("#dataForm  [name='user_state']").val("${requestScope.user_state}");
    $("#dataForm  [name='gender']").val("${requestScope.gender}");
    $("#dataForm  [name='zodiac']").val("${requestScope.zodiac}");
    $("#dataForm  [name='user_type']").val("${requestScope.user_type}");
    $("#dataForm  [name='job']").val("${requestScope.job}");
    
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
       
       
       </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>