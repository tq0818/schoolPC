<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<script>
$(function(){
	$selectMenu('teach_head');
})
</script>
<!-- 二级导航 -->
<div class="full-wrap navbar smbar">
    <div class="header">    
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe613;</i>教学</a>
        <ul class="nav nav-left navspace">
             <shiro:hasPermission name="teacher_live">
            <li code="teacher_live"><a href="<%=request.getContextPath() %>/classModule/show" >上直播</a></li>
            </shiro:hasPermission>
            <c:if test="${sessionScope.isAreaSchool1 eq 0}">
            <shiro:hasPermission name="home_work">
            <li code="home_work"><a href="<%=request.getContextPath() %>/homework/toHomeWorkIndex" >课后作业</a></li>
            </shiro:hasPermission>
            </c:if>
        </ul>
    </div>
</div>
