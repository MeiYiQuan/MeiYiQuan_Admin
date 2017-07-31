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
    
    	// 每页的行数,EL表达式取回的是默认值，这个值可以用js修改的
    	var pageEach = "${requestScope.page.eachRows}";
    	
    	
    	// 初始化方法
    	$(function(){
    		if("${requestScope.message}".trim()!=""){alert("${requestScope.message}");}
    		$("#eachPages").val(pageEach);
    	});
    	
    	// 修改每页显示的行数
    	function upEachPages(){
    		var input = $("#eachPages").val();
    		if(!isNaN(input)){
    			if(input<=0){
    				alert("每页显示行数要大于0！");
    			}else{
    				$(".qcpagestyle").each(function(){
    					var h = $(this).attr("href");
        	    		var newH = h.replace("&size=" + pageEach,"&size=" + input);
        	    		$(this).attr("href",newH);
    				});
    				pageEach = input;
    				alert("修改成功！每页显示" + pageEach + "行！请刷新页面！");
    			}
    		}else{
    			alert("请输入数字！");
    		}
    		return false;
    	}
    	
    	// 点击搜索
    	function sousuo(){
    		var type = $("#type").val();
    		var status = $("#status").val();
    		var applyLoginName = $("#applyLoginName").val();
    		var aproveLoginName = $("#aproveLoginName").val();
    		var couponName = $("#couponName").val();
    		var applyTimeBegin = $("#applyTimeBegin").val();
    		var applyTimeEnd = $("#applyTimeEnd").val();
    		var verifyTimeBegin = $("#verifyTimeBegin").val();
			var verifyTimeEnd = $("#verifyTimeEnd").val();
    		window.location.href = "${pageScope.basePath }money/getqcList.do?page=1&size=" + pageEach + 
    				"&type="+type+"&applyLoginName="+applyLoginName+"&aproveLoginName="+aproveLoginName+
    				"&couponName="+couponName+"&applyTimeBegin="+applyTimeBegin+"&status="+status+"&verifyTimeEnd="+verifyTimeEnd+
    				"&applyTimeEnd="+applyTimeEnd+"&verifyTimeBegin="+verifyTimeBegin;
    	}
    	
    	// 刷新
    	function refresh(){
    		window.location.href = "${pageScope.basePath }money/getqcList.do?page=${requestScope.page.page }&size=" + pageEach + 
    				"&" + "${pageScope.conditions }";
    	}
    	
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
                        <div class="row m-b-sm m-t-sm">
                            <div class="col-md-11">
                                <div class="input-group" align="left">
                                			申请人账号：<input type="text" id="applyLoginName" value="${requestScope.applyLoginName }" />
                                			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                			审核人账号：<input type="text" id="aproveLoginName" value="${requestScope.aproveLoginName }" />
                                			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                			优惠券名称：<input type="text" id="couponName" value="${requestScope.couponName }" />
                                			<br/><br/>
                                			申请时间：
	                                    	<input id="applyTimeBegin" class="Wdate" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${requestScope.applyTimeBegin }" style="width:100px;"/> - 
	                                        <input id="applyTimeEnd" class="Wdate" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${requestScope.applyTimeEnd }" style="width:100px;"/>
	                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                                                                                           审核时间：
	                                    	<input id="verifyTimeBegin" class="Wdate" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${requestScope.verifyTimeBegin }" style="width:100px;"/> - 
	                                        <input id="verifyTimeEnd" class="Wdate" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${requestScope.verifyTimeEnd }" style="width:100px;"/>
	                                    	<br/><br/>
	                                    	优惠券类型：<select id="type">
	                                    					<option value="" <c:if test="${(requestScope.type eq null) or (requestScope.type eq '') }">selected</c:if>>全部</option>
		                                    				<option value="1" <c:if test="${requestScope.type eq '1' }">selected</c:if>>打折券</option>
		                                    				<option value="0" <c:if test="${requestScope.type eq '0' }">selected</c:if>>抵用券</option>
	                                    		   	 </select>
	                                    	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                    	状态：<select id="status">
	                                    					<option value="" <c:if test="${(requestScope.status eq null) or (requestScope.status eq '') }">selected</c:if>>全部</option>
		                                    				<option value="1" <c:if test="${requestScope.status eq '1' }">selected</c:if>>未审核</option>
		                                    				<option value="2" <c:if test="${requestScope.status eq '2' }">selected</c:if>>通过</option>
		                                    				<option value="3" <c:if test="${requestScope.status eq '3' }">selected</c:if>>未通过</option>
	                                    		   	 </select>
	                                     	<span class="input-group-btn" style="font-size:15px">
	                                        	<button type="button" class="btn btn-sm btn-primary" onclick="refresh()">刷新</button>
	                                        	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                        	<button type="button" class="btn btn-sm btn-primary" onclick="sousuo()">搜索</button>
	                                        </span>
                                </div>
                                <br/><br/>
                            </div>
                        </div>

                        <div class="project-list">

                            <table class="table table-hover" >
                                <tbody id="ttbody">
                                		<tr>
                                			<td class="project-title" align="center">优惠券<br/>名称</td>
	                                        <td class="project-title" align="center">优惠券<br/>类型</td>
	                                        <td class="project-title" align="center">优惠券<br/>面额</td>
	                                        <td class="project-title" align="center">接收者</td>
	                                        <td class="project-title" align="center">申请人<br/>账号</td>
	                                        <td class="project-completion" align="center" >申请时间</td>
	                                        <td class="project-completion" align="center" >审核人<br/>账号</td>
	                                        <td class="project-completion" align="center" >审核时间</td>
	                                        <td class="project-completion" align="center" >审核状态</td>
	                                        <td class="project-completion" align="center" >操作</td>
                                   	    </tr>
                                	
                                	<c:forEach items="${requestScope.result }" var="verify">
                                		<tr id="tr${verify.id }">
                                			<td class="project-title" align="center">
	                                            <small>${verify.couponName }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>
													<c:if test="${verify.couponType eq '0' }">抵用券</c:if>
													<c:if test="${verify.couponType eq '1' }">打折券</c:if>
												</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${verify.couponPrice }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${verify.userName }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${verify.applyName }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small>${verify.applyTime }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small id="admin${verify.id }">${verify.verifyName }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small id="upTime${verify.id }">${verify.verifyTime }</small>
	                                        </td>
	                                        <td class="project-title" align="center">
												<small id="statu${verify.id }">
													<c:if test="${verify.status eq '1' }">审核中</c:if>
													<c:if test="${verify.status eq '2' }">通过</c:if>
													<c:if test="${verify.status eq '3' }">未通过</c:if>
												</small>
	                                        </td>
	                                        <td class="project-completion" align="center" id="caozuo${verify.id }">
	                                        	
	                                        	<c:if test="${verify.status eq '1' }">
	                                        		<a href="javascript:chuli('${verify.id }',2)" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>通过</a>
	                                        		<a href="javascript:chuli('${verify.id }',3)" class="btn btn-white btn-sm"><i class="fa fa-folder"></i>驳回</a>
	                                        	</c:if>
	                                        	
	                                        	<c:if test="${verify.status eq '2' or verify.status eq '3' }">
	                                        		已处理
	                                        	</c:if>
	                                        	
	                                        </td>
                                   	    </tr>
                                	</c:forEach>
                                		<tr>
                                			<td colspan="10" align="center" >
                                				<qc:qcPage sumPage="${requestScope.page.sumPages }" url="${pageScope.basePath }money/getqcList.do" 
	                                				conditions="size=${requestScope.page.eachRows }&${pageScope.conditions }" 
	                                				style="class=\"qcpagestyle\"" type="${requestScope.pageType }" nowPage="${requestScope.page.page }"/>
                                				&nbsp;&nbsp;&nbsp;&nbsp;共计<font style="color:red;">${requestScope.page.sumRows }</font>条符合条件的信息
                                				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                				每页显示&nbsp;<span><input type="text" id="eachPages" value="" style="width:40px" /></span>&nbsp;条
                                				&nbsp;&nbsp;&nbsp;&nbsp;
                                				<a href="#" class="btn btn-white btn-sm" onclick="return upEachPages()"><i class="fa fa-folder"></i>修改</a>
                                			</td>
                                		</tr>
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
    <script>
       $(document).ready(function(){$("#loading-example-btn").click(function(){btn=$(this);simpleLoad(btn,true);simpleLoad(btn,false)})});function simpleLoad(btn,state){if(state){btn.children().addClass("fa-spin");btn.contents().last().replaceWith(" Loading")}else{setTimeout(function(){btn.children().removeClass("fa-spin");btn.contents().last().replaceWith(" Refresh")},2000)}};
       $(".fancybox").fancybox({openEffect:"none",closeEffect:"none"});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    </body>

<!-- Mirrored from www.zi-han.net/theme/hplus/projects.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:19:44 GMT -->
</html>