<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<c:forEach items="${subList}" var="sub">
	<li style="list-style-type: none;">
		<span class="info" style="display: inline-block;width: 170px;height: 32px;">
		<a href="javascript:;" style="line-height: 2.5">${sub.subjectName}</a>
		</span>

		<span class="setM">
		<a href="javascript:;" class="btn btn-mini iconfont" ht="编辑" style="padding: 7px 7px;">&#xe625;</a>
		<a href="javascript:;" class="btn btn-mini iconfont" style="padding: 7px 7px;" subId="${sub.id}" onclick="javascript:Forms.delSubject(this)">&#xe626;</a>
		</span>
		
		<span style="" class="none edit"><br/>
		<input type="text" value="${sub.subjectName}" maxlength="22" class="newSubName" style="width: 152px;"/>
		<a href="javascript:;" class="btn btn-mini btn-success" subId="${sub.id}">保存</a>
		<a href="javascript:;" class="btn btn-mini btn-default">取消</a>
		</span>
	</li>
</c:forEach>