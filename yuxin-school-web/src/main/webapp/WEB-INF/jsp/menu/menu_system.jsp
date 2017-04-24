<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/decorators/import.jsp"%>
<!-- 二级导航 -->
<div class="full-wrap navbar smbar">
    <div class="header">    
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe600;</i>系统</a>
        <ul class="nav nav-left navspace">
            <shiro:hasPermission name="org_service">  
            	<li code="org_service"><a href="<%=request.getContextPath() %>/company/companyService" id="kaitongfuwu">开通服务</a></li>
            </shiro:hasPermission>
            
           	<shiro:hasPermission name="sys_user_auth">  
            	<li code="sys_user_auth"><a href="<%=request.getContextPath() %>/authPrivilege/showAuth">用户权限</a></li>
            </shiro:hasPermission>
        </ul>
    </div>
</div>
<script>
$(function(){
	$selectMenu('system_head');
	
})

</script>