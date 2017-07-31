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
    		if("${requestScope.message}".trim()!=""){layer.alert("${requestScope.message}");}
    	}); 
    	
    	// 新增页面
    	 function toadd(){
    		window.location.href = "${pageScope.basePath }active/toadd.do";
    	} 
    	
    	 function sousuo(){
    		 $("#dataForm").submit();
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
     function delect(id){
   	layer.confirm("确定删除此用户吗？",{
   		 btn: ['确定','取消']
   	},function(){
   		window.location.href ="${pageScope.basePath }active/deleteActivity.do?id="+id+"";
   	},function(){
   		
   	})
			
   	}
    
    
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
    function submitFormOrder(orderBy,collation){
    	
    		collation='ASC';
    	
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
                        <h5>区域列表</h5>
                    
                        <div class="ibox-tools">
                            <!-- <a href="projects.html" class="btn btn-primary btn-xs">创建新项目</a> -->
                        </div>
                    </div>
                    <div id="tab-1" class="ibox-content">
                        <div  class="row m-b-sm m-t-sm">
                            <form id="dataForm" action="/mei_yi_quan_admin/active/activelist">  
                            <input type="hidden" name="page" value="${requestScope.pageIndex }">
                        	<input type="hidden" name="size" value="${requestScope.pageSize }">
                        	<input type="hidden" name="orderBy" value="${requestScope.orderBy }">
                        	<input type="hidden" name="collation" value="${requestScope.collation }">
                         	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         	关键字类型：<select id="kaystatus" name="kaystatus">
                             			<option selected value="" <c:if test="${(requestScope.status eq null) or (requestScope.status eq '') }">selected</c:if>>全部</option>
                          				<option value="ac.title" <c:if test="${requestScope.kaystatus eq 'ac.title' }">selected</c:if> >活动名称</option>
                          				<option value="t.name" <c:if test="${requestScope.kaystatus eq 't.name' }">selected</c:if> >参与讲师名称</option>
                         		   	 </select>
                    		输入关键字：<input type="text" id="kaytxt" name="kaytxt" value="${requestScope.kaytxt }" />
                         	<br/><br/>
                         	
                         	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         	活动状态：<select id="status" name="status">
                          				<option value="" <c:if test="${(requestScope.status eq null) or (requestScope.status eq '') }">selected</c:if>>全部</option>
                          				<option value="1" <c:if test="${requestScope.status eq '1' }">selected</c:if>>未开始</option>
                          				<option value="2" <c:if test="${requestScope.status eq '2' }">selected</c:if>>报名中</option>
                         		   	 	<option value="3" <c:if test="${requestScope.status eq '3' }">selected</c:if>>报名结束</option>
                          				<option value="4" <c:if test="${requestScope.status eq '4' }">selected</c:if>>进行中</option>
                          				<option value="5" <c:if test="${requestScope.status eq '5' }">selected</c:if>>已结束</option>
                          				<option value="6" <c:if test="${requestScope.status eq '6' }">selected</c:if>>已取消</option>
                         		   	</select>
                         	<%-- <span class="col-sm-12" style="margin-top: 10px;">
	                               <input type="hidden" name="tree" value="${requestScope.tree }">
	                               <span  class="col-sm-2">
	                                    	<select id="tree" class="form-control m-b" onchange="getValue(this)">
	                                    	<option >请选择</option>
	                                    	<option value="0">北京</option>
	                                    	</select>
	                                    	</span>
	                               	<span  class="col-sm-3" style="line-height: 32px; " id="treeName"></span>
	                                <span  class="col-sm-1"><a   href="javascript:resetValue();" class="btn btn-primary ">地址重置</a></span>
	                                    </span> --%>	   	
                         	
                         	活动日期：
                         		<input id="createBegin" class="Wdate" name="createBegin" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${requestScope.createBegin }" style="width:100px;"/> - 
                             	<input id="createEnd" class="Wdate" name="createEnd" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${requestScope.createEnd }" style="width:100px;"/>
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                             </span> 
                      	 	<div class="col-md-11">
                                <div class="input-group" align="left">
	                                <span class="col-sm-3">
                             </span>
	                                    <span class="input-group-btn" style="font-size:15px">
		                                    	
				                             	<button type="button" class="btn btn-sm btn-primary" onclick="sousuo()">搜索</button>
				                             	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                             	<button type="button" class="btn btn-sm btn-primary" onclick="toadd()">添加</button>
	                           </span>
                                </div>
                            </div>
                        </form>
                        </div>
                        <div class="project-list">
                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
	                                        <td class="project-title" align="center">名称</td>
                                            <td class="project-title" align="center">活动开始时间</td>
	                                        <td class="project-title" align="center">活动成本</td>
	                                        <td class="project-title" align="center">活动价格</td>
	                                        <td class="project-title" align="center">活动地址</td>
	                                        <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('click_count','${requestScope.collation }')" style="font-size: 12px;]">点击量</a> </td>
	                                        <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('videosum','${requestScope.collation }')" style="font-size: 12px;]">报名付费量</a> </td>
	                                       <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('pricesum','${requestScope.collation }')" style="font-size: 12px;]">付费金额</a> </td>
	                                       <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('share_count','${requestScope.collation }')" style="font-size: 12px;]">转发量</a> </td>
	                                       <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('collect_count','${requestScope.collation }')" style="font-size: 12px;]">收藏量</a> </td>
	                                       <td class="project-title" align="center">
	                                        <a href="javascript:submitFormOrder('comment_count','${requestScope.collation }')" style="font-size: 12px;]">评论量</a> </td>
	                                        <td class="project-title" align="center">状态</td>
	                                        <td class="project-completion" align="center" >操作</td>
                                   	    </tr>
                                	<c:forEach items="${requestScope.code.data.list }" var="data">
                                		<tr id="tr${data.id }">
	                                        <td class="project-title" align="center">
												<small>${data.title }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.activity_time }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.cost}</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.price}</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${data.address }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.click_count }
													</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.videosum }
													</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.pricesum }
													</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
												${data.share_count }
													</small>
	                                        </td>
	                                          <td class="project-title" align="center">
													<small>
												${data.collect_count }
													</small>
	                                        </td>
	                                         <td class="project-title" align="center">
													<small>
												${data.comment_count }
													</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													<c:if test="${data.sta eq '1' }">未开始</c:if>
													<c:if test="${data.sta eq '2' }">报名中</c:if>
													<c:if test="${data.sta eq '3' }">报名结束</c:if>
													<c:if test="${data.sta eq '4' }">进行中</c:if>
													<c:if test="${data.sta eq '5' }">已结束</c:if>
													<c:if test="${data.sta eq '6' }">已取消</c:if>
													</small>
	                                        </td>
	                                        <td class="project-completion" align="center">
	                                        <c:if test="${data.status eq '0' }">
	                                        	<a href="#" class="btn btn-white btn-sm" onclick="return upStatus('${data.id}','${data.status }')" ><i class="fa fa-folder"></i>
	                                        		禁用
	                                        	</a>
	                                        </c:if>
	                                        	<c:if test="${data.status eq '1' }">
		                                        	<a href="#" class="btn btn-white btn-sm" onclick="return upStatus('${data.id}','${data.status }')" ><i class="fa fa-folder"></i>
		                                        		启用
		                                        	</a>
	                                        	</c:if>
	                                            <a href="${pageScope.basePath }active/infoActive.do?id=${data.id}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>编辑查看 </a>
	                                            <a href="#" class="btn btn-white btn-sm" onclick="return delect('${data.id }')"><i class="fa fa-pencil"></i>删除 </a>
	                                        	<a href="#" class="btn btn-white btn-sm" onclick="getDataId('${data.id}')">获得ID</a>
	                                        	<a href="${pageScope.basePath }active/jiabinList.do?id=${data.id}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>活动嘉宾列表 </a>
	                                        	<a href="${pageScope.basePath }active/peoActivelist.do?id=${data.id}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>活动参与用户列表 </a>
	                                        	<a href="${pageScope.basePath }active/activeOrder.do?aid=${data.id}" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>活动订单列表 </a>
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