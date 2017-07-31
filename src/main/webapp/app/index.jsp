<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
         
<!DOCTYPE html>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/" ;
%>
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:16:41 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>美艺圈后台管理</title>

    <meta name="keywords" content="H+后台主题,后台bootstrap框架,会员中心主题,后台HTML,响应式后台">
    <meta name="description" content="H+是一个完全响应式，基于Bootstrap3最新版本开发的扁平化主题，她采用了主流的左右两栏式布局，使用了Html5+CSS3等现代技术">

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="<%=basePath %>app/favicon.ico">
    <link href="<%=basePath %>app/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
    <link href="<%=basePath %>app/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="<%=basePath %>app/css/animate.min.css" rel="stylesheet">
    <link href="<%=basePath %>app/css/style.min862f.css?v=4.1.0" rel="stylesheet">
    <script src="<%=basePath %>app/js/jquery.min.js?v=2.1.4"></script>
    <script type="text/javascript" src="<%=basePath %>app/js/My97DatePicker/WdatePicker.js"></script>
    <script>
    	var message = "${requestScope.message}";
    	$(function(){
    		if(message.trim()!=""){
    			/* alert(message); */
    		}
    	});
    	
    </script>
    
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <jsp:include page="leftNavBar.jsp" />
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="<%=basePath %>login/exit.do" class="roll-nav roll-right J_tabExit" onclick="javascript:if(confirm('确定要退出吗？')) return true;return false;"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main" style="height:100%">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="<%=basePath %>app/welcome.jsp" frameborder="0" data-id="index_v1.html" seamless></iframe>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>
    
    <script src="<%=basePath %>app/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="<%=basePath %>app/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="<%=basePath %>app/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=basePath %>app/js/plugins/layer/layer.min.js"></script>
    <script src="<%=basePath %>app/js/hplus.min.js?v=4.1.0"></script>
    <script type="text/javascript" src="<%=basePath %>app/js/contabs.min.js"></script>
    <script src="<%=basePath %>app/js/plugins/pace/pace.min.js"></script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:17:11 GMT -->
</html>
