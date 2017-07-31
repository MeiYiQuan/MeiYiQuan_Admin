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
 </script>

</head>

<body class="gray-bg" style="padding:0px;margin:0px">

    <div class="wrapper wrapper-content animated fadeInUp" style="padding:0px;margin:0px">
        <div class="row">
            <div class="col-sm-12">
            <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>订单详情</h5>
                        <div class="ibox-tools">
                            <button onclick="JavaScript:history.back(-1);" type="button" class="btn btn-w-m btn-white btn-xs"><i class="fa fa-reply"></i>返回</button>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <table class="table table-bordered">
                            <tbody>
	                                <tr>
	                                    <td style="background-color:#f3f3f4; width:20%;">订单号</td>
	                                    <td>${requestScope.order.data.order_num  }</td>
	                                    <td style="background-color:#f3f3f4; width:20%;">订单状态</td>
	                                    <td> 
	                                    	<c:if test="${requestScope.order.data.status eq '0' }">未支付</c:if>
											<c:if test="${requestScope.order.data.status eq '1' }">已支付</c:if>
											<c:if test="${requestScope.order.data.status eq '2' }">已发送</c:if>
											</td>
	                                </tr>
                                	<tr>
                                    <td style="background-color:#f3f3f4; width:20%;"> 创建时间</td>
                                    <td>
                                          	<jsp:useBean id="create_time" class="java.util.Date"/>
												<jsp:setProperty name="create_time" property="time" value="${requestScope.order.data.create_time }"/>
												<fmt:formatDate value="${create_time}" pattern="yyyy年MM月dd日  HH:mm:ss"/>
									</td>
                                    <td style="background-color:#f3f3f4; width:20%;">支付类型</td>
                                    <td>	
                                    	<c:if test="${requestScope.order.data.pay_type eq '1' }">支付宝</c:if>
	                                    <c:if test="${requestScope.order.data.pay_type eq '2' }">微信</c:if>
	                                    <c:if test="${requestScope.order.data.pay_type eq '3' }">苹果内购</c:if>
	                                    </td>
                                </tr>
                                <tr>
                                    <td style="background-color:#f3f3f4; width:20%;"> 实付金额</td>
                                    <td>	${requestScope.order.data.price }</td>
                                    <td style="background-color:#f3f3f4; width:20%;">商品原价</td>
                                    <td>${requestScope.order.data.oldPrice }</td>
                                </tr>
                                <tr>
                                    <td style="background-color:#f3f3f4; width:20%;">是否使用了优惠券</td>
                                    <td>	
                                    		<c:if test="${requestScope.order.data.isUseCoupon eq '1' }">是</c:if>
											<c:if test="${requestScope.order.data.isUseCoupon eq '2' }">否</c:if>
									</td>
                                    <td style="background-color:#f3f3f4; width:20%;">订单类型</td>
                                    <td>
                                    		<c:if test="${requestScope.order.data.type eq '1' }">视频</c:if>
											<c:if test="${requestScope.order.data.type eq '2' }">活动</c:if>
									</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>详情</h5>
                        <div class="ibox-tools">
                            <button onclick="JavaScript:history.back(-1);" type="button" class="btn btn-w-m btn-white btn-xs"><i class="fa fa-reply"></i>返回</button>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <table class="table table-bordered">
                        <!-- 视频订单 -->
                        <c:if test="${requestScope.order.data.type eq '1' }">
                            <tbody>
	                                <tr>
	                                    <td style="background-color:#f3f3f4; width:20%;">标题</td>
	                                    <td>${requestScope.video.data.title  }</td>
	                                    <td style="background-color:#f3f3f4; width:20%;">创建时间</td>
	                                    <td> 
	                                    	-
	                                    </td>
	                                </tr>
                                	<tr>
                                    <td style="background-color:#f3f3f4; width:20%;">价格(单位:元)</td>
                                    	
                                    <td>
                                       </td>
                                    <td style="background-color:#f3f3f4; width:20%;">视频的详细介绍</td>
                                    <td>	
                                    		${requestScope.video.data.remark  }
	                                    </td>
                                </tr>
                            </tbody>
                            </c:if>
                            <!-- 活动订单 -->
                        <c:if test="${requestScope.order.data.type eq '1' }">
                            <tbody>
	                                <tr>
	                                    <td style="background-color:#f3f3f4; width:20%;">标题</td>
	                                    <td>${requestScope.video.data.title  }</td>
	                                    <td style="background-color:#f3f3f4; width:20%;">创建时间</td>
	                                    <td> 
	                                    	-
	                                    </td>
	                                </tr>
                                	<tr>
                                    <td style="background-color:#f3f3f4; width:20%;">价格(单位:元)</td>
                                    	
                                    <td>
                                       </td>
                                    <td style="background-color:#f3f3f4; width:20%;">视频的详细介绍</td>
                                    <td>	
                                    		${requestScope.video.data.remark  }
	                                    </td>
                                </tr>
                            </tbody>
                            </c:if>
                            
                        </table>
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