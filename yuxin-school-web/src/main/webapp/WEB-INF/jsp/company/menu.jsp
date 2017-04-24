<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<script>
$(function(){
	selectMenu(4);
})
function $select(index){
	$(".smbar").find(".nav").find("li").find("a").removeClass("active");
	$(".smbar").find(".nav").find("li").eq(index).find("a").addClass("active");
}
</script>
<!-- 二级导航 -->
<div class="full-wrap navbar smbar">
    <div class="header">    
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe600;</i>系统</a>
        <ul class="nav nav-left navspace">
            <li><a href="<%=request.getContextPath() %>/sysPageHeadFoot/showHead">页头导航</a></li>
            <li><a href="<%=request.getContextPath() %>/sysPageHeadFoot/toConfigFooter" >页尾配置</a></li>
            <li><a href="<%=request.getContextPath() %>/sysPageHeadFoot/toConfigFooter" >首页模板</a></li>
            <li><a href="<%=request.getContextPath() %>/sysPageHeadFoot/toConfigFooter" >分校管理</a></li>
            <li><a href="<%=request.getContextPath() %>/sysPageHeadFoot/toConfigFooter" >学科设置</a></li>
            <li><a href="./sys-must.html">必填项设置</a></li>
            <li><a href="./sys-must.html">代码维护</a></li>
            <li><a href="./sys-must.html">用户权限</a></li>
        </ul>
    </div>
</div>
<!-- 二级导航结束 -->