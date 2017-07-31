<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="qc" uri="http://qc/menuTag" %>
<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="nav-close"><i class="fa fa-times-circle"></i>
    </div>
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element" align="center">
                    <span></span>
                    <div>
                   				 <span class="clear">
                                 <span class="block m-t-xs"><strong class="font-bold">${sessionScope.user.loginname }</strong></span>
                                 <span class="text-muted text-xs block">${sessionScope.user.name }<!-- <b class="caret"></b> --></span>
                                 </span>
                    </div>
                    <!-- 
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold"></strong></span>
                                <span class="text-muted text-xs block"><b class="caret"></b></span>
                                </span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a class="J_menuItem" href="form_avatar.html">修改头像</a>
                        </li>
                        <li><a class="J_menuItem" href="profile.html">个人资料</a>
                        </li>
                        <li><a class="J_menuItem" href="contacts.html">联系我们</a>
                        </li>
                        <li><a class="J_menuItem" href="mailbox.html">信箱</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="login.html">安全退出</a>
                        </li>
                    </ul>
                     -->
                </div>
                <div class="logo-element">H+
                </div>
            </li>

            <!-- 菜单显示开始 -->
            
            <qc:menu menuName="menuName" isParentValue="top" idName="id" isParentName="parentId" parentIdName="parentId" urlName="url" allMenu="${requestScope.allMenus }" userIds="${requestScope.menuIds }"/>
            
        </ul>
    </div>
</nav>
