<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<c:forEach items="${secList}" var="sec">
	<li style="position: relative;" sectionId="${sec.id}" class="secOrder secCss${sec.id}">
		<span class="info" sectionId="${sec.id}" style="display: inline-block;width: 170px;height: 32px;">
		<a href="javascript:void(0)" sectionId="${sec.id}" style="line-height: 2.5">${sec.sectionName}</a>
		</span>
		
		<span class="sectionM none">
		<a href="javascript:void(0)" class="btn btn-sm iconfont" style="padding: 7px 7px;float: right;" secDelId="${sec.id}" onclick="javascript:Forms.delSection(this)">&#xe626;</a>
		<a href="javascript:void(0)" class="btn btn-sm iconfont" style="padding: 7px 7px;float: right;" ht="编辑">&#xe625;</a>
		</span>
		
		<span style="" class="none edit"><br/>
		<input type="text" value="${sec.sectionName}"  maxlength="22" class="newSectionName" style="width: 153px;"/>
		<a href="javascript:void(0)" class="btn btn-sm btn-success" sectionAddId="${sec.id}">保存</a>
		<a href="javascript:void(0)" class="btn btn-sm btn-default">取消</a>
		</span>
	</li>
</c:forEach>