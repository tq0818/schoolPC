<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- 二级导航 -->
<div class="full-wrap navbar smbar">
    <div class="header">
        <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe6c5;</i>总览</a>
        <ul class="nav nav-left navspace">
            <shiro:hasPermission name="statistics_area">
                <li code="statistics_area"><a href="<%=request.getContextPath() %>/query/areastatistics/index">总览</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="statistics_area_detail">
                <li code="statistics_area_detail"><a href="<%=request.getContextPath() %>/query/areastatistics/studentList">查询统计</a></li>
            </shiro:hasPermission>
        </ul>
    </div>
</div>
<script>
    $(function() {
        $selectMenu('statistics_area');
        $selectSubMenu('statistics_area');
    })
</script>