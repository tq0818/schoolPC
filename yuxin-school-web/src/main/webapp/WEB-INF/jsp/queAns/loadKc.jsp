<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/wx.tld" prefix="wx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String rootPath = request.getContextPath();
%>
<c:forEach items="${seconds}" var="kcBiao">
	<div class="course par">
		<p class="sub-name">学科小类名称</p>
		<p class="sub-name-show" tkid="${kcBiao.itemId}">${kcBiao.itemName }</p>
		<p class="community-name">社区显示名称：</p>
		<form action="">
			<label for="" class="kName" del="${kcBiao.delFlag}">${kcBiao.classifyName }</label> <input
				type="text" class="iName" value="${kcBiao.classifyName }" />
		</form>
		<div class="revise kcName" cfyId="${kcBiao.id}" del="${kcBiao.delFlag}">修改名称</div>
		<em class="tit-font"> 
			<em class="iconfont normal kcic ${kcBiao.delFlag == 1?'open':'close' } warning-btn" cfyId="${kcBiao.id}">${kcBiao.delFlag == 1?'&#xe603;':'&#xe604;' }</em> 
			<span class="i ${kcBiao.delFlag == 1?'open':'close' }">${kcBiao.delFlag == 1?'已启用':'已禁用' }</span>
		</em>
	</div>
</c:forEach>