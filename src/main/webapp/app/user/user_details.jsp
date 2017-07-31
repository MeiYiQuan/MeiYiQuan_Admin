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
    // ------------------------------------------------AJAX(结束)--------------------------------------------------------
    </script>

</head>

<body class="gray-bg" style="padding:0px;margin:0px">

    <div class="wrapper wrapper-content animated fadeInUp" style="padding:0px;margin:0px">
        <div class="row">
            <div class="col-sm-12">
            <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>会员详情</h5>
                        <div class="ibox-tools">
                            <button onclick="JavaScript:history.back(-1);" type="button" class="btn btn-w-m btn-white btn-xs"><i class="fa fa-reply"></i>返回</button>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <table class="table table-bordered">
                            <tbody>
	                                <tr>
	                                    <td style="background-color:#f3f3f4; width:20%;">会员名称</td>
	                                    <td>${requestScope.user.username  }</td>
	                                    <td style="background-color:#f3f3f4; width:20%;">会员电话</td>
	                                    <td>${requestScope.user.phone  }</td>
	                                </tr>
                                	<tr>
                                    <td style="background-color:#f3f3f4; width:20%;">会员性别</td>
                                    <td>
                                    	<c:if test="${requestScope.user.gender eq '1' }">女</c:if>
										<c:if test="${requestScope.user.gender eq '2' }">男</c:if>
									</td>
                                    <td style="background-color:#f3f3f4; width:20%;">出生日期</td>
                                    <td>	
                                    		<jsp:useBean id="birthday" class="java.util.Date"/>
											<jsp:setProperty name="birthday" property="time" value="${requestScope.user.birthday }"/>
											<fmt:formatDate value="${birthday}" pattern="yyyy年MM月dd日  HH:mm:ss"/>
									</td>
                                </tr>
                                <tr>
                                    <td style="background-color:#f3f3f4; width:20%;">会员类型</td>
                                    <td>
                                    <c:if test="${requestScope.user.user_type eq '0' }">普通用户</c:if>
									<c:if test="${requestScope.user.user_type eq '1' }">讲师用户</c:if>
									<c:if test="${requestScope.user.equi_type eq '0' }">Android</c:if>
                                    <c:if test="${requestScope.user.equi_type eq '1' }">IOS</c:if>
                                    <c:if test="${requestScope.user.equi_type eq '2' }">微信</c:if></td>
                                    <td style="background-color:#f3f3f4; width:20%;">余额(元)</td>
                                    <td>-</td>
                                </tr>
                                <tr>
                                    <td style="background-color:#f3f3f4; width:20%;">注册日期</td>
                                    <td>		
                                    			<jsp:useBean id="create_time" class="java.util.Date"/>
												<jsp:setProperty name="create_time" property="time" value="${requestScope.user.create_time }"/>
												<fmt:formatDate value="${create_time}" pattern="yyyy年MM月dd日  HH:mm:ss"/>
											</td>
                                    <td style="background-color:#f3f3f4; width:20%;">星座</td>
                                    <td>${requestScope.user.zodiac  }</td>
                                </tr>
                                <tr>
                                    <td style="background-color:#f3f3f4; width:20%;">地址</td>
                                    <td>${requestScope.user.district  }</td>
                                    <td style="background-color:#f3f3f4; width:20%;">最后登录时间</td>
                                    <td>
                                     	<jsp:useBean id="latest_login_time" class="java.util.Date"/>
												<jsp:setProperty name="latest_login_time" property="time" value="${requestScope.user.latest_login_time }"/>
												<fmt:formatDate value="${latest_login_time}" pattern="yyyy年MM月dd日  HH:mm:ss"/>
								</td>
                                </tr>
                                  <tr>
                                    <td style="background-color:#f3f3f4; width:20%;">购买视频次数</td>
                                    <td>${requestScope.user.buy_count  }</td>
                                    <td style="background-color:#f3f3f4; width:20%;">最后签到时间</td>
                                    <td>
                                    	<jsp:useBean id="latest_sign_time" class="java.util.Date"/>
												<jsp:setProperty name="latest_sign_time" property="time" value="${requestScope.user.latest_sign_time }"/>
												<fmt:formatDate value="${latest_sign_time}" pattern="yyyy年MM月dd日  HH:mm:ss"/>
									</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            
                <div class="ibox">
                    <div class="ibox-title">
                        <h5>用户订单</h5>
                        <div class="ibox-tools">
                        </div>
                    </div>
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm">
                        </div>
                        <div class="project-list">
                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
	                                        <td class="project-title" align="center">
	                                        订单号</td>
	                                         <td class="project-title" align="center">
	                                        订单状态</td>
	                                        <td class="project-title" align="center">
	                                        创建时间</td>
	                                          <td class="project-title" align="center">
	                                        支付类型</td>
	                                        <td class="project-title" align="center"> 
	                                        实付金额</td>
	                                        <td class="project-title" align="center">
	                                        商品原价</td>
	                                        <td class="project-title" align="center">
	                                        是否使用了优惠券</td>
	                                           <td class="project-title" align="center">
	                                        订单类型</td>
	                                        <td class="project-completion" align="center" >操作</td>
                                   	    </tr>
                                	<c:forEach items="${requestScope.order.data }" var="data">
                                		<tr id="tr${data.id }">
                                		 	<td class="project-title" align="center">
												<small>${data.order_num }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>
													<c:if test="${data.status eq '0' }">未支付</c:if>
													<c:if test="${data.status eq '1' }">已支付</c:if>
													<c:if test="${data.status eq '2' }">已发送</c:if>
												</small>
	                                        </td>
	                                          <td class="project-title" align="center">
												<small> 
												      	<jsp:useBean id="order_create_time" class="java.util.Date"/>
												<jsp:setProperty name="order_create_time" property="time" value="${data.create_time }"/>
												<fmt:formatDate value="${order_create_time}" pattern="yyyy年MM月dd日  HH:mm:ss"/>
												</small>
	                                        </td>
	                                           <td class="project-title" align="center">
												<small>
													<c:if test="${data.pay_type eq '1' }">支付宝</c:if>
	                                        	<c:if test="${data.pay_type eq '2' }">微信</c:if>
	                                        	<c:if test="${data.pay_type eq '3' }">苹果内购</c:if></small>
	                                        </td>
	                                        <td class="project-title" align="center">
	                                        	${data.price }
												<small>
											</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													${data.oldPrice }
													</small>
	                                        </td>
	                                        <td class="project-title" align="center">
													<small>
													<c:if test="${data.isUseCoupon eq '1' }">是</c:if>
													<c:if test="${data.isUseCoupon eq '2' }">否</c:if>
													</small>
	                                        </td>
	                                           <td class="project-title" align="center">
													<small>
													<c:if test="${data.type eq '1' }">视频</c:if>
													<c:if test="${data.type eq '2' }">活动</c:if>
													</small>
	                                        </td>
	                                        <td class="project-completion" align="center">
	                                        <a href="/mei_yi_quan_admin/order/getDetails?id=${data.order_num}" class="btn btn-white btn-sm" >查看详情</a>
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
    <script src="${pageScope.basePath }app/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${pageScope.basePath }app/js/content.min.js?v=1.0.0"></script>
    <script type="text/javascript" src="${pageScope.basePath }app/js/query.js"></script>
	<script type="text/javascript" src="${pageScope.basePath }app/js/paging.js"></script>
    <script>
       $(document).ready(function(){$("#loading-example-btn").click(function(){btn=$(this);simpleLoad(btn,true);simpleLoad(btn,false)})});function simpleLoad(btn,state){if(state){btn.children().addClass("fa-spin");btn.contents().last().replaceWith(" Loading")}else{setTimeout(function(){btn.children().removeClass("fa-spin");btn.contents().last().replaceWith(" Refresh")},2000)}};
       $(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});
       </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>