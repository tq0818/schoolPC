<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@include file="/decorators/import.jsp"%>
<script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/tikuHeader.js"></script>
<div class="full-wrap navbar smbar">
    <div class="header tiHeader">    
        <!-- <a href="javascript:;" class="navbar-brand"><i class="iconfont">&#xe624;</i>题库</a> -->
        <a href="javascript:;" class="navbar-brand"><!-- <span id="cancle">返回</span>&lt; --><span id="tiku"></span></a>
        <ul class="nav nav-left navspace tikuhader">
            <shiro:hasPermission name="tiku_topic">
            <li code="tiku_topic"><a href="javascript:;" class="th">试题</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="tiku_paper">
            <li code="tiku_paper"><a href="javascript:;" class="th">试卷</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="tiku_exampoint">
            <li code="tiku_exampoint"><a href="javascript:;" class="th">章节考点</a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="tiku_subject">
        	<li code="tiku_subject"><a href="javascript:;" class="th">科目管理</a></li>
            </shiro:hasPermission>
        </ul>
    </div>
</div>