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
    <title>统计列表</title>
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
    
    	
    // ------------------------------------------------通用部分(结束)--------------------------------------------------------
      function submitForm(){
  	  $("#dataForm").submit();
   }
    
    
    // ------------------------------------------------AJAX(开始)--------------------------------------------------------
    // ------------------------------------------------AJAX(结束)--------------------------------------------------------
    </script>

</head>

<body class="gray-bg" style="padding:0px;margin:0px">

    <div class="wrapper wrapper-content animated fadeInUp" style="padding:0px;margin:0px">
        <div class="row">
            <div class="col-sm-12">

                <div class="ibox">
                   
                    <div class="ibox-title">
                        <h5>统计列表</h5>
                    	<div class="ibox-tools">
                            <button id="fanhui" onclick="JavaScript:history.back(-1);" type="button" class="btn btn-w-m btn-white btn-xs"><i class="fa fa-reply"></i>返回</button>
                        </div>
                        <div class="ibox-tools">
                           <div  class="row m-b-sm m-t-sm">
                            <form id="dataForm" action="/mei_yi_quan_admin/money/moneyList.do"  method="post">  
                            <input type="hidden" name="page" value="${requestScope.pageIndex }">
                        	<input type="hidden" name="size" value="${requestScope.pageSize }">
                        	<input type="hidden" name="orderBy" value="uvr.feedback_time">
                        	<input type="hidden" name="collation" value="DESC">
                         	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         	<span class="col-sm-12" style="margin-top: 10px;">
                         	
                    		输入关键字：<input type="text" id="orderName" name="orderName" value="${requestScope.orderName }" />
                         	<br/><br/>
                            </span>
                         	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                         	<span class="col-sm-12" style="margin-top: 10px;">
                         	订单日期：
                         		<input id="beginTime" name="beginTime"  class="Wdate" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${requestScope.beginTime }" style="width:100px;"/> - 
                             	<input id="endTime" name="endTime" class="Wdate" type="text" readonly onClick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${requestScope.endTime }" style="width:100px;"/>
                             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                             </span>
                           <%--  <span class="col-sm-12" style="margin-top: 10px;">
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
                          		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      	 	<div class="col-md-11">
                                <div class="input-group" align="left">
	                                <span class="col-sm-3">
                             </span>
	                                    <span class="input-group-btn" style="font-size:15px">
		                                    	<button type="button" class="btn btn-sm btn-primary" onclick="submitForm()">搜索</button>
				                             	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                             	
	                           </span>
                                </div>
                            </div>
                        </form>
                        </div>
                        </div>
                    </div>
                    
                    
                    <div class="ibox float-e-margins">
                    <div class="ibox-content">
                        <table class="table table-bordered">
                            <tbody>
                            
	                                <tr>
	                                    <td style="background-color:#f3f3f4; width:20%;">支付宝单数</td>
	                                    <td>
	                                    ${zcon} 
	                                    </td> 
	                                    <td style="background-color:#f3f3f4; width:20%;">支付宝付费额</td>
	                                    <td>
	                                     ${zsu } 
	                                    </td>
	                                </tr>
                                	<tr>
                               			<td style="background-color:#f3f3f4; width:20%;">微信单数</td>
	                                    <td>
	                                     ${wcon} 
	                                    </td>
	                                    <td style="background-color:#f3f3f4; width:20%;">微信付费额</td>
	                                    <td> 
	                                    ${wsu}
	                                    </td>
                                </tr>
                                <tr>
                               			<td style="background-color:#f3f3f4; width:20%;">ios单数</td>
	                                    <td>
	                                     ${icon} 
	                                    </td>
	                                    <td style="background-color:#f3f3f4; width:20%;">ios付费额</td>
	                                    <td>
	                                    ${isu}
	                                    </td>
                                </tr>
                                <tr>
                               			<td style="background-color:#f3f3f4; width:20%;">总单数</td>
	                                    <td>
	                                     ${zscon} 
	                                    </td>
	                                    <td style="background-color:#f3f3f4; width:20%;">总付费额</td>
	                                    <td>
	                                    ${zssu} 
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
    <script type="text/javascript" src="${pageScope.basePath }app/js/query.js"></script>
	<script type="text/javascript" src="${pageScope.basePath }app/js/paging.js"></script>
    <script>
   
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