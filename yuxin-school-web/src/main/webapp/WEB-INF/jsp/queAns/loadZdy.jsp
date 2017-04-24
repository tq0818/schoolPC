<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/wx.tld" prefix="wx"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String rootPath = request.getContextPath();
%>
<c:forEach items="${classfys}" var="cfy" varStatus="index">
	<div class="padd-bar par delMark${cfy.id}">
		<div class="show-name">显示名称：</div>
		<form action="">
			<label for="definition${cfy.id}" class="lName">${cfy.classifyName }</label>
			<input type="text" id="definition${cfy.id}" class="iName"
				value="${cfy.classifyName}" />
		</form>
		<div class="revise zdyName" cfyId="${cfy.id}">修改名称</div>
		<div class="del" cfyId="${cfy.id}">删除</div>
		<div class="tit-font">
			<em class="iconfont normal zdyic warning-btn ${cfy.delFlag == 1?'open':'close'}"
				cfyId="${cfy.id }">${cfy.delFlag == 1?'&#xe603;':'&#xe604;'}</em> <span
				class="i ${cfy.delFlag == 1?'open':'close'}">${cfy.delFlag == 1?'已启用':'已禁用'}</span>
		</div>
	</div>
</c:forEach>